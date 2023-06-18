package bucheon.leafy.application.service;

import bucheon.leafy.domain.leafyApi.request.LeafyApiRequest;
import bucheon.leafy.domain.leafyApi.response.LeafyApiResponse;
import bucheon.leafy.exception.ExistException;
import bucheon.leafy.exception.enums.ExceptionKey;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeafyApiService {

    @Value("${leafy.api.serviceKey}")
    private String serviceKey;

    public JSONArray getSearchList(LeafyApiRequest req) {
        RestTemplate rt = new RestTemplate();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        ResponseEntity<String> response = rt.getForEntity(
                "https://flower.at.or.kr/api/returnData.api?kind=f001&serviceKey="+serviceKey+"&baseDate="+date+"&flowerGubn="+req.getFlowerGubn()+"&dataType=json&countPerPage=999",
                String.class
        );
        JSONObject jsonObject = new JSONObject(response.getBody());
        String resultCd = jsonObject.getJSONObject("response").getString("resultCd");
        JSONArray items = jsonObject.getJSONObject("response").getJSONArray("items");
        if(resultCd.equals("0")){
            return items;
        }
       return null;
    }
}
