package com.ceiec.twmp.tmp.vo.dict.save;

/**
 * @author Ding
 * @version V1.0
 * @Description: vo for dict update or update
 * @create 2019-02-28 14:59
 **/
public class DictSaveVo {

    private Long dictId;

    private String dictType;

    private String dictName;

    private String dictNameCode;

    private Byte dictValue;

    private String comment;

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
