package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.AlarmMapper;
import bucheon.leafy.application.mapper.NoticeMapper;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.notice.NoticeDto;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.exception.*;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
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
    private final AlarmService alarmService;
    private final AlarmMapper alarmMapper;


    public int remove(Long id) {
        if (noticeMapper.deleteById(id) != 1) {
            throw new RemoveFailedException();
        }
        return noticeMapper.deleteById(id);
    }


    public void write(AuthUser authUser, NoticeDto noticeDto)  {

        Long userId = authUser.getUserId();


        // 공지 저장
        if (noticeMapper.save(noticeDto) != 1) {
            throw new WriteFailedException();
        }
        // 모든 사용자에게 알림 보내기
        List<Long> userIds = noticeMapper.findAllUserIds();
        for (Long id : userIds) {
            // 자신에게는 알림을 보내지 않도록 조건 추가
            if (!id.equals(userId)) {
                alarmService.createAlarm(id, AlarmType.NOTICE, noticeDto.getNoticeId());
            }
        }
    }

    public List<PageResponse> getList(PageRequest pageRequest)  {
        return noticeMapper.pageFindById(pageRequest); }

    @Transactional
    public NoticeDto getRead(Long id) {

        if (noticeMapper.findById(id) == null) {
            throw new ReadFailedException();
        }
        noticeMapper.viewCnt(id);

        return noticeMapper.findById(id);
    }
    public NoticeDto modify(NoticeDto noticeDto)  {

        if (noticeMapper.editById(noticeDto) != 1) {
            throw new ModifyFailedException();
        }
        Long noticeId = noticeDto.getNoticeId();
        return noticeMapper.findById(noticeId);
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
