package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.NoticeMapper;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.notice.request.NoticeEditRequest;
import bucheon.leafy.domain.notice.request.NoticeSaveRequest;
import bucheon.leafy.domain.notice.response.NoticeEditResponse;
import bucheon.leafy.domain.notice.response.NoticeResponse;
import bucheon.leafy.domain.notice.response.NoticeSaveResponse;
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

    private final NoticeMapper noticeMapper;
    private final AlarmService alarmService;


    public void remove(Long noticeId, AuthUser user) {
        Long userId = user.getUserId();

        NoticeResponse result = noticeMapper.findByIdAndIsDeleteFalse(noticeId);

        if(result == null){ throw new NoticeNotFoundException(); }

        if (noticeMapper.deleteById(userId, noticeId) != 1) {
            throw new RemoveFailedException();
        }
    }


    public NoticeSaveResponse write(NoticeSaveRequest noticeSaveRequest, AuthUser user) {
        Long userId = user.getUserId();

        if (noticeMapper.save(userId, noticeSaveRequest) != 1) { throw new WriteFailedException(); }

        NoticeResponse result = noticeMapper.findById(noticeSaveRequest.getNoticeId());

        // 알림 발송
        List<Long> userIds = noticeMapper.findAllUserIds();
        for (Long id : userIds) {
            if (!id.equals(userId)) {
                alarmService.createAlarm(id, AlarmType.NOTICE, noticeSaveRequest.getNoticeId());
            }
        }

        return NoticeSaveResponse.of(noticeSaveRequest.getNoticeId(), result.getCreatedAt());
    }

    public PageResponse getList(PageRequest pageRequest, AuthUser user)  {
        List<NoticeResponse> list;
        long total;

        if(user == null || user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_MEMBER"))) { // 비회원, 멤버 회원은 비공개 공지사항 제외
            list = noticeMapper.pageFindById(pageRequest);
            total = noticeMapper.count();

        } else { // 어드민 회원은 비공개 공지사항 포함
            list = noticeMapper.adminPageFindById(pageRequest);
            total = noticeMapper.adminNoticeCount();
        }

        return PageResponse.of(pageRequest, list, total);
    }

    public NoticeResponse getRead(Long noticeId, AuthUser user) {

        if(user != null){alarmService.readAlarm(user.getUserId(), AlarmType.NOTICE, noticeId);} // 알림 미리 읽음으로 처리

        if(user == null || user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_MEMBER"))){ // 비회원, 멤버 회원은 비공개 공지사항 제외
            if (noticeMapper.findByIdAndIsDeleteFalseAndIsHideFalse(noticeId) == null) {
                throw new NoticeNotFoundException();
            }
            noticeMapper.viewCnt(noticeId);
            return noticeMapper.findByIdAndIsDeleteFalseAndIsHideFalse(noticeId);

        } else { // 어드민 회원은 비공개 공지사항 포함
            if (noticeMapper.findByIdAndIsDeleteFalse(noticeId) == null) {
                throw new NoticeNotFoundException();
            }
            noticeMapper.viewCnt(noticeId);
            return noticeMapper.findByIdAndIsDeleteFalse(noticeId);
        }
    }

    public NoticeEditResponse modify(Long noticeId, NoticeEditRequest noticeEditRequest, AuthUser user)  {
        Long userId = user.getUserId();

        NoticeResponse result = noticeMapper.findByIdAndIsDeleteFalse(noticeId);

        if(result == null){ throw new NoticeNotFoundException(); }

        if (noticeMapper.editById(userId, noticeId, noticeEditRequest) != 1) {
            throw new ModifyFailedException();
        }

        NoticeResponse editResult = noticeMapper.findByIdAndIsDeleteFalse(noticeId);
        return NoticeEditResponse.of(editResult.getModifiedAt());
    }


}