package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.SearchMapper;
import bucheon.leafy.domain.leafyapi.LeafyApiDto;
import bucheon.leafy.domain.search.response.SearchResponse;
import bucheon.leafy.domain.search.response.goodNameResponse;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchMapper searchMapper;

    public PageResponse getSearch(String keyword, PageRequest pageRequest) {
        List<SearchResponse> list = searchMapper.findSearchByPumName(keyword, pageRequest);
        long total = searchMapper.count(keyword);
        PageResponse pageResponse = PageResponse.of(pageRequest, list, total);

        return pageResponse;
    }

    @Transactional
    public int saveSearch(List<LeafyApiDto> request){return searchMapper.saveSearch(request);}

    @Transactional
    public int deleteSearch() {return searchMapper.deleteSearch();}

    public List<goodNameResponse> getGoodName(String keyword) {
        return searchMapper.findGoodNameByPumName(keyword);
    }
}
