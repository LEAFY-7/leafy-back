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
    public CommentDto getRead(Long id, Long userId){ return commentMapper.select(id, userId); }
    public void remove(Long id, Long userId) {  commentMapper.delete(id, userId); }
    public void write(CommentDto commentDto) {  commentMapper.insert(commentDto); }
    public void modify(CommentDto commentDto) {  commentMapper.update(commentDto); }

}


