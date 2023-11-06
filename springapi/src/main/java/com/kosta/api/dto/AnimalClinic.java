package com.kosta.api.dto;

public class AnimalClinic {
    private String trdStateNm; // 영업상태명
    private String siteTel; // 전화번호
    private String rdnwhlAddr; // 도로명주소
    private String bplcNm; // 사업장명
    private String x; // 좌표정보
    private String y; // 좌표정보

    // 생성자
    public AnimalClinic(String trdStateNm, String siteTel, String rdnwhlAddr, String bplcNm, String x, String y) {
        this.trdStateNm = trdStateNm;
        this.siteTel = siteTel;
        this.rdnwhlAddr = rdnwhlAddr;
        this.bplcNm = bplcNm;
        this.x = x;
        this.y = y;
    }

    public String getTrdStateNm() {
        return trdStateNm;
    }

    public void setTrdStateNm(String trdStateNm) {
        this.trdStateNm = trdStateNm;
    }

    public String getSiteTel() {
        return siteTel;
    }

    public void setSiteTel(String siteTel) {
        this.siteTel = siteTel;
    }

    public String getRdnwhlAddr() {
        return rdnwhlAddr;
    }

    public void setRdnwhlAddr(String rdnwhlAddr) {
        this.rdnwhlAddr = rdnwhlAddr;
    }

    public String getBplcNm() {
        return bplcNm;
    }

    public void setBplcNm(String bplcNm) {
        this.bplcNm = bplcNm;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
