package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaReplyMapper;
import bucheon.leafy.domain.reply.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaReplyService {

    private final QnaReplyMapper qnareplyMapper;
    public void remove(Long id, Long userId) { qnareplyMapper.delete(id, userId); }
    public void write(ReplyDto replyDto) { qnareplyMapper.insert(replyDto); }
    public void modify(ReplyDto replyDto) { qnareplyMapper.update(replyDto); }
    public List<ReplyDto> getList(Long qnaCommentId) {
        return qnareplyMapper.selectAll(qnaCommentId);
    }

}