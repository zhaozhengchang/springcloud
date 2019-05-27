package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.entity.TwmpDevDevice;
import com.ceiec.twmp.tmp.entity.TwmpDevLifecycle;
import com.ceiec.twmp.tmp.entity.TwmpDevType;
import com.ceiec.twmp.tmp.services.IDeviceService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.device.query.DevicePageQueryVO;
import com.ceiec.twmp.tmp.vo.device.query.DeviceQueryVO;
import com.ceiec.twmp.tmp.vo.device.result.DeviceAttributeExtend;
import com.ceiec.twmp.tmp.vo.device.result.DeviceListVO;
import com.ceiec.twmp.tmp.vo.device.update.DeviceStatusUpdateVO;
import com.ceiec.twmp.tmp.vo.device.update.DeviceUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: device controller
 * @create 2019-04-02 9:16
 **/
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;


    /*************************************************************************************************************************************
     ** @Description get device table column
     ** @param: token
     ** @param: departmentAddVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/2 10:49
     **
     ************************************************************************************************************************************/
    @PostMapping("/deviceTableColumn/{typeId}")
    public ResponseContent deviceTableColumn(@RequestHeader String token, @PathVariable Long typeId) {
        List<DeviceAttributeExtend> list = deviceService.getDeviceTableColumn(typeId);
        return new ResponseContent(ResponseType.SUCCESS, list);
    }

    /*************************************************************************************************************************************
     ** @Description get device type column
     ** @param: token
     ** @param: departmentAddVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/2 10:50
     **
     ************************************************************************************************************************************/
    @PostMapping("/deviceTypeColumn")
    public ResponseContent deviceTypeColumn(@RequestHeader String token) {
        List<TwmpDevType> list = deviceService.getDeviceType();
        return new ResponseContent(ResponseType.SUCCESS, list);
    }


    /*************************************************************************************************************************************
     ** @Description get device list by page
     ** @param: token
     ** @param: departmentAddVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/2 10:53
     **
     ************************************************************************************************************************************/
    @PostMapping("/deviceList")
    public ResponseContent deviceList(@RequestHeader String token, @Valid @RequestBody DevicePageQueryVO deviceQueryVO) {
        PagedItemsVO<DeviceListVO> list = deviceService.getDeviceByPage(token, deviceQueryVO);
        return new ResponseContent(ResponseType.SUCCESS, list);
    }

    /*************************************************************************************************************************************
     ** @Description get device detail
     ** @param: token
     ** @param: deviceId
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/3 16:33
     **
     ************************************************************************************************************************************/
    @PostMapping("/deviceDetail/{deviceId}")
    public ResponseContent deviceDetail(@RequestHeader String token, @PathVariable Long deviceId) {
        TwmpDevDevice twmpDevDevice = deviceService.getDeviceById(deviceId);
        return new ResponseContent(ResponseType.SUCCESS, twmpDevDevice);
    }

    /*************************************************************************************************************************************
     ** @Description insert/update device
     ** @param: token
     ** @param: deviceUpdateVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/2 11:43
     **
     ************************************************************************************************************************************/
    @PostMapping("/deviceData")
    public ResponseContent deviceData(@RequestHeader String token, @Valid @RequestBody DeviceUpdateVO deviceUpdateVO) {
        deviceService.updateDevice(token, deviceUpdateVO);
        return new ResponseContent(ResponseType.SUCCESS);
    }

    /*************************************************************************************************************************************
     ** @Description delete device
     ** @param: token
     ** @param: deviceId
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/2 11:53
     **
     ************************************************************************************************************************************/
    @PostMapping("/deviceDel/{deviceId}")
    public ResponseContent deviceDel(@RequestHeader String token, @PathVariable Long deviceId) {
        deviceService.deleteDevice(token, deviceId);
        return new ResponseContent(ResponseType.SUCCESS);
    }

    /*************************************************************************************************************************************
     ** @Description get device by device number
     ** @param: token
     ** @param: deviceId
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/3 11:51
     **
     ************************************************************************************************************************************/
    @PostMapping("/getDevice/{deviceNumber}")
    public ResponseContent getDevice(@RequestHeader String token, @PathVariable String deviceNumber) {
        List<TwmpDevDevice> list = deviceService.queryDeviceByDeviceNumber(deviceNumber);
        return new ResponseContent(ResponseType.SUCCESS, list);
    }

    /*************************************************************************************************************************************
     ** @Description update device status
     ** @param: token
     ** @param: deviceNumber
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/3 11:54
     **
     ************************************************************************************************************************************/
    @PostMapping("/updateDeviceStatus")
    public ResponseContent updateDeviceStatus(@RequestHeader String token, @RequestBody DeviceStatusUpdateVO deviceStatusUpdateVO) {
        deviceService.updateDeviceStatus(token, deviceStatusUpdateVO);
        return new ResponseContent(ResponseType.SUCCESS);
    }


    /*************************************************************************************************************************************
     ** @Description get device lifecycle
     ** @param: token
     ** @param: deviceId
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/3 14:44
     **
     ************************************************************************************************************************************/
    @PostMapping("/deviceLifeCycle/{deviceId}")
    public ResponseContent deviceLifeCycle(@RequestHeader String token, @PathVariable Long deviceId) {
        List<TwmpDevLifecycle> list = deviceService.getDeviceLifecycle(token, deviceId);
        return new ResponseContent(ResponseType.SUCCESS, list);
    }



    /*************************************************************************************************************************************
     ** @Description  download device template.
     ** @param: token
     ** @param: response
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/4 14:02
     **
     ************************************************************************************************************************************/
    @PostMapping("/templateDownload/{typeId}")
    public void templateDownload(@RequestHeader String token, @PathVariable Long typeId, HttpServletResponse response) throws IOException {
        deviceService.downloadDeviceTemplate(token, typeId, response);
    }


    /*************************************************************************************************************************************
     ** @Description export device list
     ** @param: token
     ** @param: deviceQueryVO
     ** @param: response
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/8 9:34
     **
     ************************************************************************************************************************************/
    @PostMapping("/deviceExport")
    public void deviceExport(@RequestHeader String token, @RequestBody DeviceQueryVO deviceQueryVO, HttpServletResponse response) throws IOException {
        deviceService.exportDevice(token, deviceQueryVO, response);
    }

    /*************************************************************************************************************************************
     ** @Description import device info
     ** @param: token
     ** @param: typeId
     ** @param: file
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/10 14:00
     **
     ************************************************************************************************************************************/
    @PostMapping("/deviceImport/{typeId}")
    public ResponseContent deviceImport(@RequestHeader String token, @PathVariable Long typeId, @RequestParam("file") MultipartFile file) throws IOException {
        String message = deviceService.importDevice(token, typeId, file);

        return new ResponseContent(ResponseType.SUCCESS, message);
    }



}
