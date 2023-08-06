package bucheon.leafy.application.service;

import bucheon.leafy.domain.leafyapi.LeafyApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SchedulerService {

    private final LeafyApiService leafyApiService;

    private final SearchService searchService;

    private final AlarmService alarmService;

    // 식물별 경매가 외부 api 가져와서 저장, 식물 구분이 4개라서 for문으로 1~4까지
    @Scheduled(cron = "0 0 1 * * *")
    @Transactional
    @Async
    public void getAndSaveApi() {
        for (int i=1; i < 5; i++){
            List<LeafyApiDto> leafyApiDtoList = leafyApiService.getSearchList(Integer.toString(i));
            if(!(leafyApiDtoList == null) && !leafyApiDtoList.isEmpty()){
                for(LeafyApiDto leafyApiDto: leafyApiDtoList){
                    searchService.SaveSearch(leafyApiDto);
                }
            }
        }
    }

    // 7일 경과된 식물 리스트 삭제, 최근 경매가를 노출하는 것을 목적으로 해서 기존 데이터 삭제
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    @Async
    public void deleteApi() {
        searchService.deleteSearch();
    }


    // 알림 전부 7일 경과후 삭제처리
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    @Async
    public void deleteAlarm() {
        alarmService.deleteAlarm();
    }


}
