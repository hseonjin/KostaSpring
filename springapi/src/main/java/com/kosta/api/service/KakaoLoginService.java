package com.kosta.api.service;

import com.kosta.api.dto.UserInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class KakaoLoginService {

    public UserInfo kakaoLogin(String code) throws Exception {
        String token = getAccessToken(code);
        System.out.println(token);
        UserInfo userInfo = getUserInfo(token);
        return userInfo;
    }

    public String getAccessToken(String code) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("https://kauth.kakao.com/oauth/token"); // kakao로 부터 토큰 요청
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("context-type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true); // 출력스트림 생성 (body 영역에 넣기 위해)

        // 파라미터 생성
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        StringBuilder param = new StringBuilder();

        param.append("grant_type=authorization_code");
//      conn.setRequestProperty("client_secret", ""); // client_secret을 설정한 경우 required`
        param.append("&client_id=c95c7f73f119e07ce4e82a038f1d7883");
        param.append("&redirect_url=http://localhost:8088/kakaologin");

        param.append("&code="+code);

        // 생성한 파라미터를 출력스트림에 쓰기 (body)에 넣기
        bw.write(param.toString());
        bw.flush();

        BufferedReader br;
        int resultCode = conn.getResponseCode();

        if(resultCode >= 200 && resultCode <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }

        StringBuilder resBuilder = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            resBuilder.append(line);
        }
        br.close();
        conn.disconnect();
        System.out.println(resBuilder.toString());

        JSONParser parser = new JSONParser(); // 파싱처리
        JSONObject tokenObj = (JSONObject)parser.parse(resBuilder.toString());
        String token = (String)tokenObj.get("access_token");
        return token;
    }

    public UserInfo getUserInfo(String token) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("https://kapi.kakao.com/v2/user/me");
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer "+token);

        BufferedReader br;
        int resultCode = conn.getResponseCode();

        if(resultCode >= 200 && resultCode <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder resBuilder = new StringBuilder();
        String line;
        while((line=br.readLine()) != null) {
            resBuilder.append(line);
        }
        br.close();
        conn.disconnect();
        System.out.println(resBuilder.toString());

        JSONParser parser = new JSONParser(); // 파싱처리
        JSONObject user = (JSONObject)parser.parse(resBuilder.toString());
        Long id = (Long)user.get("id");
        JSONObject  properties = (JSONObject)user.get("properties");
        String nickname = (String)properties.get("nickname");
        String profileImage = (String)properties.get("profile_image");

        JSONObject kakaoAccount = (JSONObject)user.get("kakao_account");
        String email = (String)kakaoAccount.get("email");

        UserInfo userInfo = new UserInfo(id, nickname, email, profileImage);
        return userInfo;
    }

}
