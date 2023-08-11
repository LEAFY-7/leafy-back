package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.reply.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaReplyMapper {
    void delete(Long id, Long userId ) ;
    void insert(ReplyDto replyDto) ;
    void update(ReplyDto replyDto) ;
    List<ReplyDto> selectAll(Long qanCommentId) ;


}
