package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.comment.CommentDto;
import org.apache.ibatis.annotations.Mapper;
<<<<<<< HEAD

=======
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
import java.util.List;


@Mapper
public interface CommentMapper {
<<<<<<< HEAD
    CommentDto select(Long id, Long userId) ;
    int delete(Long id, Long userId) ;
    int insert(CommentDto commentDto) ;
    int update(CommentDto commentDto) ;
    List<CommentDto> selectId();
=======
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


>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
}
