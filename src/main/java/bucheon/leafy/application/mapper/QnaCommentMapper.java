package bucheon.leafy.application.mapper;



import bucheon.leafy.domain.qna.QnaCommentDto;

import bucheon.leafy.domain.qna.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import java.rmi.server.ExportException;
import java.util.List;
import java.util.Map;

@Mapper
public interface QnaCommentMapper {

    int deleteAll() throws ExportException;
    QnaCommentDto select(Integer id) throws Exception;
    int delete(Integer id, String userId) throws Exception;
    int insert(QnaCommentDto dto) throws Exception;
    int update(QnaCommentDto dto) throws Exception;
    List<QnaCommentDto> selectPage(Map map) throws Exception;
    List<QnaCommentDto> selectAll() throws Exception;
    List<QnaCommentDto> searchSelectPage(SearchCondition sc) throws Exception;

}
