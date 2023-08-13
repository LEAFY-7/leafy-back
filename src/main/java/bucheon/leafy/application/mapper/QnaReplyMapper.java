package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.reply.QnaReplyDto;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaReplyMapper {
    void delete(Long id, Long userId ) ;
    void insert(QnaReplyDto qnaReplyDto) ;
    void update(QnaReplyDto qnaReplyDto) ;
    List<QnaReplyDto> selectAll(Long qanCommentId) ;


}
