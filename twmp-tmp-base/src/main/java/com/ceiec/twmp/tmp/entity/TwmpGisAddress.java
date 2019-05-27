package com.ceiec.twmp.tmp.entity;

import javax.persistence.*;

@Table(name = "twmp_gis_address")
public class TwmpGisAddress {
    @Id
    @Column(name = "address_id")
    private Long addressId;

    /**
     * 名称
     */
    @Column(name = "address_name")
    private String addressName;

    /**
     * 地址经纬度，格式：经度，纬度11.861,-15.79986
     */
    private String point;

    /**
     * 删除标志(1未删除0删除)
     */
    private Byte deleted;

    /**
     * @return address_id
     */
    public Long getAddressId() {
        return addressId;
    }

    /**
     * @param addressId
     */
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    /**
     * 获取名称
     *
     * @return address_name - 名称
     */
    public String getAddressName() {
        return addressName;
    }

    /**
     * 设置名称
     *
     * @param addressName 名称
     */
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    /**
     * 获取地址经纬度，格式：经度，纬度11.861,-15.79986
     *
     * @return point - 地址经纬度，格式：经度，纬度11.861,-15.79986
     */
    public String getPoint() {
        return point;
    }

    /**
     * 设置地址经纬度，格式：经度，纬度11.861,-15.79986
     *
     * @param point 地址经纬度，格式：经度，纬度11.861,-15.79986
     */
    public void setPoint(String point) {
        this.point = point;
    }

    /**
     * 获取删除标志(1未删除0删除)
     *
     * @return deleted - 删除标志(1未删除0删除)
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标志(1未删除0删除)
     *
     * @param deleted 删除标志(1未删除0删除)
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}