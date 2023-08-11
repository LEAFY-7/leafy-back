package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaCommentMapper;

import bucheon.leafy.domain.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
@RequiredArgsConstructor
public class QnaCommentService {

    private final QnaCommentMapper qnacommentMapper;
    public CommentDto getRead(Long id, Long userId){ return qnacommentMapper.select(id, userId); }
    public void remove(Long id, Long userId) {  qnacommentMapper.delete(id, userId); }
    public void write(CommentDto commentDto) {  qnacommentMapper.insert(commentDto); }
    public void modify(CommentDto commentDto) {  qnacommentMapper.update(commentDto); }

}


