package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.application.mapper.ReplyMapper;
import bucheon.leafy.domain.qna.QnaReply;
import bucheon.leafy.domain.reply.ReplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    ReplyMapper replyMapper;
    public Long getRepliesByCommentId(Long id) {
        return replyMapper.selectByCommentId(id);
    }
    public int remove(Long id, Long userId) {
        return replyMapper.delete(id, userId);
    }

    public int write(ReplyDto replytDto) { return replyMapper.insert(replytDto);
    }

    public List<ReplyDto> getList(){ return replyMapper.selectAll();}

    public List<ReplyDto> getRead(Long id){ return replyMapper.select(id); }

    public int modify(ReplyDto replytDto) {
        return replyMapper.update(replytDto);
    }
}
