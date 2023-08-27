package bucheon.leafy.application.service;

import bucheon.leafy.application.controller.response.FeedByIdResponse;
import bucheon.leafy.application.mapper.AlarmMapper;
import bucheon.leafy.application.mapper.NoticeMapper;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.notice.Notice;
import bucheon.leafy.domain.notice.request.NoticeEditRequest;
import bucheon.leafy.domain.notice.request.NoticeSaveRequest;
import bucheon.leafy.domain.notice.response.NoticeEditResponse;
import bucheon.leafy.domain.notice.response.NoticeResponse;
import bucheon.leafy.domain.notice.response.NoticeSaveResponse;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.exception.*;
import bucheon.leafy.util.request.PageRequest;
import bucheon.leafy.util.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final UserRepository userRepository;
    private final NoticeMapper noticeMapper;
    private final AlarmService alarmService;
    private final AlarmMapper alarmMapper;


    public int remove(Long noticeId) {
        if (noticeMapper.deleteById(noticeId) != 1) {
            throw new RemoveFailedException();
        }
        return noticeMapper.deleteById(noticeId);
    }


    public NoticeSaveResponse write(NoticeSaveRequest noticeSaveRequest) {


        if (noticeMapper.save(noticeSaveRequest) != 1) {
            throw new WriteFailedException();
        }
        NoticeSaveResponse noticeSaveResponse = noticeMapper.saveResponse(noticeSaveRequest);

        Long authorUserId = noticeSaveRequest.getUserId();

        List<Long> userIds = noticeMapper.findAllUserIds();
        for (Long id : userIds) {
            if (!id.equals(authorUserId)) {
                alarmService.createAlarm(id, AlarmType.NOTICE, noticeSaveResponse.getNoticeId());
            }
        }

        return noticeSaveResponse;
    }


    public PageResponse getList(PageRequest pageRequest)  {

        List<PageResponse> list = noticeMapper.pageFindById(pageRequest);
        long total = noticeMapper.count();
        PageResponse pageResponse = PageResponse.of(pageRequest, list, total);

        return pageResponse;
    }

    @Transactional
    public NoticeResponse getRead(Long noticeId) {

        if (noticeMapper.findById(noticeId) == null) {
            throw new ReadFailedException();
        }
        noticeMapper.viewCnt(noticeId);

        return noticeMapper.findById(noticeId);
    }
    public NoticeEditResponse modify(Long noticeId, NoticeEditRequest noticeEditRequest)  {

        if (noticeMapper.editById(noticeId, noticeEditRequest) == 0) {
            throw new ModifyFailedException();
        }

        return noticeMapper.modifiedAt(noticeId);
    }

    public void hideByNoticeId(Long noticeId){
        noticeMapper.hideByNoticeId(noticeId);
    };
    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public GetMeResponse getMe(Long userId) {
        User user = getUserById(userId);
        int alarmCount = alarmMapper.countByUserId(userId);
        return GetMeResponse.of(user, alarmCount);
    }
}
