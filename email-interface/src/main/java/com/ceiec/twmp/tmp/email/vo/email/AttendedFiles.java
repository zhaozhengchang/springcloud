package com.ceiec.twmp.tmp.email.vo.email;

/**
 * The type Attended files.
 */
public class AttendedFiles{
    /** 插入文件保存到附件中的文件名，包括文件的格式后缀*/
    private String attachmentFilename;

    /** 插入文件保存到附件中的文件字节*/
    private byte[] fileBytes;

    /**
     * Instantiates a new Attended files.
     */
    public AttendedFiles(){

    }

    /**
     * Gets attachment filename.
     *
     * @return the attachment filename
     */
    public String getAttachmentFilename() {
        return attachmentFilename;
    }

    /**
     * Sets attachment filename.
     *
     * @param attachmentFilename the attachment filename
     */
    public void setAttachmentFilename(String attachmentFilename) {
        this.attachmentFilename = attachmentFilename;
    }


    /**
     * Get file bytes byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getFileBytes() {
        return fileBytes;
    }

    /**
     * Sets file bytes.
     *
     * @param fileBytes the file bytes
     */
    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }
}