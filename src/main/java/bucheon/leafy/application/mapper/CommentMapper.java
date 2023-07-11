package bucheon.leafy.application.mapper;

<<<<<<< HEAD
=======



>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
import bucheon.leafy.domain.comment.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface CommentMapper {
<<<<<<< HEAD

    int count();

    int deleteAll();
    Long selectByQnaId(Long id);

    List<CommentDto> select(Long id) ;

    int delete(Long id, Long userId) ;

    int insert(CommentDto commentDto) ;

    int update(CommentDto commentDto) ;

    List<CommentDto> selectAll() ;

    String getEmail(Long id);
    String getContent(Long id);


=======
    int count();
    int deleteAll();
    CommentDto select(Long id) ;
    int delete(Long id, Long userId) ;
    int insert(CommentDto commentDto) ;
    int update(CommentDto commentDto) ;
    List<CommentDto> selectAll() ;

>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
}
