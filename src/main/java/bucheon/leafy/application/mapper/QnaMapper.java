package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.qna.SearchHandler;
import bucheon.leafy.domain.qna.QnaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface QnaMapper {

    int count() ;
    int deleteAll() ;
    List<QnaDto> select(Long id);
    int delete(Long id, Long userId) ;
    int insert(QnaDto qnaDto);
    int update(QnaDto qnaDto) ;
    List<QnaDto> selectPage(Map<String, Integer> map) ;
    List<QnaDto> selectAll();
    int searchResultCnt(SearchHandler searchHandler);
    List<QnaDto> searchSelectPage(SearchHandler searchHandler) ;


}
