package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.leafyapi.LeafyApiDto;
import bucheon.leafy.domain.search.response.SearchResponse;
import bucheon.leafy.domain.search.response.goodNameResponse;
import bucheon.leafy.util.request.PageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    List<SearchResponse> findSearchByPumName(String keyword, PageRequest pageRequest);

    int saveSearch(List<LeafyApiDto> request);

    int deleteSearch();

    long count(String searchName);

    List<goodNameResponse> findGoodNameByPumName(String keyword);
}
