package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.reply.QnaReplyDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface QnaReplyMapper {
    void deleteByQnaReplyId(@Param("qnaReplyId")Long qnaReplyId, @Param("userId")Long userId ) ;
    void save(QnaReplyDto qnaReplyDto) ;
    void edit(QnaReplyDto qnaReplyDto) ;
    QnaReplyDto findByQnaCommentId(@Param("qnaCommentId")Long qnaCommentId);   //클릭해서 읽기(select)

}
