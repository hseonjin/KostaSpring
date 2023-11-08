package com.kosta.api.service;

import com.kosta.api.dto.AnimalClinic;
import com.kosta.api.dto.ElecCar;
import com.kosta.api.dto.PageInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElecCarApiService {
    public List<ElecCar> elecCarList(PageInfo pageInfo) throws Exception {
        int startIdx = (pageInfo.getCurPage() - 1) * 10 + 1;
        String serviceKey = "pqjiZl5sL4DRPdIzyi9iYiLMgt%2Bu8nWOugT7q9BZYG4G021YOkaWQucOkf%2FxGxQ52P2cwmLpEYlvqPvVymh2Tg%3D%3D";

        // 요청하기 위한 url 생성
        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/EvInfoServiceV2/v1/getEvSearchList");
        urlBuilder.append("?page=").append(startIdx);
        urlBuilder.append("&perPage=").append(10);
        urlBuilder.append("&serviceKey=").append(serviceKey);

        // request
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        // response
        BufferedReader br;
        int resultCode = conn.getResponseCode();
        if(resultCode>=200 && resultCode<=300) { //정상
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else { // 에러
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder resBuilder = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            resBuilder.append(line);
        }
        br.close();
        conn.disconnect();
        System.out.println(resBuilder.toString());

        List<ElecCar> elList = new ArrayList<>();
        JSONParser parser = new JSONParser(); // 의존성 추가 필요
        JSONObject mobj = (JSONObject) parser.parse(resBuilder.toString());
        JSONArray data = (JSONArray) mobj.get("data");
        Long total_count = (Long) mobj.get("matchCount");

        for(int i=0; i<data.size(); i++) {
            JSONObject acJson = (JSONObject) data.get(i);
            String addr = (String) acJson.get("addr");
            String csNm = (String) acJson.get("csNm");
            String lat = (String) acJson.get("lat");
            String longi = (String) acJson.get("longi");
            String cpNm = (String) acJson.get("cpNm");
            elList.add(new ElecCar(addr,csNm,lat,longi,cpNm));
        }

        // 페이지 정보
        int allPage = (int)Math.ceil(total_count.doubleValue()/10);
        int startPage = (pageInfo.getCurPage()-1)/10*10+1;
        int endPage = Math.min(startPage+10-1, allPage);

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        if(pageInfo.getCurPage()>allPage) pageInfo.setCurPage(allPage);

        return elList;
    }
}
