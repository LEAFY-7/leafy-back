package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.reply.QnaReplyDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnaReplyMapper {
    void delete(@Param("qnaReplyId")Long qnaReplyId, @Param("userId")Long userId ) ;
    void insert(QnaReplyDto qnaReplyDto) ;
    void update(QnaReplyDto qnaReplyDto) ;
    List<QnaReplyDto> selectAll(Long qanCommentId) ;


}
