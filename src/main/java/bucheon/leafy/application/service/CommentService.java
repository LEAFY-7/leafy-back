package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.CommentMapper;

import bucheon.leafy.domain.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;
    public int remove(Long id, Long userId) { return commentMapper.delete(id, userId); }
    public int write(CommentDto commentDto) { return commentMapper.insert(commentDto); }
    public CommentDto getRead(Long id, Long userId){ return commentMapper.select(id, userId); }
    public int modify(CommentDto commentDto) { return commentMapper.update(commentDto); }

}


