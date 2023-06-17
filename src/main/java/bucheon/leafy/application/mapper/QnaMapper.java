package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.qna.domain.QnaDto;
import bucheon.leafy.domain.qna.domain.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import java.rmi.server.ExportException;
import java.util.List;
import java.util.Map;

@Mapper
public interface QnaMapper {

    int countQna() throws Exception;
    int deleteAllQna() throws ExportException;
    QnaDto selectQna(Integer id) throws Exception;
    int deleteQna(Integer id, String userId) throws Exception;
    int insertQna(QnaDto dto) throws Exception;
    int updateQna(QnaDto dto) throws Exception;
    int increaseViewCntQna(Integer id) throws Exception;
    List<QnaDto> selectPageQna(Map map) throws Exception;
    List<QnaDto> selectAllQna() throws Exception;
    int searchResultCntQna(SearchCondition sc) throws Exception;
    List<QnaDto> searchSelectPageQna(SearchCondition sc) throws Exception;
    int updateCommentCnt(Integer bno, int i) throws Exception;
}
