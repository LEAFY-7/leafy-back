package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.qna.QnaReply;
import bucheon.leafy.domain.reply.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    int deleteAll();
    Long selectByCommentId(Long id);

    List<ReplyDto> select(Long id) ;

    int delete(Long id, Long userId ) ;

    int insert(ReplyDto replyDto) ;

    int update(ReplyDto replyDto) ;

    List<ReplyDto> selectAll() ;


}
