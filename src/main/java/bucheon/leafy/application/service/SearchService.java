package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.SearchMapper;
import bucheon.leafy.domain.leafyapi.LeafyApiDto;
import bucheon.leafy.domain.search.response.SearchResponse;
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

    public PageResponse getSearch(String searchName, PageRequest pageRequest) {
        pageRequest = new PageRequest(pageRequest.getPage(), pageRequest.getLimit(), pageRequest.getOffset(), pageRequest.getSortColumn(), pageRequest.getSortStatus());
        List<SearchResponse> list = searchMapper.findSearchByPumName(searchName, pageRequest);
        long total = searchMapper.count(searchName);
        PageResponse pageResponse = PageResponse.of(pageRequest, list, total);

        return pageResponse;
    }

    @Transactional
    public int SaveSearch(LeafyApiDto req){return searchMapper.SaveSearch(req);}

    @Transactional
    public int deleteSearch() {return searchMapper.deleteSearch();}
}
