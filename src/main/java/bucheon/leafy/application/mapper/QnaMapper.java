package bucheon.leafy.application.mapper;


<<<<<<< HEAD
import bucheon.leafy.domain.qna.EmailSend;
=======
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
import bucheon.leafy.domain.qna.SearchHandler;
import bucheon.leafy.domain.qna.QnaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface QnaMapper {

<<<<<<< HEAD
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
=======
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

>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57

}
