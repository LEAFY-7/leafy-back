package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.reply.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.rmi.server.ExportException;
import java.util.List;

@Mapper
public interface QnaReplyMapper {

    int deleteAllReply()throws ExportException;
    int delete(Integer cid, String user_id) throws Exception;
    int insert(ReplyDto dto) throws Exception;
    List<ReplyDto> selectAll(Integer cid) throws Exception;
    ReplyDto select(Integer cid) throws Exception;
    int update(ReplyDto dto) throws Exception;

}
