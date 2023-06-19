package bucheon.leafy.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SchedulerService {

    private final LeafyApiService leafyApiService;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    @Async
    public void getAndSaveApi() {
        int flowerGubn = 1;
        while (flowerGubn++ != 5){
            // TODO LeafyApiService.getSearchList 리스트로 반환받고 searchService.SaveSearch DB insert
        }
    }


}
