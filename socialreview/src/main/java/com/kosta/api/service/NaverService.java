package com.kosta.api.service;

import com.kosta.api.dao.Token;
import com.kosta.api.dao.UserInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class NaverService {
    public Token getToken(String code, String state) throws Exception {
        String client_id = "{client_id}";
        String client_secret = "{client_secret}";
        String redirect_uri = URLEncoder.encode("http://localhost:8088/naverlogin", "UTF-8");

        StringBuilder urlBuilder = new StringBuilder("https://nid.naver.com/oauth2.0/token");
        urlBuilder.append("?grant_type=authorization_code");
        urlBuilder.append("&client_id=" + client_id);
        urlBuilder.append("&client_secret=" + client_secret);
        urlBuilder.append("&redirect_uri=" + redirect_uri);
        urlBuilder.append("&code=" + code);
        urlBuilder.append("&state=" + state);

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br;
        int resCode = conn.getResponseCode();
        if(resCode >= 200 && resCode <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String inputLine;
        StringBuffer res = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            res.append(inputLine);
        }
        br.close();
        conn.disconnect();

        System.out.println(res.toString());

        if (resCode != 200) {
            throw new Exception(res.toString());
        }

        Token token = new Token();
        JSONParser parser = new JSONParser();
        JSONObject tokenObj =  (JSONObject)parser.parse(res.toString());
        token.setAccessToken((String)tokenObj.get("access_token"));
        token.setTokenType((String)tokenObj.get("token_type"));

        return token;
    }

    public UserInfo getUserInfo(Token token) throws Exception {
        URL url = new URL("https://openapi.naver.com/v1/nid/me");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", token.getTokenType()+ " "+token.getAccessToken());

        BufferedReader br;
        int resultCode = conn.getResponseCode();

        if(resultCode>=200 && resultCode<=300) { //정상
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else { //에러
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder resBuilder = new StringBuilder();
        String line;
        while((line=br.readLine())!=null) {
            resBuilder.append(line);
        }
        br.close();
        conn.disconnect();

        System.out.println(resBuilder.toString());

        JSONParser parser = new JSONParser();
        JSONObject resObj = (JSONObject)parser.parse(resBuilder.toString());
        JSONObject user = (JSONObject)resObj.get("response");
        String id = (String) user.get("id");
        String nickname = (String) user.get("nickname");
        String profileImage = (String) user.get("profile_image");
        String email = (String) user.get("email");

        UserInfo userInfo = new UserInfo(id, nickname, email, profileImage);
        return userInfo;
    }

    public UserInfo naverLogin(String code, String state) throws Exception {
        Token token = getToken(code, state);
        UserInfo userInfo = getUserInfo(token);
        return userInfo;
    }
}
