package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaMapper;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.exception.ReadFailedException;
import bucheon.leafy.exception.RemoveFailedException;
import bucheon.leafy.exception.UserNotFoundException;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final UserRepository userRepository;
    private final QnaMapper qnaMapper;
    private final AlarmService alarmService;

    public boolean remove(Long id) {

        boolean deleteStatus = qnaMapper.delete(id);

        if (!deleteStatus) {
            throw new RemoveFailedException();
        }

        return true;
    }
    public Long write(QnaDto qnaDto) {
        return qnaMapper.insert(qnaDto);
    }

    public PageResponse<QnaDto> getList(Long userId, PageRequest pageRequest) {
        PageResponse<QnaDto> qnaDto = qnaMapper.selectAll(userId, pageRequest);
        return qnaDto;
    }

    public PageResponse<QnaDto> admingetList(PageRequest pageRequest){
        return qnaMapper.adminSelectAll(pageRequest);
    }
    @Transactional
    public QnaDto getRead(Long id) {
        QnaDto qnaDto = qnaMapper.select(id);

        if (qnaDto == null) {
            throw new ReadFailedException();
        }

        qnaMapper.viewCnt(id);

        return qnaMapper.select(id);
    }
    public int modify(QnaDto qnaDto,Long id) {
        return qnaMapper.update(qnaDto, id);
    }
    public int qnaStatusModify(Long id) {
        return qnaMapper.qnaStatusModify(id);
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public GetMeResponse getMe(Long userId) {
        User user = getUserById(userId);
        return GetMeResponse.of(user);
    }
}