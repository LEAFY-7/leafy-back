package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.qna.reply.request.QnaReplyEditRequest;
import bucheon.leafy.domain.qna.reply.request.QnaReplySaveRequest;
import bucheon.leafy.domain.qna.reply.response.QnaReplyEditResponse;
import bucheon.leafy.domain.qna.reply.response.QnaReplyResponse;
import bucheon.leafy.domain.qna.reply.response.QnaReplySaveResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface QnaReplyMapper {
    void deleteByQnaReplyId(@Param("qnaReplyId") Long qnaReplyId, @Param("userId") Long userId, @Param("qnaCommentId") Long qnaCommentId);

    int saveQnaReply(@Param("qnaReplySaveRequest") QnaReplySaveRequest qnaReplySaveRequest, @Param("userId") Long userId, @Param("qnaCommentId") Long qnaCommentId);

    QnaReplySaveResponse selectAfterQnaReplySave(Long qnaReplyId);

    int editQnaReply(@Param("qnaReplyId") Long qnaReplyId, @Param("qnaReplyEditRequest") QnaReplyEditRequest qnaReplyEditRequest, @Param("userId") Long userId, @Param("qnaCommentId") Long qnaCommentId);

    QnaReplyEditResponse selectAfterQnaReplyEdit(Long qnaReplyId);    //수정 조회오기
    List<QnaReplyResponse> selectByQnaId(Long qnaId);

    Long selectUserIdByQnaCommentId(Long qnaCommentId);

    QnaReplyResponse selectIsDeleteTrueAndFalseById(Long qnaReplyId);

    Long findUserId(Long userId);

}
