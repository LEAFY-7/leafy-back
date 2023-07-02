package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.ReplyMapper;
import bucheon.leafy.domain.reply.ReplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReplyService {

    @Autowired
    ReplyMapper replyMapper;

    public int getCount() {return replyMapper.count();
    }

    public int remove(Long id, Long userId) {
        int rowCnt = replyMapper.delete(id, userId);
        return rowCnt;
    }

    public int write(ReplyDto replytDto) { return replyMapper.insert(replytDto);
    }

    public List<ReplyDto> getList(){return replyMapper.selectAll();
    }
    public ReplyDto  read(Long id){ return replyMapper.select(id);
    }

    public int modify(ReplyDto replytDto) {
        return replyMapper.update(replytDto);
    }
}
