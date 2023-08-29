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
    void deleteByQnaReplyId(Long qnaReplyId) ;
    int save(@Param("qnaReplySaveRequest") QnaReplySaveRequest qnaReplySaveRequest,@Param("userId") Long userId);
    QnaReplySaveResponse saveResponse(QnaReplySaveRequest qnaReplySaveRequest);
    int edit(@Param("qnaReplyId")Long qnaReplyId,@Param("qnaReplyEditReqeust") QnaReplyEditReqeust qnaReplyEditReqeust);

    QnaReplyEditResponse qnaReplyEditFind(QnaReplyEditReqeust qnaReplyEditReqeust);    //수정 조회오기

    QnaReplyResponse findByQnaCommentId(@Param("qnaCommentId")Long qnaCommentId);   //클릭해서 읽기(select)

}
