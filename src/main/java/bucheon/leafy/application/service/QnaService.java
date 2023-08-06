package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.AlarmMapper;
import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.search.response.SearchResponse;
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
import static bucheon.leafy.domain.alarm.AlarmType.QNA;


@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final UserRepository userRepository;
    private final QnaMapper qnaMapper;
    private final AlarmService alarmService;
    private final AlarmMapper alarmMapper;


    public boolean remove(Long id) {
        boolean deleteStatus = qnaMapper.deleteById(id);
        if (!deleteStatus) {
            throw new RemoveFailedException();
        }
        return true;
    }

    public Long write(QnaDto qnaDto) { return qnaMapper.save(qnaDto); }

    public PageResponse admingetList(PageRequest pageRequest,Long id){
//        qnaMapper.pageFindById(id);
        return qnaMapper.adminSelectAll(pageRequest);
    }
    public PageResponse getList(Long userId, PageRequest pageRequest, Long id){
        List<PageResponse> list = qnaMapper.pageFindById(id, pageRequest);
        long total = qnaMapper.count();
        PageResponse pageResponse = PageResponse.of(pageRequest, list, total);

        return pageResponse;
//        return qnaMapper.selectAll(userId, pageRequest);
    }
    public Long findTableIdByUserId(Long userId) { return qnaMapper.findTableIdByUserId(userId); }

    @Transactional
    public QnaDto getRead(Long userId, Long id) {

        QnaDto qnaDto = qnaMapper.findById(id);

        if (qnaDto == null) {
            throw new ReadFailedException();
        }

        String msg = "답장이 완료 됬습니다.";

        Long tableId = findTableIdByUserId(userId);

        alarmService.createAlarm(userId, QNA, tableId, msg);
        qnaMapper.viewCnt(id);
        qnaMapper.qnaStatusModify(id);

        return qnaMapper.findById(id);
    }
    public int modify(QnaDto qnaDto,Long id) {
        return qnaMapper.editById(qnaDto, id);
    }

    public QnaDto getQnaById(Long qnaId) {
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