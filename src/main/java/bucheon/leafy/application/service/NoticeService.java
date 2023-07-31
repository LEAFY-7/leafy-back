package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.NoticeMapper;
<<<<<<< HEAD
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.ReadFailedException;
import bucheon.leafy.exception.RemoveFailedException;
import bucheon.leafy.exception.UserNotFoundException;
=======
import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.qna.QnaDto;
import bucheon.leafy.exception.enums.ReadFailedException;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde


@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

<<<<<<< HEAD
    private final UserRepository userRepository;
    private final NoticeMapper noticeMapper;

    public int remove(Long id) {
        if (noticeMapper.delete(id) != 1) {
            throw new RemoveFailedException();
        }
        return noticeMapper.delete(id);
    }
    public Long write(NoticeDto noticeDto)  {

        if (noticeMapper.insert(noticeDto) != 1) {
            throw new ReadFailedException();
        }
=======
    private final NoticeMapper noticeMapper;
    private final ReentrantLock lock = new ReentrantLock();

    public int getCount()  {
        return noticeMapper.count();
    }

    public int remove(Long id, Long userId)  {return noticeMapper.delete( id, userId); }

    public int write(NoticeDto noticeDto)  {
>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
        return noticeMapper.insert(noticeDto);
    }

    public List<NoticeDto> getList()  {
        return noticeMapper.selectAll();
    }

<<<<<<< HEAD
    @Transactional
    public NoticeDto getRead(Long id) {

        if (noticeMapper.select(id) == null) {
            throw new ReadFailedException();
        }
        noticeMapper.viewCnt(id);

        return noticeMapper.select(id);
    }
    public NoticeDto modify(NoticeDto noticeDto)  {

        if (noticeMapper.update(noticeDto) != 1) {
            throw new ModifyFailedException();
        }
        Long id = noticeDto.getId();
        return noticeMapper.select(id);
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public GetMeResponse getMe(Long userId) {
        User user = getUserById(userId);
        return GetMeResponse.of(user);
    }
=======
    public NoticeDto getRead(Long id) {

        lock.lock();
        try {
            NoticeDto noticeDto = noticeMapper.select(id);
            noticeMapper.increaseViewCnt(id);

            if (noticeDto == null) {
                throw new ReadFailedException();
            }

            return noticeDto;
        } finally {
            lock.unlock();
        }
    }

    public int modify(NoticeDto noticeDto)  {
        return noticeMapper.update(noticeDto);
    }

    public List<NoticeDto> getPage(Map map) throws Exception { return noticeMapper.selectPage(map);
    }

>>>>>>> 48dd2d34b88ed1d765648b11af5e585fb255cbde
}
