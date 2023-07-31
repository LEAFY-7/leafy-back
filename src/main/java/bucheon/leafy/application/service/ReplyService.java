package bucheon.leafy.application.service;

<<<<<<< HEAD
import bucheon.leafy.application.mapper.ReplyMapper;
import bucheon.leafy.domain.reply.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
=======
import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.application.mapper.ReplyMapper;
import bucheon.leafy.domain.qna.QnaReply;
import bucheon.leafy.domain.reply.ReplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde

import java.util.List;

@Service
<<<<<<< HEAD
@Transactional
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper replyMapper;
    public int remove(Long id, Long userId) {
        return replyMapper.delete(id, userId);
    }
    public int write(ReplyDto replyDto) {
        return replyMapper.insert(replyDto);
    }
    public List<ReplyDto> getList(Long qnaCommentId) {
        return replyMapper.selectAll(qnaCommentId);
    }
    public int modify(ReplyDto replyDto) {
        return replyMapper.update(replyDto);
    }
}
=======
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
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
