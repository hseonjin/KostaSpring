package com.kosta.api.dto;

public class ElecCar {
    private String addr; // 충전소 주소
    private String csNm; // 충전소 명칭
    private String lat; // 위도
    private String longi; // 경도
    private String cpNm; // 충전기명칭

    // 생성자
    public ElecCar(String addr, String csNm, String lat, String longi, String cpNm) {
        this.addr = addr;
        this.csNm = csNm;
        this.lat = lat;
        this.longi = longi;
        this.cpNm = cpNm;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCsNm() {
        return csNm;
    }

    public void setCsNm(String csNm) {
        this.csNm = csNm;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getCpNm() {
        return cpNm;
    }

    public void setCpNm(String cpNm) {
        this.cpNm = cpNm;
    }
}
