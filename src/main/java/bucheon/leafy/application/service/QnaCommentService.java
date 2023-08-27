package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaCommentMapper;


import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.domain.comment.request.QnaCommentSaveReqeust;
import bucheon.leafy.domain.comment.response.QnaCommentSaveResponse;
import bucheon.leafy.domain.qna.response.QnaSaveResponse;
import bucheon.leafy.exception.WriteFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
@RequiredArgsConstructor
public class QnaCommentService {

    private final QnaCommentMapper qnacommentMapper;
    private final QnaMapper qnaMapper;
    public void remove(Long qnaCommentId, Long userId) {  qnacommentMapper.deleteByQnaCommentId(qnaCommentId, userId); }
    public QnaCommentSaveResponse write(QnaCommentSaveReqeust qnaCommentSaveReqeust) {

        if (qnacommentMapper.save(qnaCommentSaveReqeust) != 1) {
            throw new WriteFailedException();
        }
        // 답변상태 완료 코드작성하기
        qnaMapper.editByIdQnaStatus(qnaCommentSaveReqeust.getQnaId());

        QnaCommentSaveResponse qnaSaveResponse = qnacommentMapper.saveResponse(qnaCommentSaveReqeust);

    return qnaSaveResponse;

    }
    public void modify(Long userId ,String comment ) {  qnacommentMapper.editByQnaCommentId(userId ,comment); }

}


