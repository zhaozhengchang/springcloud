package com.ceiec.twmp.tmp.vo.device.result;

/**
 * @author Ding
 * @version V1.0
 * @Description: attribute extend
 * @create 2019-04-02 9:40
 **/
public class DeviceAttributeExtend {

    private String attributeKey;

    private String attributeName;

    private int maxLength;

    private int minLength;

    private byte show;

    private Integer order;

    private String attributeValue;

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public byte getShow() {
        return show;
    }

    public void setShow(byte show) {
        this.show = show;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
