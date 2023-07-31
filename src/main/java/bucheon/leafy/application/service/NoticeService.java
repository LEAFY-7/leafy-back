package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.NoticeMapper;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.ReadFailedException;
import bucheon.leafy.exception.RemoveFailedException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

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
        return noticeMapper.insert(noticeDto);
    }

    public List<NoticeDto> getList()  {
        return noticeMapper.selectAll();
    }

    @Transactional
    public NoticeDto getRead(Long id) {

        if (noticeMapper.findById(id) == null) {
            throw new ReadFailedException();
        }
        noticeMapper.viewCnt(id);

        return noticeMapper.findById(id);
    }
    public NoticeDto modify(NoticeDto noticeDto)  {

        if (noticeMapper.update(noticeDto) != 1) {
            throw new ModifyFailedException();
        }
        Long id = noticeDto.getId();
        return noticeMapper.findById(id);
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public GetMeResponse getMe(Long userId) {
        User user = getUserById(userId);
        return GetMeResponse.of(user);
    }
}
