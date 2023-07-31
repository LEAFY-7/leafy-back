package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QnaMapper {
    QnaDto findById(Long id);
    boolean deleteById(Long id) ;
    Long save(QnaDto qnaDto);
    int editById(@Param("qnaDto") QnaDto qnaDto, @Param("id") Long id);
    int qnaStatusModify(Long id);
    PageResponse<QnaDto> selectAll(Long userId, PageRequest pageRequest);
    PageResponse<QnaDto> adminSelectAll( PageRequest pageRequest);
    int viewCnt(Long id);
}
