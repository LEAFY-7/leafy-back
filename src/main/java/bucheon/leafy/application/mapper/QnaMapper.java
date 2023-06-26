package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.qna.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import java.rmi.server.ExportException;
import java.util.List;
import java.util.Map;

@Mapper
public interface QnaMapper {


    int deleteAll() throws ExportException;
    QnaDto select(Integer id) throws Exception;
    int delete(Integer id, String userId) throws Exception;
    int insert(QnaDto dto) throws Exception;
    int update(QnaDto dto) throws Exception;
    List<QnaDto> selectPage(Map map) throws Exception;
    List<QnaDto> selectAll() throws Exception;
    List<QnaDto> searchSelectPage(SearchCondition sc) throws Exception;

}