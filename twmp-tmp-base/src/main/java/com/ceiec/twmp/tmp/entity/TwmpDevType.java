package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "twmp_dev_type")
public class TwmpDevType extends BaseEntity {
    @Id
    @Column(name = "type_id")
    private Long typeId;

    /**
     * 类型名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 安装示意图url
     */
    @Column(name = "photo_url")
    private String photoUrl;

    /**
     * 缩略图
     */
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    /**
     * 设备类型备注
     */
    private String comment;

    /**
     * 安装校验服务串，已“，”串起来
     */
    @Column(name = "check_service_ids")
    private String checkServiceIds;

    /**
     * 扩展属性
     */
    @Column(name = "attribute_extend")
    private String attributeExtend;

    /**
     * 文件上传校验规则（一个字段json格式）
     */
    @Column(name = "file_rule")
    private String fileRule;

    /**
     * 自检属性
     */
    @Column(name = "function_extend")
    private String functionExtend;

    /**
     * @return type_id
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * @param typeId
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取类型名称
     *
     * @return type_name - 类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置类型名称
     *
     * @param typeName 类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取安装示意图url
     *
     * @return photo_url - 安装示意图url
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * 设置安装示意图url
     *
     * @param photoUrl 安装示意图url
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * 获取缩略图
     *
     * @return thumbnail_url - 缩略图
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * 设置缩略图
     *
     * @param thumbnailUrl 缩略图
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    /**
     * 获取设备类型备注
     *
     * @return comment - 设备类型备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置设备类型备注
     *
     * @param comment 设备类型备注
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取安装校验服务串，已“，”串起来
     *
     * @return check_service_ids - 安装校验服务串，已“，”串起来
     */
    public String getCheckServiceIds() {
        return checkServiceIds;
    }

    /**
     * 设置安装校验服务串，已“，”串起来
     *
     * @param checkServiceIds 安装校验服务串，已“，”串起来
     */
    public void setCheckServiceIds(String checkServiceIds) {
        this.checkServiceIds = checkServiceIds;
    }

    /**
     * 获取扩展属性
     *
     * @return attribute_extend - 扩展属性
     */
    public String getAttributeExtend() {
        return attributeExtend;
    }

    /**
     * 设置扩展属性
     *
     * @param attributeExtend 扩展属性
     */
    public void setAttributeExtend(String attributeExtend) {
        this.attributeExtend = attributeExtend;
    }

    /**
     * 获取文件上传校验规则（一个字段json格式）
     *
     * @return file_rule - 文件上传校验规则（一个字段json格式）
     */
    public String getFileRule() {
        return fileRule;
    }

    /**
     * 设置文件上传校验规则（一个字段json格式）
     *
     * @param fileRule 文件上传校验规则（一个字段json格式）
     */
    public void setFileRule(String fileRule) {
        this.fileRule = fileRule;
    }

    /**
     * 获取自检属性
     *
     * @return function_extend - 自检属性
     */
    public String getFunctionExtend() {
        return functionExtend;
    }

    /**
     * 设置自检属性
     *
     * @param functionExtend 自检属性
     */
    public void setFunctionExtend(String functionExtend) {
        this.functionExtend = functionExtend;
    }
}