package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.util.request.PageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QnaMapper {
    QnaDto findById(Long id);
    QnaDto pageFindById(Long id);
    boolean deleteById(Long id);
    Long save(QnaDto qnaDto);
    int editById(@Param("qnaDto") QnaDto qnaDto, @Param("id") Long id);
    QnaDto findQnaById(Long qnaId);
    int qnaStatusModify(Long id);
    QnaDto selectAll(@Param("userId")Long userId, @Param("pageRequest")PageRequest pageRequest);
    QnaDto adminSelectAll( PageRequest pageRequest);
    int viewCnt(Long id);
    Long findTableIdByUserId(Long userId);
}
