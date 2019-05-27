package com.ceiec.twmp.tmp.vo.monitor.result;

import com.ceiec.twmp.tmp.vo.monitor.add.FenceAreaGatherVO;

import java.io.Serializable;
import java.util.List;

/**
 * @version V1.0
 * @title: FenceAreaGatherResultVO </br>
 * @createDate: 2019/3/18 11:41 </br>
 * @author: shihsh </br>
 * @description: 围栏区域结果VO </br>
 **/


public class FenceAreaGatherResultVO implements Serializable {


    private static final long serialVersionUID = 3261008631378319444L;

    /**
     * 围栏区域Id
     */
    private Long shapeId;

    /**
     * 围栏坐标
     */
    private String space;

    /**
     * 围栏区域坐标
     */
    private List<FenceAreaGatherResultVO.FenceCoordinateResultVO> fenceCoordinate;

    public Long getShapeId() {
        return shapeId;
    }

    public void setShapeId(Long shapeId) {
        this.shapeId = shapeId;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public List<FenceAreaGatherResultVO.FenceCoordinateResultVO> getFenceCoordinate() {
        return fenceCoordinate;
    }

    public void setFenceCoordinate(List<FenceAreaGatherResultVO.FenceCoordinateResultVO> fenceCoordinate) {
        this.fenceCoordinate = fenceCoordinate;
    }

    public static class FenceCoordinateResultVO implements Serializable {

        private static final long serialVersionUID = 2383874756131618220L;
        /**
         * 围栏点经度
         */
        private String longtitude;

        /**
         * 围栏点纬度
         */
        private String latitude;


        public String getLongtitude() {
            return longtitude;
        }

        public void setLongtitude(String longtitude) {
            this.longtitude = longtitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }
}
