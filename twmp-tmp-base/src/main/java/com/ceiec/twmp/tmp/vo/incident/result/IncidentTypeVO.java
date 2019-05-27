package com.ceiec.twmp.tmp.vo.incident.result;

import java.io.Serializable;

/**
 * @title: IncidentTypeVO </br>
 * @createDate: 2019/4/18 16:06 </br>
 * @author: shihsh  </br>
 * @description: 警情类型VO </br>
 * @version: V1.0
 **/


public class IncidentTypeVO implements Serializable {
    private static final long serialVersionUID = 8051824860570429748L;

    private String incidentTypeId;

    private String incidentTypeName;

    private String parentId;

    private String incidentTypeDescription;

    public String getIncidentTypeId() {
        return incidentTypeId;
    }

    public void setIncidentTypeId(String incidentTypeId) {
        this.incidentTypeId = incidentTypeId;
    }

    public String getIncidentTypeName() {
        return incidentTypeName;
    }

    public void setIncidentTypeName(String incidentTypeName) {
        this.incidentTypeName = incidentTypeName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIncidentTypeDescription() {
        return incidentTypeDescription;
    }

    public void setIncidentTypeDescription(String incidentTypeDescription) {
        this.incidentTypeDescription = incidentTypeDescription;
    }
}
