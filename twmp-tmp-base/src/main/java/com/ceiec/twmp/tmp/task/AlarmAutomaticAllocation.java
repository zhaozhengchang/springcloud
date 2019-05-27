package com.ceiec.twmp.tmp.task;

import com.ceiec.twmp.tmp.cache.redis.PAARedis;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.common.dict.AlarmStatus;
import com.ceiec.twmp.tmp.common.dict.AlarmType;
import com.ceiec.twmp.tmp.common.dict.MessageType;
import com.ceiec.twmp.tmp.common.enums.ESystemParameter;
import com.ceiec.twmp.tmp.entity.TwmpSmAlarmEf;
import com.ceiec.twmp.tmp.services.IAlarmService;
import com.ceiec.twmp.tmp.services.IParameterService;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.tools.redis.RedisUtils;
import com.ceiec.twmp.tmp.vo.alarm.result.RedisPAAVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserOnlineVO;
import com.ceiec.twmp.tmp.websocket.message.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ding
 * @version V1.0
 * @Description: alarm automatic allocation
 * @create 2019-03-25 14:29
 **/
@Component
@Order(value=101)
public class AlarmAutomaticAllocation implements CommandLineRunner {
    private static final String LOCK_KEY = "alarmAutomaticAllocation";

    @Autowired
    private IParameterService parameterService;

    @Autowired
    private IAlarmService alarmService;

    @Autowired
    private MessageSender messageSender;

    private Integer aaaNum = 10;

    private List<RedisPAAVO> paaList = new ArrayList<>();

    private Lock lock = new ReentrantLock();

    private Lock userLock = new ReentrantLock();

    private static Logger logger = LoggerFactory.getLogger(AlarmAutomaticAllocation.class);

    @Override
    public void run(String... args) {

        StartThread startThread = new StartThread();
        startThread.start();

    }


    class StartThread extends Thread {
        @Override
        public void run() {
            setNum();

            while(true){
                if(!RedisUtils.checkRedisKey(LOCK_KEY)){
                    new AlarmSortThread().start();
                    new AlarmAllocationThread().start();
                    new SynchronizeDataThread().start();
                    RedisUtils.delRedisKey(LOCK_KEY);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void setNum(){
         aaaNum = Integer.parseInt(parameterService.queryByParameterName(ESystemParameter.AAA_NUM.getName()).getParameterValue());
    }


    /*************************************************************************************************************************************
     ** @Description sort alarm which is hig priority
     ** @Return
     ** @Author Ding
     ** @Date 2019/3/25 15:42
     **
     ************************************************************************************************************************************/
    class AlarmSortThread extends Thread{
        @Override
        public void run() {

            while (true){
                Map<String, RedisPAAVO> paaMap = new HashMap<>();
                userLock.lock();
                try{
                    paaMap = PAARedis.getPAA();

                    if(paaMap==null || paaMap.size()==0){
                        continue;
                    }
                }catch (Exception e){
                    logger.error("get paaMap is error ",e);
                }finally {
                    userLock.unlock();
                }


                lock.lock();
                try{

                    //convert map to list
                    paaList = new ArrayList<>();
                    for(String alarmId : paaMap.keySet()){
                        paaList.add(paaMap.get(alarmId));
                    }

                    //sort alarm, the condition is alarm level, task level, alarm time
                    for (int i = 0; i < paaList.size() - 1; i++) {
                        for (int j = 0; j < paaList.size() - 1 - i; j++) {
                            boolean changeFlag = false;

                            if (AlarmType.get(paaList.get(j).getAlarmType()).level < AlarmType.get(paaList.get(j+1).getAlarmType()).level) {
                                changeFlag = true;

                            }else if(AlarmType.get(paaList.get(j).getAlarmType()).level == AlarmType.get(paaList.get(j+1).getAlarmType()).level){

                                if(paaList.get(j).getTaskLevel() < paaList.get(j+1).getTaskLevel()){
                                    changeFlag = true;

                                }else if(paaList.get(j).getTaskLevel() .equals(paaList.get(j+1).getTaskLevel()) ){

                                    if(paaList.get(j).getAlarmTime().getTime() > paaList.get(j+1).getAlarmTime().getTime()){
                                        changeFlag = true;
                                    }
                                }
                            }

                            if(changeFlag){
                                RedisPAAVO temp = paaList.get(j+1);
                                paaList.set(j+1, paaList.get(j));
                                paaList.set(j, temp);
                            }
                        }
                    }

                }catch (Exception e){
                    logger.error("alarm sort is error ",e);
                }finally {
                    lock.unlock();
                }

                try {
                    sleep(500); //every 500 millis sort alarm once
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /*************************************************************************************************************************************
     ** @Description allocate alarm to user online
     ** @Return
     ** @Author Ding
     ** @Date 2019/3/25 16:58
     **
     ************************************************************************************************************************************/
    class AlarmAllocationThread extends Thread{
        @Override
        public void run(){
            while (true){

                lock.lock();
                userLock.lock();
                try{
                    if(paaList!=null && paaList.size()>0){

                        /** get user online info **/
                        Map<String, RedisUserOnlineVO> userOnlineMap = UserInfoRedis.getUserOnline();

                        /** loop and get the num-userOnlineList map **/
                        if(userOnlineMap!=null && userOnlineMap.size()>0){
                            Map<Integer, List<RedisUserOnlineVO>> userAAANMap = new HashMap<>();

                            for(String token : userOnlineMap.keySet()){
                                List<RedisUserOnlineVO> list =  userAAANMap.get(userOnlineMap.get(token).getAlarmAutomaticAllocationNum());
                                if(list == null){
                                    list = new ArrayList<>();
                                }
                                list.add(userOnlineMap.get(token));
                            }


                            Map<Long, List<Long>> userAlarmMap = new HashMap<>();
                            Map<Long, String> userNameMap = new HashMap<>();

                            for(RedisPAAVO paaVo : paaList){

                                /** find the smallest aaaNum of user and allocate to him , then make the num++ **/
                                for(int i=0; i<=aaaNum; i++){
                                    if(userAAANMap.get(i)!=null && userAAANMap.get(i).size()>0){

                                        /** the user is not the reject one **/
                                        if(paaVo.getRejectUserId().indexOf(userAAANMap.get(i).get(0).getUserId().toString())>=0){
                                            RedisUserOnlineVO redisUserOnlineVO = userAAANMap.get(i).remove(0);
                                            redisUserOnlineVO.setAlarmAutomaticAllocationNum(i+1);

                                            List<RedisUserOnlineVO> nextUserNum = userAAANMap.get(i+1);
                                            if(nextUserNum==null){
                                                nextUserNum = new ArrayList<>();
                                            }

                                            nextUserNum.add(redisUserOnlineVO);
                                            userAAANMap.put(i+1, nextUserNum);

                                            List<Long> alarmIdList = userAlarmMap.get(redisUserOnlineVO.getUserId());
                                            if(alarmIdList == null){
                                                alarmIdList = new ArrayList<>();
                                            }
                                            alarmIdList.add(paaVo.getAlarmId());
                                            userAlarmMap.put(redisUserOnlineVO.getUserId(), alarmIdList);
                                            userNameMap.put(redisUserOnlineVO.getUserId(), redisUserOnlineVO.getUserName());

                                            UserInfoRedis.userAANPlusOne(redisUserOnlineVO.getToken());
                                            PAARedis.delPAA(paaVo.getAlarmId());

                                            break;
                                        }
                                    }
                                }
                            }

                            if(userAlarmMap!=null && userAlarmMap.size()>0 && userNameMap!=null && userNameMap.size()>0){
                                //update alarm handle user
                                alarmService.updateAlarmHandleUserBatch(userAlarmMap, userNameMap);

                                for(Long userId: userNameMap.keySet()){
                                    messageSender.sendUserSystemMessage(userId, MessageType.allocation);
                                }
                            }

                        }
                    }
                }catch (Exception e){
                    logger.error("alarm allocation is error ",e);
                }finally {
                    userLock.unlock();
                    lock.unlock();
                }



                try {
                    sleep(1000); //every 1000 millis allocate alarm once
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*************************************************************************************************************************************
     ** @Description to synchronize paa and user online info
     ** @Return
     ** @Author Ding
     ** @Date 2019/3/26 11:35
     **
     ************************************************************************************************************************************/
    class SynchronizeDataThread extends Thread{
        @Override
        public void run(){
            while (true){
                userLock.lock();

                try{
                    List<TwmpSmAlarmEf> alarmEfList = alarmService.getUnDisposedAlarm();
                    if(alarmEfList!=null && alarmEfList.size()>0){
                        for(TwmpSmAlarmEf twmpSmAlarmEf: alarmEfList){

                            if(twmpSmAlarmEf.getAlarmStatus() == AlarmStatus.undistributed.value){//if alarm is undistributed , update it to paa redis
                                RedisPAAVO redisPAAVO = new RedisPAAVO();
                                ObjectUtils.copy(twmpSmAlarmEf, redisPAAVO);
                                PAARedis.addPAA(redisPAAVO);
                            }else if(twmpSmAlarmEf.getAlarmStatus() == AlarmStatus.toDispose.value){//if alarm is to dispose, set user online info alarm num

                                RedisUserOnlineVO userOnlineVO = UserInfoRedis.getUserOnlineByUserId(twmpSmAlarmEf.getHandleUserId());
                                if(userOnlineVO!=null){
                                    UserInfoRedis.userAANPlusOne(userOnlineVO.getToken());
                                    break;
                                }


                            }
                        }
                    }else{ // there is no alarm data
                        PAARedis.setPaaEmpty();

                        Map<String, RedisUserOnlineVO> userOnlineMap = UserInfoRedis.getUserOnline();
                        if(userOnlineMap !=null && userOnlineMap.size()>0){
                            for(String token : userOnlineMap.keySet()){
                                UserInfoRedis.clearAANP(token);
                            }
                        }
                    }

                }catch (Exception e){
                    logger.error("synchronize data is failed ", e);
                }
                try {
                    sleep(10*60*1000); //every 10 minutes synchronize once
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }





}
