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
    public void remove(Long qnaCommentId, Long userId) {  qnacommentMapper.deleteByQnaCommentId(qnaCommentId, userId); }
    public void write(QnaCommentDto qnaCommentDto) {  qnacommentMapper.save(qnaCommentDto); }
    public void modify(QnaCommentDto qnaCommentDto) {  qnacommentMapper.editByQnaCommentId(qnaCommentDto); }

}


