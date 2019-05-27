package com.ceiec.twmp.tmp.vo.ope.query;

/**
 * @author Ding
 * @version V1.0
 * @Description: for query ope finish detail
 * @create 2019-04-15 15:07
 **/
public class OpeFinishDetailQueryVO {

    private Long opeId;

    private Byte opeType;

    public Long getOpeId() {
        return opeId;
    }

    public void setOpeId(Long opeId) {
        this.opeId = opeId;
    }

    public Byte getOpeType() {
        return opeType;
    }

    public void setOpeType(Byte opeType) {
        this.opeType = opeType;
    }
}
