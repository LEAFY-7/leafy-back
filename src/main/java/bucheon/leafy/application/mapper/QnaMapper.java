package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnaMapper {

    long count();
    QnaDto findById(Long id);
    List<PageResponse> pageFindById(@Param("id")Long id, @Param("pageRequest")PageRequest pageRequest);
    boolean deleteById(Long id);
    Long save(QnaDto qnaDto);
    int editById(@Param("qnaDto") QnaDto qnaDto, @Param("id") Long id);
    QnaDto findQnaById(Long qnaId);
    int qnaStatusModify(Long id);
    PageResponse selectAll(@Param("userId")Long userId, @Param("pageRequest")PageRequest pageRequest);
    PageResponse adminSelectAll(PageRequest pageRequest);
    int viewCnt(Long id);
    AlarmType findTableIdByUserId(Long userId);
}
