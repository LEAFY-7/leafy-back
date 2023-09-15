package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaCommentMapper;
import bucheon.leafy.application.mapper.QnaMapper;

import bucheon.leafy.application.mapper.QnaReplyMapper;
import bucheon.leafy.application.repository.QnaRepository;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.qna.QnaType;
import bucheon.leafy.domain.qna.comment.response.QnaCommentResponse;
import bucheon.leafy.domain.qna.Qna;
import bucheon.leafy.domain.qna.request.QnaEditRequest;
import bucheon.leafy.domain.qna.request.QnaSaveRequest;
import bucheon.leafy.domain.qna.response.*;
import bucheon.leafy.domain.qna.reply.response.QnaReplyResponse;
import bucheon.leafy.exception.*;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static bucheon.leafy.domain.qna.QnaType.HOLD;

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
        if (result == null) {
            throw new QnaNotFoundException();
        }


        if (result.getUserId() != user.getUserId()) {
            throw new QnaNotFoundException();
        }

        if (qnaMapper.deleteById(qnaId) != 1) {
            throw new RemoveFailedException();
        }

    }

    public QnaSaveResponse write(QnaSaveRequest qnaSaveRequest, AuthUser user) {
        Long userId = user.getUserId();
        QnaType qnaStatus = HOLD;

        if (qnaMapper.save(userId, qnaStatus, qnaSaveRequest) != 1) {
            throw new WriteFailedException();
        }
        QnaSaveResponse qnaSaveResponse = qnaMapper.selectAfterSave(qnaSaveRequest.getQnaId());
        return qnaSaveResponse;
    }

    public PageResponse getList(AuthUser user, PageRequest pageRequest) {
        Long userId = user.getUserId();

        List<PageResponse> list;
        long total;

        if (user == null || user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_MEMBER"))) {
            list = qnaMapper.pageFindById(userId, pageRequest);
            total = qnaMapper.count(userId);

        } else {
            list = qnaMapper.adminSelectAll(pageRequest);
            total = qnaMapper.adminCount();

        }

        return PageResponse.of(pageRequest, list, total);
    }

    public QnaResponse getRead(Long qnaId, AuthUser user) {
        Long userId = user.getUserId();

        if (userId != null) {
            List<Long> qnaCommentIds = qnaCommentMapper.selectQnaCommentIdByQnaId(qnaId);
            if (qnaCommentIds != null) {
                for (long qnaCommentId : qnaCommentIds) {
                    alarmService.readAlarm(user.getUserId(), AlarmType.QNA_COMMENT, qnaCommentId);
                }
            }

            List<Long> qnaReplyIds = qnaReplyMapper.selectQnaReplyIdByQnaId(qnaId);
            if (qnaReplyIds != null) {
                for (Long qnaReply : qnaReplyIds) {
                    alarmService.readAlarm(user.getUserId(), AlarmType.QNA_REPLY, qnaReply);
                }
            }

            QnaResponse qnaResponse = qnaMapper.selectById(qnaId);

            if (qnaResponse == null) {
                throw new ReadFailedException();
            }

            qnaMapper.viewCount(qnaId);

            List<QnaCommentResponse> comments = qnaCommentMapper.selectByQnaId(qnaResponse.getQnaId());
            List<QnaReplyResponse> replies = qnaReplyMapper.selectByQnaId(qnaResponse.getQnaId());

            qnaResponse.setComments(comments);
            qnaResponse.setReplies(replies);

            return qnaResponse;
        }

        // 유저가 인증되지 않은 경우 403 오류를 던집니다.
        throw new ReadFailedException();
    }



        public QnaEditResponse modify (Long qnaId, QnaEditRequest qnaEditRequest, AuthUser user){
            Long userId = user.getUserId();

            QnaResponse result = qnaMapper.selectIsDeleteTrueAndFalseById(qnaId);
            if (result == null) {
                throw new QnaNotFoundException();
            }

            if (qnaMapper.editById(qnaId, qnaEditRequest, userId) != 1) {
                throw new WriteFailedException();
            }
            QnaEditResponse qnaEditResponse = qnaMapper.selectAfterEdit(qnaId);
            return qnaEditResponse;
        }

        public List<MyPageQnaResponse> getQnasByUserId (Long userId){
            List<Qna> qnas = qnaRepository.findTop10ByUserIdOrderByCreatedAtDesc(userId);
            return qnas.stream()
                    .map(MyPageQnaResponse::of)
                    .collect(Collectors.toList());
        }
    }
