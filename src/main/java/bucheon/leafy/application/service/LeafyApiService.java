package bucheon.leafy.application.service;

import bucheon.leafy.domain.leafyApi.LeafyApiDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LeafyApiService {

    @Value("${leafy.api.serviceKey}")
    private String serviceKey;

    public List<LeafyApiDto> getSearchList(String flowerGubn) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        ResponseEntity<String> response = rt.getForEntity(
                "https://flower.at.or.kr/api/returnData.api?kind=f001&serviceKey="+serviceKey+"&baseDate="+date+"&flowerGubn="+flowerGubn+"&dataType=json&countPerPage=999",
                String.class
        );
        JSONObject jsonObject = new JSONObject(response.getBody());
        String resultCd = jsonObject.getJSONObject("response").getString("resultCd");
        JSONArray items = jsonObject.getJSONObject("response").getJSONArray("items");
        List<LeafyApiDto> leafyApiDtoList = new ArrayList<>();
        if(resultCd.equals("0") && !(items.length() == 0)){
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);

                String saleDate = item.getString("saleDate");
                flowerGubn = item.getString("flowerGubn");
                String pumName = item.getString("pumName");
                String goodName = item.getString("goodName");
                String lv = item.getString("lvNm");
                int maxAmt = item.getInt("maxAmt");
                int minAmt = item.getInt("minAmt");
                int avgAmt = item.getInt("avgAmt");

                LeafyApiDto leafyApiDto = new LeafyApiDto(saleDate, flowerGubn, pumName, goodName, lv, maxAmt, minAmt, avgAmt);
                leafyApiDtoList.add(leafyApiDto);
            }
            return leafyApiDtoList;
        }
        return null;
    }
}
