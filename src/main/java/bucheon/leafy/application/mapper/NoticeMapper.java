package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.qna.SearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.rmi.server.ExportException;
import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {


    int deleteAll() throws ExportException;
    NoticeDto select(Integer id) throws Exception;
    int delete(Integer id, Integer user_user_Id) throws Exception;
    int insert(NoticeDto dto) throws Exception;
    int update(NoticeDto dto) throws Exception;
    List<NoticeDto> selectPage(Map map) throws Exception;
    List<NoticeDto> selectAll() throws Exception;
    List<NoticeDto> searchSelectPage(SearchCondition sc) throws Exception;

}
