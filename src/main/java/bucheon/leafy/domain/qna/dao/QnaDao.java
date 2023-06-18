package bucheon.leafy.domain.qna.dao;


import bucheon.leafy.domain.qna.domain.QnaDto;
import bucheon.leafy.domain.qna.domain.SearchCondition;

import java.rmi.server.ExportException;
import java.util.List;
import java.util.Map;

public interface QnaDao {
    int count() throws Exception;
    int deleteAll() throws ExportException;
    QnaDto select(Integer bno) throws Exception;
    int deleteForAdmin(Integer bno) throws Exception;
    int delete(Integer bno, String user_id) throws Exception;
    int insert(QnaDto dto) throws Exception;
    int update(QnaDto dto) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;
    List<QnaDto> selectPage(Map map) throws Exception;
    List<QnaDto> selectAll() throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;
    List<QnaDto> searchSelectPage(SearchCondition sc) throws Exception;
}
