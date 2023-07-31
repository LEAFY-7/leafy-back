package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaMapper;
<<<<<<< HEAD
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

=======
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.domain.qna.SearchHandler;
import bucheon.leafy.exception.enums.ReadFailedException;
import lombok.RequiredArgsConstructor;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


<<<<<<< HEAD
=======

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {

<<<<<<< HEAD
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
=======
    private final QnaMapper qnaMapper;

    public int getCount() {
        return qnaMapper.count();
    }
    public int remove(Long id, Long userId) {
        return qnaMapper.delete(id, userId);
    }
    public int adminRemove(Long id) {
        return qnaMapper.adminDelete(id);
    }

    public int write(QnaDto qnaDto) {
        return qnaMapper.insert(qnaDto);
    }

    public List<QnaDto> getList() {
        return qnaMapper.selectAll();
    }

>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    @Transactional
    public QnaDto getRead(Long id) {
        QnaDto qnaDto = qnaMapper.select(id);

        if (qnaDto == null) {
            throw new ReadFailedException();
        }

<<<<<<< HEAD
        qnaMapper.viewCnt(id);

        return qnaMapper.select(id);
    }
    public int modify(QnaDto qnaDto,Long id) {
        return qnaMapper.update(qnaDto, id);
=======
        qnaMapper.increaseViewCnt(id);

        return qnaMapper.select(id);
    }
    public int modify(QnaDto qnaDto) {
        return qnaMapper.update(qnaDto);
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    }
    public int qnaStatusModify(Long id) {
        return qnaMapper.qnaStatusModify(id);
    }
<<<<<<< HEAD

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public GetMeResponse getMe(Long userId) {
        User user = getUserById(userId);
        return GetMeResponse.of(user);
=======
    public int getSearchResultCnt(SearchHandler searchHandler) {
        return qnaMapper.searchResultCnt(searchHandler);
    }
    public List<QnaDto> getSearchResultPage(SearchHandler searchHandler) {
        return qnaMapper.searchSelectPage(searchHandler);
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
    }
}