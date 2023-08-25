package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaCommentMapper;

import bucheon.leafy.domain.comment.QnaCommentDto;
import bucheon.leafy.domain.comment.request.QnaCommentEditReqeust;
import bucheon.leafy.domain.comment.request.QnaCommentSaveReqeust;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
@RequiredArgsConstructor
public class QnaCommentService {

    private final QnaCommentMapper qnacommentMapper;
    public void remove(Long qnaCommentId, Long userId) {  qnacommentMapper.deleteByQnaCommentId(qnaCommentId, userId); }
    public void write(QnaCommentSaveReqeust qnaCommentSaveReqeust) {  qnacommentMapper.save(qnaCommentSaveReqeust); }
    public void modify(Long userId ,QnaCommentEditReqeust qnaCommentEditReqeust) {  qnacommentMapper.editByQnaCommentId(userId ,qnaCommentEditReqeust); }

}


