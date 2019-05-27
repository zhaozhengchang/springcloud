package com.ceiec.twmp.tmp.entity;

import javax.persistence.*;

@Table(name = "twmp_monitor_task_fence_space_ef")
public class TwmpMonitorTaskFenceSpaceEf {
    @Id
    @Column(name = "shape_id")
    private Long shapeId;

    /**
     * 监控任务id
     */
    @Column(name = "fence_id")
    private Long fenceId;

    /**
     * 围栏范围, xy之间逗号分隔，坐标时间空格分隔。用如下形式表示x,y x,y x,y x,y…….
     */
    private String space;

    /**
     * @return shape_id
     */
    public Long getShapeId() {
        return shapeId;
    }

    /**
     * @param shapeId
     */
    public void setShapeId(Long shapeId) {
        this.shapeId = shapeId;
    }

    /**
     * 获取监控任务id
     *
     * @return fence_id - 监控任务id
     */
    public Long getFenceId() {
        return fenceId;
    }

    /**
     * 设置监控任务id
     *
     * @param fenceId 监控任务id
     */
    public void setFenceId(Long fenceId) {
        this.fenceId = fenceId;
    }

    /**
     * 获取围栏范围, xy之间逗号分隔，坐标时间空格分隔。用如下形式表示x,y x,y x,y x,y…….
     *
     * @return space - 围栏范围, xy之间逗号分隔，坐标时间空格分隔。用如下形式表示x,y x,y x,y x,y…….
     */
    public String getSpace() {
        return space;
    }

    /**
     * 设置围栏范围, xy之间逗号分隔，坐标时间空格分隔。用如下形式表示x,y x,y x,y x,y…….
     *
     * @param space 围栏范围, xy之间逗号分隔，坐标时间空格分隔。用如下形式表示x,y x,y x,y x,y…….
     */
    public void setSpace(String space) {
        this.space = space;
    }
}