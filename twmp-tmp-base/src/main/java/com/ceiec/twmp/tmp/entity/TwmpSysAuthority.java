package com.ceiec.twmp.tmp.entity;

import javax.persistence.*;

@Table(name = "twmp_sys_authority")
public class TwmpSysAuthority {
    @Id
    @Column(name = "authority_id")
    private Long authorityId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 权限类型 1菜单 2按钮
     */
    @Column(name = "authority_type")
    private Byte authorityType;

    /**
     * 权限名称（用于国际化）
     */
    @Column(name = "authority_name")
    private String authorityName;

    /**
     * 权限中文名
     */
    @Column(name = "zh_name")
    private String zhName;

    /**
     * 权限代码
     */
    private String node;

    /**
     * 父id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 权限的URL
     */
    @Column(name = "authority_url")
    private String authorityUrl;

    /**
     * 相对的前端代码路径
     */
    @Column(name = "authority_component")
    private String authorityComponent;

    /**
     * 权限图标
     */
    private String icon;

    /**
     * 备注
     */
    private String comment;

    private Byte deleted;

    private Byte show;

    public Byte getShow() {
        return show;
    }

    public void setShow(Byte show) {
        this.show = show;
    }

    /**
     * @return authority_id
     */
    public Long getAuthorityId() {
        return authorityId;
    }

    /**
     * @param authorityId
     */
    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取权限类型 1菜单 2按钮
     *
     * @return authority_type - 权限类型 1菜单 2按钮
     */
    public Byte getAuthorityType() {
        return authorityType;
    }

    /**
     * 设置权限类型 1菜单 2按钮
     *
     * @param authorityType 权限类型 1菜单 2按钮
     */
    public void setAuthorityType(Byte authorityType) {
        this.authorityType = authorityType;
    }

    /**
     * 获取权限名称（用于国际化）
     *
     * @return authority_name - 权限名称（用于国际化）
     */
    public String getAuthorityName() {
        return authorityName;
    }

    /**
     * 设置权限名称（用于国际化）
     *
     * @param authorityName 权限名称（用于国际化）
     */
    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    /**
     * 获取权限中文名
     *
     * @return zh_name - 权限中文名
     */
    public String getZhName() {
        return zhName;
    }

    /**
     * 设置权限中文名
     *
     * @param zhName 权限中文名
     */
    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    /**
     * 获取权限代码
     *
     * @return node - 权限代码
     */
    public String getNode() {
        return node;
    }

    /**
     * 设置权限代码
     *
     * @param node 权限代码
     */
    public void setNode(String node) {
        this.node = node;
    }

    /**
     * 获取父id
     *
     * @return parent_id - 父id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父id
     *
     * @param parentId 父id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取权限的URL
     *
     * @return authority_url - 权限的URL
     */
    public String getAuthorityUrl() {
        return authorityUrl;
    }

    /**
     * 设置权限的URL
     *
     * @param authorityUrl 权限的URL
     */
    public void setAuthorityUrl(String authorityUrl) {
        this.authorityUrl = authorityUrl;
    }

    /**
     * 获取权限图标
     *
     * @return icon - 权限图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置权限图标
     *
     * @param icon 权限图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取备注
     *
     * @return comment - 备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置备注
     *
     * @param comment 备注
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return deleted
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }


    public String getAuthorityComponent() {
        return authorityComponent;
    }

    public void setAuthorityComponent(String authorityComponent) {
        this.authorityComponent = authorityComponent;
    }
}