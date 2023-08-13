package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.AlarmMapper;
import bucheon.leafy.application.mapper.NoticeMapper;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.notice.Notice;
import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.exception.ModifyFailedException;
import bucheon.leafy.exception.ReadFailedException;
import bucheon.leafy.exception.RemoveFailedException;
import bucheon.leafy.exception.UserNotFoundException;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static bucheon.leafy.domain.alarm.AlarmType.NOTICE;


@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final UserRepository userRepository;
    private final NoticeMapper noticeMapper;
    private final AlarmService alarmService;
    private final AlarmMapper alarmMapper;


    public int remove(Long id) {
        if (noticeMapper.delete(id) != 1) {
            throw new RemoveFailedException();
        }
        return noticeMapper.delete(id);
    }
    public Long write(AuthUser user, NoticeDto noticeDto)  {

        if (noticeMapper.insert(noticeDto) != 1) {
            throw new ReadFailedException();
        }
        Long userId = user.getUserId();
        String msg = "글쓰기가 완료 됬습니다.";

        Long tableId = findTableIdByUserId(userId);
        alarmService.createAlarm(userId, AlarmType.NOTICE , tableId);
        return noticeMapper.insert(noticeDto);
    }

    public PageResponse getList(PageRequest pageRequest)  {
        noticeMapper.searchSelectPage(pageRequest);
        return noticeMapper.selectAll(); }

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
    public Long findTableIdByUserId(Long userId) { return noticeMapper.findTableIdByUserId(userId); }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public GetMeResponse getMe(Long userId) {
        User user = getUserById(userId);
        int alarmCount = alarmMapper.countByUserId(userId);
        return GetMeResponse.of(user, alarmCount);
    }
}
