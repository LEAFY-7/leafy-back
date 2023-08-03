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
    public void remove(Long id, Long userId) { replyMapper.delete(id, userId); }
    public void write(ReplyDto replyDto) { replyMapper.insert(replyDto); }
    public void modify(ReplyDto replyDto) { replyMapper.update(replyDto); }
    public List<ReplyDto> getList(Long qnaCommentId) {
        return replyMapper.selectAll(qnaCommentId);
    }

}