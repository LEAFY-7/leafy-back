package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.qna.EmailSend;
import bucheon.leafy.domain.qna.SearchHandler;
import bucheon.leafy.domain.qna.QnaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface QnaMapper {

    int deleteAll() ;

    int count();

    QnaDto select(Long id);

    int delete(Long id, Long userId) ;
    int adminDelete(Long id);

    int insert(QnaDto qnaDto);

    int update(QnaDto qnaDto);
    int qnaStatusModify(Long id);

    List<QnaDto> selectAll();
    int increaseViewCnt(Long id);
    int searchResultCnt(SearchHandler searchHandler);
    List<QnaDto> searchSelectPage(SearchHandler searchHandler) ;

}
