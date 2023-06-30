package bucheon.leafy.application.mapper;



import bucheon.leafy.domain.qna.QnaCommentDto;

import bucheon.leafy.domain.qna.SearchHandler;
import org.apache.ibatis.annotations.Mapper;
import java.rmi.server.ExportException;
import java.util.List;
import java.util.Map;

@Mapper
public interface QnaCommentMapper {

    int deleteAll() throws ExportException;
    QnaCommentDto select(Integer id) ;
    int delete(Integer id, String userId) ;
    int insert(QnaCommentDto dto) ;
    int update(QnaCommentDto dto) ;
    List<QnaCommentDto> selectPage(Map map) ;
    List<QnaCommentDto> selectAll() ;
    List<QnaCommentDto> searchSelectPage(SearchHandler sc) ;

}
