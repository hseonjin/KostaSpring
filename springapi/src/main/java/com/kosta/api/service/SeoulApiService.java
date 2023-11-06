package com.kosta.api.service;

import com.kosta.api.dto.AnimalClinic;
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
public class SeoulApiService {
    public List<AnimalClinic> animalClinicList(PageInfo pageInfo) throws Exception {
        int startIdx = (pageInfo.getCurPage() - 1) * 10 + 1;

        // 요청하기 위한 url 생성
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/"+ URLEncoder.encode("4864596d7173656f3131306649487165", "UTF-8"));
        urlBuilder.append("/"+URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("/"+URLEncoder.encode("LOCALDATA_020301", "UTF-8"));
        urlBuilder.append("/"+URLEncoder.encode(startIdx+"", "UTF-8"));
        urlBuilder.append("/"+URLEncoder.encode(startIdx+10+"", "UTF-8"));

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

        List<AnimalClinic> acList = new ArrayList<>();
        JSONParser parser = new JSONParser(); // 의존성 추가 필요
        JSONObject mobj = (JSONObject) parser.parse(resBuilder.toString());
        JSONObject LOCALDATA_020301 = (JSONObject) mobj.get("LOCALDATA_020301");
        Long list_total_count = (Long) LOCALDATA_020301.get("list_total_count");
        JSONArray row = (JSONArray) LOCALDATA_020301.get("row");

        for(int i=0; i<row.size(); i++) {
            JSONObject acJson = (JSONObject) row.get(i);
            String trdStatNm = (String) acJson.get("TRDSTATENM");
            String siteTel = (String) acJson.get("SITETEL");
            String rdnwhlAddr = (String) acJson.get("RDNWHLADDR");
            String bplcNm = (String) acJson.get("BPLCNM");
            String x = (String) acJson.get("X");
            String y = (String) acJson.get("Y");
            acList.add(new AnimalClinic(trdStatNm, siteTel, rdnwhlAddr, bplcNm, x, y));
        }

        // 페이지 정보
        int allPage = (int)Math.ceil(list_total_count.doubleValue()/10);
        int startPage = (pageInfo.getCurPage()-1)/10*10+1;
        int endPage = Math.min(startPage+10-1, allPage);

        pageInfo.setAllPage(allPage);
        pageInfo.setStartPage(startPage);
        pageInfo.setEndPage(endPage);
        if(pageInfo.getCurPage()>allPage) pageInfo.setCurPage(allPage);

        return acList;
    }
}
