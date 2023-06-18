package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.qnareply.domain.QnaReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.rmi.server.ExportException;
import java.util.List;

@Mapper
public interface QnaReplyMapper {
    int count(Integer cid) throws Exception;
    int deleteAllReply()throws ExportException;
    int delete(Integer cid, String user_id) throws Exception;
    int insert(QnaReplyDto dto) throws Exception;
    List<QnaReplyDto> selectAll(Integer cid) throws Exception;
    QnaReplyDto select(Integer cid) throws Exception;
    int update(QnaReplyDto dto) throws Exception;
    int updateCommentCnt(Integer bno, int i);
    int increaseviewcntQnaReply(Integer id);
}
