package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.SearchMapper;
import bucheon.leafy.domain.leafyApi.LeafyApiDto;
import bucheon.leafy.domain.result.Message;
import bucheon.leafy.domain.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchMapper searchMapper;

    public Result getSearch(String searchName) {
        if(searchMapper.findSearchByPumName(searchName).size() == 0){
            return new Result(200, Message.SUCCESS.getMsg(), "최근 7일간 경매내역이 없습니다.");
        }
        return new Result(200, Message.SUCCESS.getMsg(), searchMapper.findSearchByPumName(searchName));
    }

    @Transactional
    public int SaveSearch(LeafyApiDto req){return searchMapper.SaveSearch(req);}

    @Transactional
    public int deleteSearch() {return searchMapper.deleteSearch();}
}
