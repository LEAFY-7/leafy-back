package bucheon.leafy.application.mapper;

<<<<<<< HEAD
=======
import bucheon.leafy.domain.qna.QnaReply;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
import bucheon.leafy.domain.reply.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
<<<<<<< HEAD
    int delete(Long id, Long userId ) ;
    int insert(ReplyDto replyDto) ;
    int update(ReplyDto replyDto) ;
    List<ReplyDto> selectAll(Long qanCommentId) ;
=======

    int deleteAll();
    Long selectByCommentId(Long id);

    List<ReplyDto> select(Long id) ;

    int delete(Long id, Long userId ) ;

    int insert(ReplyDto replyDto) ;

    int update(ReplyDto replyDto) ;

    List<ReplyDto> selectAll() ;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde


}
