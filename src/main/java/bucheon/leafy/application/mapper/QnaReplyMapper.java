package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.reply.request.QnaReplySaveRequest;
import bucheon.leafy.domain.reply.response.QnaReplyResponse;
import bucheon.leafy.domain.reply.response.QnaReplySaveResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface QnaReplyMapper {
    void deleteByQnaReplyId(Long qnaReplyId) ;
    int save(QnaReplySaveRequest qnaReplySaveRequest) ;
    QnaReplySaveResponse saveResponse(QnaReplySaveRequest qnaReplySaveRequest);
    void edit(@Param("qnaReplyId")Long qnaReplyId,@Param("comment") String comment) ;
    QnaReplyResponse findByQnaCommentId(@Param("qnaCommentId")Long qnaCommentId);   //클릭해서 읽기(select)

}
