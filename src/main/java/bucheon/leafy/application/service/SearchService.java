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

    public ResponseEntity getSearch(String searchName) {
        if(searchMapper.findSearchByPumName(searchName).size() == 0){
            return ResponseEntity.status(200).body("최근 7일간 경매내역이 없습니다.");
        }
        return ResponseEntity.status(200).body(searchMapper.findSearchByPumName(searchName));
    }

    @Transactional
    public int SaveSearch(LeafyApiDto req){return searchMapper.SaveSearch(req);}

    @Transactional
    public int deleteSearch() {return searchMapper.deleteSearch();}
}
