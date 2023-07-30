package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaMapper {
    QnaDto select(Long id);
    boolean delete(Long id) ;
    Long insert(QnaDto qnaDto);
    int update(QnaDto qnaDto, Long id);
    int qnaStatusModify(Long id);
    PageResponse<QnaDto> selectAll(Long userId, PageRequest pageRequest);
    PageResponse<QnaDto> adminSelectAll( PageRequest pageRequest);
    int viewCnt(Long id);
}
