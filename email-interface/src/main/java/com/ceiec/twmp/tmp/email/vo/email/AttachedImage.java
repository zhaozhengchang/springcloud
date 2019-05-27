package com.ceiec.twmp.tmp.email.vo.email;

import org.springframework.core.io.InputStreamSource;

/**
 * The type Attached image.
 */
public class AttachedImage{
    /** Html内容中的 // cid为固定写法，imageId指定一个标识*/
    private String contentId ;
    /** 插入文件保存到正文中的图片字节*/
    private byte[] imageBytes;
    /** 文件的格式--比如jpg，jpe，jpeg等后缀文件的文件格式都是 mage/jpeg */
    private String contentType ;

    /**
     * Instantiates a new Attached image.
     */
    public AttachedImage(){

    }

    /**
     * Gets content id.
     *
     * @return the content id
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * Sets content id.
     *
     * @param contentId the content id
     */
    public void setContentId(String contentId) {
            this.contentId = contentId;
        }

    /**
     * Get image bytes byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getImageBytes() {
        return imageBytes;
    }

    /**
     * Sets image bytes.
     *
     * @param imageBytes the image bytes
     */
    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }

    /**
     * Gets content type.
     *
     * @return the content type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets content type.
     *
     * @param contentType the content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
