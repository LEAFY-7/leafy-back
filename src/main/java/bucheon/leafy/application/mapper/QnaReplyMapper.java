package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.qnareply.dto.QnaReplyDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface QnaReplyMapper {

    int deleteAllReply();
    int delete(Long id, Long userId);
    int insert(QnaReplyDto qnaReplyDto);
    List<QnaReplyDto> selectAll(Long id);
    QnaReplyDto select(Long id);
    int update(QnaReplyDto qnaReplyDto);

}
