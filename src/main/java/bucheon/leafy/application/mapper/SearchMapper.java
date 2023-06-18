package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.search.response.SearchResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface SearchMapper {

    SearchResponse findSearchByPumName(String pumName);
}
