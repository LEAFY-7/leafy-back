package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.qna.SearchHandler;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;


@Mapper
public interface NoticeMapper {

    int count();
    int deleteAll();
    List<NoticeDto> select(Long id);
    int delete(Long id, Long userId);
    int insert(NoticeDto noticeDto) ;
    int update(NoticeDto noticeDto);
    List<NoticeDto> selectPage(Map<String ,Integer>map);
    List<NoticeDto> selectAll();

}
