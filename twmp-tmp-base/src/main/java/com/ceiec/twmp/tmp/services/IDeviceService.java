package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.common.dict.LifecycleEventType;
import com.ceiec.twmp.tmp.entity.TwmpDevDevice;
import com.ceiec.twmp.tmp.entity.TwmpDevLifecycle;
import com.ceiec.twmp.tmp.entity.TwmpDevType;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.device.query.DevicePageQueryVO;
import com.ceiec.twmp.tmp.vo.device.query.DeviceQueryVO;
import com.ceiec.twmp.tmp.vo.device.result.DeviceAttributeExtend;
import com.ceiec.twmp.tmp.vo.device.result.DeviceListVO;
import com.ceiec.twmp.tmp.vo.device.update.DeviceStatusUpdateVO;
import com.ceiec.twmp.tmp.vo.device.update.DeviceUpdateVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: device service
 * @create 2019-04-02 9:20
 **/
public interface IDeviceService {
    /*************************************************************************************************************************************
     ** @Description get device type
     ** @param:
     ** @Return java.util.List<com.ceiec.twmp.tmp.entity.TwmpDevType>
     ** @Author Ding
     ** @Date 2019/4/2 9:54
     **
     ************************************************************************************************************************************/
    List<TwmpDevType> getDeviceType();
    /*************************************************************************************************************************************
     ** @Description get device table column
     ** @param:
     ** @Return java.util.List<com.ceiec.twmp.tmp.vo.device.result.DeviceAttributeExtend>
     ** @Author Ding
     ** @Date 2019/4/2 9:54
     **
     ************************************************************************************************************************************/
    List<DeviceAttributeExtend> getDeviceTableColumn(Long typeId);


    /*************************************************************************************************************************************
     ** @Description
     ** @param:
     ** @Return java.util.List<com.ceiec.twmp.tmp.entity.TwmpDevDevice>
     ** @Author Ding
     ** @Date 2019/4/2 10:18
     **
     ************************************************************************************************************************************/
    PagedItemsVO<DeviceListVO> getDeviceByPage(String token, DevicePageQueryVO deviceQueryVO);

    /*************************************************************************************************************************************
     ** @Description get device by device id
     ** @param: deviceId
     ** @Return com.ceiec.twmp.tmp.entity.TwmpDevDevice
     ** @Author Ding
     ** @Date 2019/4/3 16:31
     **
     ************************************************************************************************************************************/
    TwmpDevDevice getDeviceById(Long deviceId);

    /*************************************************************************************************************************************
     ** @Description insert/,update device
     ** @param:
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/2 11:18
     **
     ************************************************************************************************************************************/
    void updateDevice(String token, DeviceUpdateVO deviceUpdateVO);

    /*************************************************************************************************************************************
     ** @Description delete device
     ** @param: token
     ** @param: deviceId
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/2 11:52
     **
     ************************************************************************************************************************************/
    void deleteDevice(String token, Long deviceId);

   /*************************************************************************************************************************************
    ** @Description query device list by device number
    ** @param: deviceNumber
    ** @Return java.util.List<com.ceiec.twmp.tmp.entity.TwmpDevDevice>
    ** @Author Ding
    ** @Date 2019/4/3 11:24
    **
    ************************************************************************************************************************************/
    List<TwmpDevDevice> queryDeviceByDeviceNumber(String deviceNumber);

    /*************************************************************************************************************************************
     ** @Description update device status
     ** @param: deviceStatusUpdateVO
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/3 11:56
     **
     ************************************************************************************************************************************/
    void updateDeviceStatus(String token, DeviceStatusUpdateVO deviceStatusUpdateVO);

    /*************************************************************************************************************************************
     ** @Description get device lifecycle
     ** @param: deviceId
     ** @Return java.util.List<com.ceiec.twmp.tmp.entity.TwmpDevLifecycle>
     ** @Author Ding
     ** @Date 2019/4/3 13:56
     **
     ************************************************************************************************************************************/
    List<TwmpDevLifecycle> getDeviceLifecycle(String token, Long deviceId);

    /*************************************************************************************************************************************
     ** @Description download device import template xls
     ** @param: response
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/4 14:24
     **
     ************************************************************************************************************************************/
    void downloadDeviceTemplate(String token, Long deviceTypeId,  HttpServletResponse response) throws IOException;

    /*************************************************************************************************************************************
     ** @Description export device list into excel
     ** @param: toke
     ** @param: deviceQueryVO
     ** @param: response
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/4 17:01
     **
     ************************************************************************************************************************************/
    void exportDevice(String token, DeviceQueryVO deviceQueryVO, HttpServletResponse response) throws IOException;

    /*************************************************************************************************************************************
     ** @Description  import device info from xls
     ** @param: token
     ** @param: typeId
     ** @param: file
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/10 9:30
     **
     ************************************************************************************************************************************/
    String importDevice(String token, Long typeId, MultipartFile file) throws IOException;

    /*************************************************************************************************************************************
     ** @Description insert device lifecycle
     ** @param: token
     ** @param: deviceId
     ** @param: LifecycleEventType
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/12 10:49
     **
     ************************************************************************************************************************************/
    void insertDeviceLifecycle(String token, TwmpDevDevice device, LifecycleEventType LifecycleEventType);
}
