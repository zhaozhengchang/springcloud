package com.ceiec.twmp.tmp.vo.fence;

import com.ceiec.twmp.tmp.utils.tools.GisTools;
import com.vividsolutions.jts.io.ParseException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.operation.TransformException;

/**
 * @author Ding
 * @version V1.0
 * @Description: fence vo
 * @create 2019-03-12 9:48
 **/
public class FenceShapeVO {

    private Long fenceId;

    private Byte fenceType;

    private String space;

    private String startTime;

    private String endTime;

    private String wktPoly;

    private int distanceMeters = 0; //meters , if the distance of the gps between the fence more than the bufferDistance, generate a a fence alarm immediately

    private boolean flag = false;//switch on/off the gps drift calculation


    public FenceShapeVO() {
    }

    /*************************************************************************************************************************************
     ** @Description  should use this method if you want to generate a fenceShapeVO
     ** @param: fenceId
     ** @param: fenceType
     ** @param: space
     ** @param: startTime
     ** @param: endTime
     ** @param: distanceMeters
     ** @Return
     ** @Author Ding
     ** @Date 2019/3/22 11:21
     **
     ************************************************************************************************************************************/
    public FenceShapeVO(Long fenceId, Byte fenceType, String space, String startTime, String endTime, int distanceMeters, boolean flag) throws FactoryException, ParseException, TransformException {
        this.fenceId = fenceId;
        this.fenceType = fenceType;
        this.space = space;
        this.startTime = startTime;
        this.endTime = endTime;
        this.distanceMeters = distanceMeters;
        if(flag){
            this.wktPoly = GisTools.addLonLatDistanceBuffer(space, distanceMeters);
        }
        this.flag = flag;
    }

    public String getWktPoly() {
        return wktPoly;
    }

    public void setWktPoly(String wktPoly) {
        this.wktPoly = wktPoly;
    }

//    public int getDistanceMeters() {
//        return distanceMeters;
//    }
//
//    public void setDistanceMeters(int distanceMeters) {
//        this.distanceMeters = distanceMeters;
//    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getFenceId() {
        return fenceId;
    }

    public void setFenceId(Long fenceId) {
        this.fenceId = fenceId;
    }

    public Byte getFenceType() {
        return fenceType;
    }

    public void setFenceType(Byte fenceType) {
        this.fenceType = fenceType;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) throws FactoryException, ParseException, TransformException {
        this.space = space;
        this.wktPoly = GisTools.addLonLatDistanceBuffer(space, distanceMeters);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
