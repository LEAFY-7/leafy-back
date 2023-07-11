package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.comment.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface CommentMapper {

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


}
