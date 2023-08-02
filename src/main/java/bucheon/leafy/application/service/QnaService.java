package bucheon.leafy.application.service;

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
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

    private final UserRepository userRepository;
    private final QnaMapper qnaMapper;
    private final AlarmService alarmService;

    public boolean remove(Long id) {

        boolean deleteStatus = qnaMapper.deleteById(id);

        if (!deleteStatus) {
            throw new RemoveFailedException();
        }

        return true;
    }
    public Long write(QnaDto qnaDto) {
        return qnaMapper.save(qnaDto);
    }
    public QnaDto admingetList(PageRequest pageRequest){
        return qnaMapper.adminSelectAll(pageRequest);
    }

    public QnaDto getList(PageRequest pageRequest){
        return qnaMapper.adminSelectAll(pageRequest);
    }
    @Transactional
    public QnaDto getRead(Long id) {
        QnaDto qnaDto = qnaMapper.findById(id);

        if (qnaDto == null) {
            throw new ReadFailedException();
        }

        qnaMapper.viewCnt(id);

        return qnaMapper.findById(id);
    }
    public int modify(QnaDto qnaDto,Long id) {
        return qnaMapper.editById(qnaDto, id);
    }
    public int qnaStatusModify(Long id) {
        return qnaMapper.qnaStatusModify(id);
    }


    public QnaDto getQnaById(Long qnaId) {
        return Optional.of(qnaMapper.findQnaById(qnaId)).orElseThrow(FeedNotFoundException::new);
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public GetMeResponse getMe(Long userId) {
        User user = getUserById(userId);
        return GetMeResponse.of(user);
    }
}