package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpDevDevice;
import com.ceiec.twmp.tmp.vo.device.query.DevicePageQueryVO;
import com.ceiec.twmp.tmp.vo.device.query.DeviceQueryVO;
import com.ceiec.twmp.tmp.vo.device.result.DeviceListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Component
public interface TwmpDevDeviceMapper extends Mapper<TwmpDevDevice> {
    /**
     * 根据组织机构id查询设备数量
     * @param departmentIdlist
     * @return
     */
    int deviceCountByDepartmentId(List<Long> departmentIdlist);

    List<DeviceListVO> getDeviceByPage(DevicePageQueryVO deviceQueryVO);

    Long countDevice(DevicePageQueryVO devicePageQueryVO);

    List<DeviceListVO> getDevice(DeviceQueryVO deviceQueryVO);

    List<TwmpDevDevice> queryDeviceByDeviceNumber(@Param("deviceNumber")String deviceNumber);

    Long countDeviceByDeviceNumber(@Param("deviceNumber")String deviceNumber, @Param("deviceId")Long deviceId);

    List<TwmpDevDevice> getAllDevice();

    void addBatch(List<TwmpDevDevice> list);
}