package com.example.xunwu.model;

import java.io.Serializable;

public class SupportAddress implements Serializable {
    private Integer id;

    private String belongTo;

    private String enName;

    private String cnName;

    private String level;

    private Double baiduMapLng;

    private Double baiduMapLat;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getBaiduMapLng() {
        return baiduMapLng;
    }

    public void setBaiduMapLng(Double baiduMapLng) {
        this.baiduMapLng = baiduMapLng;
    }

    public Double getBaiduMapLat() {
        return baiduMapLat;
    }

    public void setBaiduMapLat(Double baiduMapLat) {
        this.baiduMapLat = baiduMapLat;
    }
}