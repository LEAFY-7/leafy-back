package bucheon.leafy.application.mapper;

<<<<<<< HEAD
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QnaMapper {
    QnaDto findById(Long id);
    boolean deleteById(Long id) ;
    Long save(QnaDto qnaDto);
    int editById(@Param("qnaDto") QnaDto qnaDto, @Param("id") Long id);
    int qnaStatusModify(Long id);
    PageResponse<QnaDto> selectAll(Long userId, PageRequest pageRequest);
    PageResponse<QnaDto> adminSelectAll( PageRequest pageRequest);
    int viewCnt(Long id);
=======

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

>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
}
