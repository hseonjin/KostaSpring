package com.kosta.api.dto;

public class UserInfo {
    private String id;
    private String password;
    private String nickname;
    private String email;
    private String profile_image;
    private String address;


    public UserInfo(String id, String password, String nickname, String email, String profile_image, String address) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.profile_image = profile_image;
        this.address = address;
    }

    public UserInfo(String id, String nickname, String email, String profile_image) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.profile_image = profile_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
