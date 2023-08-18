package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.comment.QnaCommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface QnaCommentMapper {
    void deleteByQnaCommentId(@Param("qnaCommentId") Long qnaCommentId,@Param("userId") Long userId) ;
    void save(QnaCommentDto qnaCommentDto) ;
    void editByQnaCommentId(QnaCommentDto qnaCommentDto) ;
}
