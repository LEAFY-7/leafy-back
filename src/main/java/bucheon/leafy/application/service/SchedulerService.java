package bucheon.leafy.application.service;

import bucheon.leafy.domain.leafyApi.LeafyApiDto;
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

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    @Async
    public void deleteApi() {
        searchService.deleteSearch();
    }


}
