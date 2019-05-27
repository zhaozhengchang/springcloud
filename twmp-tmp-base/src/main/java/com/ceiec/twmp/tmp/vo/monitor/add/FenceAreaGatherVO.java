package com.ceiec.twmp.tmp.vo.monitor.add;

import java.util.List;

/**
 * @version V1.0
 * @title: FenceAreaGatherVO </br>
 * @createDate：2019/3/13 11:43 </br>
 * @author：shihsh </br>
 * @description: 围栏区域VO </br>
 **/


public class FenceAreaGatherVO {

    /**
     * 围栏区域Id
     */
    private Long spaceId;

    /**
     * 围栏区域坐标
     */
    private List<FenceCoordinateVO> fenceCoordinate;

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public List<FenceCoordinateVO> getFenceCoordinate() {
        return fenceCoordinate;
    }

    public void setFenceCoordinate(List<FenceCoordinateVO> fenceCoordinate) {
        this.fenceCoordinate = fenceCoordinate;
    }

    public static class FenceCoordinateVO {

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

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}