package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.leafyApi.LeafyApiDto;
import bucheon.leafy.domain.search.response.SearchResponse;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SearchMapper {

    List<SearchResponse> findSearchByPumName(String searchName, PageRequest pageRequest);

    int SaveSearch(LeafyApiDto req);

    int deleteSearch();

    long count(String searchName);
}