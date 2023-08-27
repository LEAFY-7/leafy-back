package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.AlarmMapper;
import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.qna.request.QnaEditRequest;
import bucheon.leafy.domain.qna.request.QnaSaveRequest;
import bucheon.leafy.domain.qna.response.QnaResponse;
import bucheon.leafy.domain.qna.response.QnaSaveResponse;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.exception.*;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final UserRepository userRepository;
    private final QnaMapper qnaMapper;
    private final AlarmMapper alarmMapper;


    public boolean remove(Long qnaId) {
        boolean deleteStatus = qnaMapper.deleteById(qnaId);
        if (!deleteStatus) {
            throw new RemoveFailedException();
        }
        return true;
    }

    public QnaSaveResponse write(QnaSaveRequest qnaSaveRequest) {

        if (qnaMapper.save(qnaSaveRequest) != 1) {
            throw new WriteFailedException();
        }

        QnaSaveResponse qnaSaveResponse = qnaMapper.saveResponse(qnaSaveRequest);

        return qnaSaveResponse;
    }

    public PageResponse admingetList(PageRequest pageRequest){
        List<PageResponse> list = qnaMapper.adminSelectAll(pageRequest);
        long total = qnaMapper.count();
        PageResponse pageResponse = PageResponse.of(pageRequest, list, total);
        return pageResponse;
    }
    public PageResponse getList(Long qnaId, Long userId, PageRequest pageRequest){
        List<PageResponse> list = qnaMapper.pageFindById(qnaId, userId, pageRequest);
        long total = qnaMapper.count();
        PageResponse pageResponse = PageResponse.of(pageRequest, list, total);

        return pageResponse;

    }

    @Transactional
    public QnaResponse getRead(Long qnaId) {

        QnaResponse qnaResponse = qnaMapper.findById(qnaId);

        if (qnaResponse == null) {
            throw new ReadFailedException();
        }
        qnaMapper.viewCnt(qnaId);
        qnaMapper.editByIdQnaStatus(qnaId);

        return qnaMapper.findById(qnaId);
    }
    public int modify(QnaEditRequest qnaEditRequest, Long qnaId) {
        return qnaMapper.editById(qnaEditRequest, qnaId);
    }

    public Long getQnaById( Long qnaId) {
        return Optional.of(qnaMapper.findQnaById(qnaId)).orElseThrow(FeedNotFoundException::new);
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public GetMeResponse getMe(Long userId) {
        User user = getUserById(userId);
        int alarmCount = alarmMapper.countByUserId(userId);
        return GetMeResponse.of(user, alarmCount);
    }
}