package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.SearchMapper;
import bucheon.leafy.domain.leafyApi.LeafyApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchMapper searchMapper;

    public ResponseEntity getSearch(String pumName) {
        //todo 조회결과 null 일때 어떻게 내려줄지 고민
        return ResponseEntity.status(200).body(searchMapper.findSearchByPumName(pumName));
    }

    @Transactional
    public int SaveSearch(LeafyApiDto req){
        return searchMapper.SaveSearch(req);
    }
}
