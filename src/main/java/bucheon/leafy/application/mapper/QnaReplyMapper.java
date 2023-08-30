package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.comment.request.QnaCommentEditRequest;
import bucheon.leafy.domain.comment.response.QnaCommentEditResponse;
import bucheon.leafy.domain.reply.request.QnaReplyEditReqeust;
import bucheon.leafy.domain.reply.request.QnaReplySaveRequest;
import bucheon.leafy.domain.reply.response.QnaReplyEditResponse;
import bucheon.leafy.domain.reply.response.QnaReplyResponse;
import bucheon.leafy.domain.reply.response.QnaReplySaveResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface QnaReplyMapper {
    void deleteByQnaReplyId(@Param("qnaReplyId") Long qnaReplyId,@Param("userId") Long userId, @Param("qnaCommentId")Long qnaCommentId) ;
    int saveQnaReply(@Param("qnaReplySaveRequest") QnaReplySaveRequest qnaReplySaveRequest,@Param("userId") Long userId, @Param("qnaCommentId")Long qnaCommentId);
    QnaReplySaveResponse saveQnaReplyResponse(Long userId);
    int editQnaReply(@Param("qnaReplyId")Long qnaReplyId,@Param("qnaReplyEditReqeust") QnaReplyEditReqeust qnaReplyEditReqeust, @Param("userId") Long userId, @Param("qnaCommentId") Long qnaCommentId);
    QnaReplyEditResponse qnaReplyEditFind(Long userId);    //수정 조회오기
//    QnaReplyResponse findByQnaCommentId(@Param("qnaCommentId")Long qnaCommentId);   //클릭해서 읽기(select)
    Long findUserIdByQnaCommentId(Long qnaCommentId);


}
