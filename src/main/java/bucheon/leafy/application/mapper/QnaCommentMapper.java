package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.comment.request.QnaCommentSaveReqeust;
import bucheon.leafy.domain.comment.response.QnaCommentSaveResponse;
import bucheon.leafy.domain.reply.response.QnaReplyEditResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface QnaCommentMapper {
    void deleteByQnaCommentId(@Param("qnaCommentId") Long qnaCommentId,@Param("userId") Long userId) ;
    int save(QnaCommentSaveReqeust qnaCommentSaveReqeust);
    QnaCommentSaveResponse saveResponse(QnaCommentSaveReqeust qnaCommentSaveReqeust);
    QnaReplyEditResponse editByQnaCommentId(@Param("userId")Long qnaReplyId , @Param("comment")String comment) ;
}
