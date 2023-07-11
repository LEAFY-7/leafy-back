package bucheon.leafy.application.service;

<<<<<<< HEAD
import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.application.mapper.ReplyMapper;
import bucheon.leafy.domain.qna.QnaReply;
=======
import bucheon.leafy.application.mapper.ReplyMapper;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
import bucheon.leafy.domain.reply.ReplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

<<<<<<< HEAD
=======

>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
@Service
public class ReplyService {

    @Autowired
    ReplyMapper replyMapper;
<<<<<<< HEAD
    public Long getRepliesByCommentId(Long id) {
        return replyMapper.selectByCommentId(id);
    }
    public int remove(Long id, Long userId) {
        return replyMapper.delete(id, userId);
=======

    public int getCount() {return replyMapper.count();
    }

    public int remove(Long id, Long userId) {
        int rowCnt = replyMapper.delete(id, userId);
        return rowCnt;
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57
    }

    public int write(ReplyDto replytDto) { return replyMapper.insert(replytDto);
    }

<<<<<<< HEAD
    public List<ReplyDto> getList(){ return replyMapper.selectAll();}

    public List<ReplyDto> getRead(Long id){ return replyMapper.select(id); }
=======
    public List<ReplyDto> getList(){return replyMapper.selectAll();
    }
    public ReplyDto  read(Long id){ return replyMapper.select(id);
    }
>>>>>>> 9218b65be2274ff7ec268c04b6bc3eb932729e57

    public int modify(ReplyDto replytDto) {
        return replyMapper.update(replytDto);
    }
}
