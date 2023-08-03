package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.comment.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CommentMapper {
    CommentDto select(Long id, Long userId) ;
    void delete(Long id, Long userId) ;
    void insert(CommentDto commentDto) ;
    void update(CommentDto commentDto) ;
}
