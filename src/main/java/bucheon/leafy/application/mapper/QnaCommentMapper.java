package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.comment.QnaCommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface QnaCommentMapper {
    QnaCommentDto select(Long id, Long userId) ;
    void delete(Long id, Long userId) ;
    void insert(QnaCommentDto qnaCommentDto) ;
    void update(QnaCommentDto qnaCommentDto) ;
}
