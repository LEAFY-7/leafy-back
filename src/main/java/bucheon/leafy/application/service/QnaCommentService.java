package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaCommentMapper;

import bucheon.leafy.domain.comment.QnaCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
@RequiredArgsConstructor
public class QnaCommentService {

    private final QnaCommentMapper qnacommentMapper;
    public QnaCommentDto getRead(Long id, Long userId){ return qnacommentMapper.select(id, userId); }
    public void remove(Long id, Long userId) {  qnacommentMapper.delete(id, userId); }
    public void write(QnaCommentDto qnaCommentDto) {  qnacommentMapper.insert(qnaCommentDto); }
    public void modify(QnaCommentDto qnaCommentDto) {  qnacommentMapper.update(qnaCommentDto); }

}


