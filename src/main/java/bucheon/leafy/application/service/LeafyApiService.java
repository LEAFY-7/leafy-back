package bucheon.leafy.application.service;

import bucheon.leafy.domain.leafyapi.LeafyApiDto;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
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
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1); // 전일 데이터 get
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

                LeafyApiDto leafyApiDto = new LeafyApiDto(
                        item.getString("saleDate"),
                        item.getString("flowerGubn"),
                        item.getString("pumName"),
                        item.getString("goodName"),
                        item.getString("lvNm"),
                        item.getInt("maxAmt"),
                        item.getInt("minAmt"),
                        item.getInt("avgAmt")
                );
                leafyApiDtoList.add(leafyApiDto);
            }
            return leafyApiDtoList;
        }
        return null;
    }
}
