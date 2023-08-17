package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.AlarmMapper;
import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.exception.FeedNotFoundException;
import bucheon.leafy.exception.ReadFailedException;
import bucheon.leafy.exception.RemoveFailedException;
import bucheon.leafy.exception.UserNotFoundException;
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

    public Long write(QnaDto qnaDto) { return qnaMapper.save(qnaDto); }

    public PageResponse admingetList(PageRequest pageRequest){
//        qnaMapper.pageFindById(qnaId);
        return qnaMapper.adminSelectAll(pageRequest);
    }
    public PageResponse getList(Long qnaId, Long userId, PageRequest pageRequest){
        List<PageResponse> list = qnaMapper.pageFindById(qnaId, userId, pageRequest);
        long total = qnaMapper.count();
        PageResponse pageResponse = PageResponse.of(pageRequest, list, total);

        return pageResponse;

    }

    @Transactional
    public QnaDto getRead(Long qnaId, Long userId) {

        QnaDto qnaDto = qnaMapper.findById(qnaId, userId);

        if (qnaDto == null) {
            throw new ReadFailedException();
        }
        qnaMapper.viewCnt(qnaId);
        qnaMapper.editByIdQnaStatus(qnaId);

        return qnaMapper.findById(qnaId, userId);
    }
    public int modify(QnaDto qnaDto,Long qnaId) {
        return qnaMapper.editById(qnaDto, qnaId);
    }

    public QnaDto getQnaById( Long userId) {
        return Optional.of(qnaMapper.findQnaById(userId)).orElseThrow(FeedNotFoundException::new);
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