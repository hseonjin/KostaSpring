package com.kosta.api.dto;

public class UserInfo {
    private Long id;
    private String nickname;
    private String email;
    private String profile_image;

    public UserInfo(Long id, String nickname, String email, String profile_image) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.profile_image = profile_image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}