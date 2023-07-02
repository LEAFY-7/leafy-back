package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.CommentMapper;
import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.domain.comment.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    QnaMapper qnaMapper;

    @Autowired
    CommentMapper commentMapper;

    public int getCount(Long id) {
        return commentMapper.count();
    }

    public int remove(Long id, Long userId) {
        int rowCnt = commentMapper.delete(id, userId);
        return rowCnt;
    }

    public int write(CommentDto commentDto) {
        return commentMapper.insert(commentDto);
    }

    public List<CommentDto> getList(){
        return commentMapper.selectAll();
    }

    public CommentDto read(Long id){
        return commentMapper.select(id);
    }

    public int modify(CommentDto commentDto) {
        return commentMapper.update(commentDto);
    }
}
