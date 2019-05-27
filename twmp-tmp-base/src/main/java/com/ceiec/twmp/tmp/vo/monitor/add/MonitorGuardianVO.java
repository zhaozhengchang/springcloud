package com.ceiec.twmp.tmp.vo.monitor.add;

/**
 * @version V1.0
 * @title: MonitorGuardianVO </br>
 * @createDate：2019/3/12 17:31 </br>
 * @author：shihsh </br>
 * @description: 监护成员VO </br>
 **/


public class MonitorGuardianVO {

    /**
     * 监护成员
     */
    private Long teamId;

    /**
     * 头像照片
     */
    private String photoUrl;

    private String name;

    private String phone;

    private String email;

    private String address;

    private Byte relationType;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getRelationType() {
        return relationType;
    }

    public void setRelationType(Byte relationType) {
        this.relationType = relationType;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

