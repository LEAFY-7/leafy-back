package bucheon.leafy.application.mapper;

<<<<<<< HEAD
import bucheon.leafy.domain.qna.QnaReply;
=======
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
import bucheon.leafy.domain.reply.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

<<<<<<< HEAD
    int deleteAll();
    Long selectByCommentId(Long id);

    List<ReplyDto> select(Long id) ;

    int delete(Long id, Long userId ) ;

    int insert(ReplyDto replyDto) ;

    int update(ReplyDto replyDto) ;

=======
    int count();
    int deleteAll();
    ReplyDto select(Long id) ;
    int delete(Long id, Long userId ) ;
    int insert(ReplyDto replyDto) ;
    int update(ReplyDto replyDto) ;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
    List<ReplyDto> selectAll() ;


}
