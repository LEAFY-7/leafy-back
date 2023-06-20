package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.leafyApi.LeafyApiDto;
import bucheon.leafy.domain.search.response.SearchResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SearchMapper {

    List<SearchResponse> findSearchByPumName(String searchName);

    int SaveSearch(LeafyApiDto req);

    int deleteSearch();
}
