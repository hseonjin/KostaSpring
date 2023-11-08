package com.kosta.api.service;

import com.kosta.api.dao.UserInfo;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class KakaoService {
    public String getToken(String code) throws Exception { // get 메서드를 통해 받아온 인가 코드 사용
        // 요청 파라미터
        String client_id = "{client_id}";
        String redirect_uri = "http://localhost:8088/kakaologin";

        // 토큰 요청 URL 생성
        StringBuilder urlBuilder = new StringBuilder("https://kauth.kakao.com/oauth/token"); // 기본 정보
        urlBuilder.append("?grant_type=authorization_code");
        urlBuilder.append("&client_id=" + client_id);
        urlBuilder.append("&redirect_uri=" + redirect_uri);
        urlBuilder.append("&code=" + code);

        // 위에서 생성한 토큰 요청 URL을 기반으로 HTTP POST 요청을 보낼 HttpURLConnection 생성
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST"); // 메서드 요청 방식 설정
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 메서드 요청 데이터 타입 설정

        // BufferedReader를 생성하여 HTTP 응답 코드를 읽고, 정상 응답인지 에러 응답인지 판별
        BufferedReader br;
        int resCode = conn.getResponseCode();
        if(resCode >= 200 && resCode <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        // 응답 내용을 읽어와서 문자열로 저장
        // 앞서 생성한 br에서 한 줄씩 응답 데이터를 모두 읽어 res에 추가하고, 읽기가 끝나면 br을 닫음
        // -> 결과적으로 res에 전체 응답 데이터가 문자열로 저장되었으므로 파싱할 때 사용한다
        String inputLine;
        StringBuilder res = new StringBuilder();
        while ((inputLine = br.readLine()) != null) {
            res.append(inputLine);
        }
        br.close();
        conn.disconnect();

        // 응답 내용 확인
        System.out.println(res.toString());

        // 만약 HTTP 응답 코드가 200이 아닌 경우(에러인 경우) 예외 발생
        if (resCode != 200) {
            throw new Exception(res.toString());
        }

        // AccessToken을 파싱하여 반환
        JSONParser parser = new JSONParser();
        JSONObject tokenObj = (JSONObject) parser.parse(res.toString());
        String token = (String) tokenObj.get("access_token");

        return token;
    }

    public UserInfo getUserInfo(String token) throws Exception {
        // 사용자 정보 가져오기 요청 URL 생성
        StringBuilder urlBuilder = new StringBuilder("https://kapi.kakao.com/v2/user/me");

        // HTTP POST 요청을 보낼 HttpURLConnection 생성
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + token);

        // BufferedReader를 생성하여 HTTP 응답 코드를 읽고, 정상 응답인지 에러 응답인지 판별
        BufferedReader br;
        int resCode = conn.getResponseCode();
        if(resCode >= 200 && resCode <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        // 응답 내용을 읽어와서 문자열로 저장
        String inputLine;
        StringBuilder res = new StringBuilder();
        while((inputLine = br.readLine()) != null) {
            res.append(inputLine);
        }
        br.close();
        conn.connect();

        System.out.println(res.toString()); // 응답 내용 확인

        // 만약 HTTP 응답 코드가 200이 아닌 경우(에러인 경우) 예외 발생
        if (resCode != 200) {
            throw new Exception(res.toString());
        }

        // 필요한 사용자 정보 파싱하여 저장
        JSONParser parser = new JSONParser();
        JSONObject user = (JSONObject) parser.parse(res.toString());
        JSONObject properties = (JSONObject) user.get("properties");
        JSONObject kakaoAccount = (JSONObject)user.get("kakao_account");
        String id = (Long) user.get("id")+"";
        String nickname = (String) properties.get("nickname");
        String profileImage = (String) properties.get("profile_image");
        String email = (String) kakaoAccount.get("email");

        UserInfo userInfo = new UserInfo(id, nickname, email, profileImage);
        return userInfo;
    }

    public UserInfo kakaoLogin(String code) throws Exception {
        String token = getToken(code);
        UserInfo userInfo = getUserInfo(token);
        return userInfo;
    }
}
