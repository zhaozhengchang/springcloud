package com.ceiec.twmp.tmp.vo.paperwork.add;

/**
 * @author zhaoZhengchang
 * @create_date 2019-03-11 17:29
 * Description:
 **/

public class PaperworkAddVO {

    private Long paperworkId;

    private String paperworkName;

    private Long fileId;

    private String   comment;

    public Long getPaperworkId() {
        return paperworkId;
    }

    public void setPaperworkId(Long paperworkId) {
        this.paperworkId = paperworkId;
    }

    public String getPaperworkName() {
        return paperworkName;
    }

    public void setPaperworkName(String paperworkName) {
        this.paperworkName = paperworkName;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
