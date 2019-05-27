package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "twmp_sys_dict")
public class TwmpSysDict extends BaseEntity {
    @Id
    @Column(name = "dict_id")
    private Long dictId;

    /**
     * dic_type
     */
    @Column(name = "dict_type")
    private String dictType;

    /**
     * 字典名称
     */
    @Column(name = "dict_name")
    private String dictName;

    /**
     * 字典名称国际化（如果有此值，则使用此值）
     */
    @Column(name = "dict_name_code")
    private String dictNameCode;

    /**
     * 字典值(-127-127)
     */
    @Column(name = "dict_value")
    private Byte dictValue;

    /**
     * 字典备注
     */
    private String comment;

    /**
     * 1可以变更，2不可以变更
     */
    @Column(name = "change_enable")
    private Byte changeEnable;

    @Column(name = "dict_parent_type")
    private String dictParentType;

    @Column(name = "dict_parent_value")
    private Byte dictParentValue;

    /**
     * @return dic_id
     */
    public Long getDictId() {
        return dictId;
    }

    /**
     * @param dictId
     */
    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    /**
     * 获取dic_type
     *
     * @return dic_type - dic_type
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * 设置dict_type
     *
     * @param dictType dic_type
     */
    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    /**
     * 获取字典名称
     *
     * @return dic_name - 字典名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 设置字典名称
     *
     * @param dictName 字典名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    /**
     * 获取字典名称国际化（如果有此值，则使用此值）
     *
     * @return dic_name_code - 字典名称国际化（如果有此值，则使用此值）
     */
    public String getDictNameCode() {
        return dictNameCode;
    }

    /**
     * 设置字典名称国际化（如果有此值，则使用此值）
     *
     * @param dictNameCode 字典名称国际化（如果有此值，则使用此值）
     */
    public void setDictNameCode(String dictNameCode) {
        this.dictNameCode = dictNameCode;
    }

    /**
     * 获取字典值(-127-127)
     *
     * @return dic_value - 字典值(-127-127)
     */
    public Byte getDictValue() {
        return dictValue;
    }

    /**
     * 设置字典值(-127-127)
     *
     * @param dictValue 字典值(-127-127)
     */
    public void setDictValue(Byte dictValue) {
        this.dictValue = dictValue;
    }

    /**
     * 获取字典备注
     *
     * @return comment - 字典备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置字典备注
     *
     * @param comment 字典备注
     */
    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getDictParentType() {
        return dictParentType;
    }

    public void setDictParentType(String dictParentType) {
        this.dictParentType = dictParentType;
    }

    public Byte getDictParentValue() {
        return dictParentValue;
    }

    public void setDictParentValue(Byte dictParentValue) {
        this.dictParentValue = dictParentValue;
    }

    /**
     * 获取1可以变更，2不可以变更
     *
     * @return change_enable - 1可以变更，2不可以变更
     */
    public Byte getChangeEnable() {
        return changeEnable;
    }

    /**
     * 设置1可以变更，2不可以变更
     *
     * @param changeEnable 1可以变更，2不可以变更
     */
    public void setChangeEnable(Byte changeEnable) {
        this.changeEnable = changeEnable;
    }

}