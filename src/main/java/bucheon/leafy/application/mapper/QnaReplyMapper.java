package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.reply.request.QnaReplyEditReqeust;
import bucheon.leafy.domain.reply.request.QnaReplySaveReqeust;
import bucheon.leafy.domain.reply.response.QnaReplyResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface QnaReplyMapper {
    void deleteByQnaReplyId(@Param("qnaReplyId")Long qnaReplyId, @Param("userId")Long userId ) ;
    void save(QnaReplySaveReqeust qnaReplySaveReqeust) ;
    void edit(@Param("qnaId")Long qnaId,@Param("qnaReplyEditReqeust") QnaReplyEditReqeust qnaReplyEditReqeust) ;
    QnaReplyResponse findByQnaCommentId(@Param("qnaCommentId")Long qnaCommentId);   //클릭해서 읽기(select)

}
