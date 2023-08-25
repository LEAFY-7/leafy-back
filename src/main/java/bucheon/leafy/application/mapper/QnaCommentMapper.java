package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.comment.QnaCommentDto;
import bucheon.leafy.domain.comment.request.QnaCommentEditReqeust;
import bucheon.leafy.domain.comment.request.QnaCommentSaveReqeust;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface QnaCommentMapper {
    void deleteByQnaCommentId(@Param("qnaCommentId") Long qnaCommentId,@Param("userId") Long userId) ;
    void save(QnaCommentSaveReqeust qnaCommentSaveReqeust) ;
    void editByQnaCommentId(Long userId ,QnaCommentEditReqeust qnaCommentEditReqeust) ;
}
