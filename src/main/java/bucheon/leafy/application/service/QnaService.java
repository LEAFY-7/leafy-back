package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaCommentMapper;
import bucheon.leafy.application.mapper.QnaMapper;

import bucheon.leafy.application.mapper.QnaReplyMapper;
import bucheon.leafy.application.repository.QnaRepository;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.qna.comment.response.QnaCommentResponse;
import bucheon.leafy.domain.qna.Qna;
import bucheon.leafy.domain.qna.QnaStatus;
import bucheon.leafy.domain.qna.request.QnaEditRequest;
import bucheon.leafy.domain.qna.request.QnaSaveRequest;
import bucheon.leafy.domain.qna.response.MyPageQnaResponse;
import bucheon.leafy.domain.qna.response.QnaEditResponse;
import bucheon.leafy.domain.qna.response.QnaResponse;
import bucheon.leafy.domain.qna.response.QnaSaveResponse;
import bucheon.leafy.domain.qna.reply.response.QnaReplyResponse;
import bucheon.leafy.exception.*;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final QnaMapper qnaMapper;
    private final QnaCommentMapper qnaCommentMapper;
    private final QnaReplyMapper qnaReplyMapper;
    private final QnaRepository qnaRepository;
    private final AlarmService alarmService;

    public void remove(Long qnaId, AuthUser user) {
         Long userId = user.getUserId();
        QnaResponse result = qnaMapper.findQnaById(qnaId);
         if(result == null){ throw new QnaNotFoundException(); }


         if(result.getUserId() != user.getUserId()){ throw new QnaNotFoundException(); }

        if(qnaMapper.deleteById(qnaId) != 1){
            throw new RemoveFailedException();
        }

    }

    public QnaSaveResponse write( QnaSaveRequest qnaSaveRequest, AuthUser user) {
        Long userId = user.getUserId();
        QnaStatus qnaStatus = QnaStatus.HOLD;

        if (qnaMapper.save(userId, qnaStatus, qnaSaveRequest) != 1) { throw new WriteFailedException(); }

        QnaSaveResponse qnaSaveResponse = qnaMapper.selectAfterSave(qnaSaveRequest.getQnaId());

        return qnaSaveResponse;
    }
    public PageResponse getList(AuthUser user,PageRequest pageRequest){
        Long userId = user.getUserId();

        List<PageResponse> list ;
        long total ;

         if (user == null || user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_MEMBER"))) {
             list = qnaMapper.pageFindById(userId ,pageRequest);
             total = qnaMapper.count(userId);

        }else{
             list = qnaMapper.adminSelectAll(pageRequest);
             total = qnaMapper.adminCount();

         }

        return PageResponse.of(pageRequest, list, total);
    }


    public QnaResponse getRead(Long qnaId, AuthUser user) {

        if (user != null) {

            alarmService.readAlarm(user.getUserId(), AlarmType.QNA_REPLY, qnaId);
            alarmService.readAlarm(user.getUserId(), AlarmType.QNA_COMMENT, qnaId);
        }

        QnaResponse qnaResponse = qnaMapper.selectById(qnaId);

        if (qnaResponse == null || qnaResponse.equals(0)) {
            throw new ReadFailedException();
        }

        qnaMapper.viewCount(qnaId);

        List<QnaCommentResponse> comments = qnaCommentMapper.selectByQnaId(qnaResponse.getQnaId());
        List<QnaReplyResponse> replies = qnaReplyMapper.selectByQnaId(qnaResponse.getQnaId());

        qnaResponse.setComments(comments);
        qnaResponse.setReplies(replies);

        return qnaResponse;
    }




    public QnaEditResponse modify(Long qnaId, QnaEditRequest qnaEditRequest, AuthUser user) {
        Long userId = user.getUserId();

        QnaResponse result = qnaMapper.selectIsDeleteTrueAndFalseById(qnaId);
        if (result == null) { throw new QnaNotFoundException(); }

        if (qnaMapper.editById(qnaId, qnaEditRequest, userId) != 1) {
            throw new WriteFailedException();
        }
        QnaEditResponse qnaEditResponse = qnaMapper.selectAfterEdit(qnaId);
        return qnaEditResponse;
    }

//    public QnaResponse getQnaById( Long qnaId) {
//        return Optional.of(qnaMapper.findQnaById(qnaId)).orElseThrow(FeedNotFoundException::new);
//    }

    public List<MyPageQnaResponse> getQnasByUserId(Long userId) {
        List<Qna> qnas = qnaRepository.findTop10ByUserIdOrderByCreatedAtDesc(userId);
        return qnas.stream()
                .map(MyPageQnaResponse::of)
                .collect(Collectors.toList());
    }


}