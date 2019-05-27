package com.ceiec.twmp.tmp.utils.tools.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.WriteModel;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: utils for mongodb
 * @create 2019-03-19 15:57
 **/
public class MongodbUtils {

    private static MongodbConfig mongodbConfig;

    @Autowired
    public void setMongodbConfig(MongodbConfig mongodbConfig){
        MongodbUtils.mongodbConfig = mongodbConfig;
    }


    private static MongoClient mongoClient;

    private static MongoDatabase mongoDb;

    private static List<ServerAddress> serverAddressList = new ArrayList<ServerAddress>();

    private static final String GPS_DB= "gps";

    private static final String GPS_COLLECTION = "gps_collection";

    private final static String CLUSTER_MODE = "cluster";

    private final static String SINGLE_MODE = "single";

    private final static Logger logger = LoggerFactory.getLogger(MongodbUtils.class);


    public static boolean initConnection(){
        try {
            //连接mongoDb
            if(mongoClient == null){

                MongoClientOptions.Builder buider = new MongoClientOptions.Builder();
                buider.connectionsPerHost(Integer.parseInt(mongodbConfig.getConnectCount()));// 与目标数据库可以建立的最大链接数
                buider.connectTimeout(Integer.parseInt(mongodbConfig.getTimeout()));// 与数据库建立链接的超时时间
                buider.maxWaitTime(Integer.parseInt(mongodbConfig.getWaitTime()));// 一个线程成功获取到一个可用数据库之前的最大等待时间
                buider.threadsAllowedToBlockForConnectionMultiplier(100);
                buider.maxConnectionIdleTime(0);
                buider.maxConnectionLifeTime(0);
                buider.socketTimeout(0);
//                buider.socketKeepAlive(true);
                MongoClientOptions myOptions = buider.build();
                String mode = mongodbConfig.getMode();

                if (CLUSTER_MODE.equals(mode)) {
                    String[] ipArray = mongodbConfig.getHost().split(",");
                    String[] portArray = mongodbConfig.getPort().split(",");

                    for(int i=0; i<ipArray.length; i++){
                        ServerAddress address = new ServerAddress(ipArray[i], Integer.parseInt(portArray[i]));
                        serverAddressList.add(address);
                    }

                    mongoClient = new MongoClient(serverAddressList);
                }else {
                    mongoClient = new MongoClient(new ServerAddress(mongodbConfig.getHost(), Integer.parseInt(mongodbConfig.getPort())), myOptions);
                }

                mongoDb = mongoClient.getDatabase(GPS_DB);

            }
        }catch (Exception e){
            logger.error("connect mongodb is failed", e);
            return false;
        }

        return true;
    }


    /*************************************************************************************************************************************
     ** @Description test connecting mongodb
     ** @Return boolean
     ** @Author Ding
     ** @Date 2019/3/19 16:14
     **
     ************************************************************************************************************************************/
    public static boolean testConnection(){
        //初始化连接
        boolean flag = initConnection();

        if(!flag){
            return false;
        }

        try{
            mongoDb.getName();
        }catch (Exception e){
            logger.error("get mongodb name is failed", e);
            return false;
        }

        return true;
    }



    /*************************************************************************************************************************************
     ** @Description insert data into mongodb
     ** @param: documents
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/19 16:15
     **
     ************************************************************************************************************************************/
    public static void executeInsert(List<WriteModel<Document>> documents){
        //初始化连接
        initConnection();

        //表结构集合
        MongoCollection<Document> docCollection = mongoDb.getCollection(GPS_COLLECTION);

        try{
            docCollection.bulkWrite(documents);

        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }





    /*************************************************************************************************************************************
     ** @Description query record from mongodb by condition
     ** @param: queryCondition
     ** @param: sort
     ** @Return java.util.List<org.bson.Document>
     ** @Author Ding
     ** @Date 2019/3/28 9:48
     **
     ************************************************************************************************************************************/
    public static List<Document> queryRecordList(BasicDBObject queryCondition, BasicDBObject sort) {
        List<Document> documents = new ArrayList<>();
        //初始化连接
        initConnection();

        MongoCollection<Document> collection = mongoDb.getCollection(GPS_COLLECTION);
        if(sort!=null){
            documents = collection.find(queryCondition).sort(sort).into(new ArrayList<Document>());
        }else{
            documents = collection.find(queryCondition).into(new ArrayList<Document>());
        }

        return documents;
    }




}
