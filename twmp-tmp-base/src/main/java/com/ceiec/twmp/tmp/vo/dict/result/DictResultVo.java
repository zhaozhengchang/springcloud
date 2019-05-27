package com.ceiec.twmp.tmp.vo.dict.result;

import java.io.Serializable;

/**
 * @author Ding
 * @version V1.0
 * @Description: vo for dict result
 * @create 2019-02-28 15:00
 **/
public class DictResultVo implements Serializable {

    private static final long serialVersionUID = 7985322981484631339L;


    public DictResultVo() {
    }

    private Long dictId;
    /**dicType**/
    private String dictType;
    /**dicName**/
    private String dictName;
    /**dicNameCode**/
    private String dictNameCode;
    /**dicValue**/
    private Byte dictValue;
    /**comment**/
    private String comment;

    private String parentType;

    private byte parentValue;

    private byte changeEnable;

    public byte getChangeEnable() {
        return changeEnable;
    }

    public void setChangeEnable(byte changeEnable) {
        this.changeEnable = changeEnable;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public byte getParentValue() {
        return parentValue;
    }

    public void setParentValue(byte parentValue) {
        this.parentValue = parentValue;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictNameCode() {
        return dictNameCode;
    }

    public void setDictNameCode(String dictNameCode) {
        this.dictNameCode = dictNameCode;
    }

    public Byte getDictValue() {
        return dictValue;
    }

    public void setDictValue(Byte dictValue) {
        this.dictValue = dictValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
