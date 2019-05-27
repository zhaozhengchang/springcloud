package com.ceiec.twmp.tmp.vo;

import java.io.Serializable;
import java.util.List;

/**
 * CreateDate：2018/9/11 <br/>
 * Author：wenliang <br/>
 * Description: the VO for save new material
 **/
public class MaterialAddVO implements Serializable {

    /** serialize number */
    private static final long serialVersionUID = 2038965812441637390L;

    /** material id */
    private String materialId;

    /** applied site */
    private Integer appliedSite;

    /** material name */
    private String materialName;

    /** material type */
    private List<Integer> materialType;

    /** word content */
    private String wordContent;

    /** media content */
    private List<String> mediaContent;

    /** material label */
    private List<String> materialLabels;

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public Integer getAppliedSite() {
        return appliedSite;
    }

    public void setAppliedSite(Integer appliedSite) {
        this.appliedSite = appliedSite;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public List<Integer> getMaterialType() {
        return materialType;
    }

    public void setMaterialType(List<Integer> materialType) {
        this.materialType = materialType;
    }

    public String getWordContent() {
        return wordContent;
    }

    public void setWordContent(String wordContent) {
        this.wordContent = wordContent;
    }

    public List<String> getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(List<String> mediaContent) {
        this.mediaContent = mediaContent;
    }

    public List<String> getMaterialLabels() {
        return materialLabels;
    }

    public void setMaterialLabels(List<String> materialLabels) {
        this.materialLabels = materialLabels;
    }

    @Override
    public String toString() {
        return "MaterialAddVO{" +
                "materialId='" + materialId + '\'' +
                ", appliedSite=" + appliedSite +
                ", materialName='" + materialName + '\'' +
                ", materialType=" + materialType +
                ", wordContent='" + wordContent + '\'' +
                ", mediaContent=" + mediaContent +
                ", materialLabels=" + materialLabels +
                '}';
    }
}