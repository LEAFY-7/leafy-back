package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.ReplyMapper;
import bucheon.leafy.domain.reply.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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