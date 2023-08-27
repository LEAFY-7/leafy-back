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
        NoticeResponse result = noticeMapper.findByIdAndIsDeleteFalse(noticeId);

        if(result == null){ throw new NoticeNotFoundException(); }

        if(result.getUserId() != user.getUserId()){ throw new NoticeNotUserIdException(); }

        if (noticeMapper.deleteById(noticeId) != 1) {
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

    //TODO 어드민이랑 유저(비회원도)랑 리스트 다르게 뿌리기 (어드민은 isHide true 인것도 보여줘야함)
    public PageResponse getList(PageRequest pageRequest)  {
        List<NoticeResponse> list = noticeMapper.pageFindById(pageRequest);
        long total = noticeMapper.count();
        PageResponse pageResponse = PageResponse.of(pageRequest, list, total);

        return pageResponse;
    }

    //TODO 어드민이랑 유저(비회원도)랑 리스트 다르게 뿌리기 (어드민은 isHide true 인것도 보여줘야함)
    public NoticeResponse getRead(Long noticeId) {
        if (noticeMapper.findByIdAndIsDeleteFalseAndIsHideFalse(noticeId) == null) {
            throw new NoticeNotFoundException();
        }
        noticeMapper.viewCnt(noticeId);

        return noticeMapper.findByIdAndIsDeleteFalseAndIsHideFalse(noticeId);
    }


    public NoticeEditResponse modify(Long noticeId, NoticeEditRequest noticeEditRequest, AuthUser user)  {

        NoticeResponse result = noticeMapper.findByIdAndIsDeleteFalse(noticeId);

        if(result == null){ throw new NoticeNotFoundException(); }

        if(result.getUserId() != user.getUserId()){ throw new NoticeNotUserIdException(); }


        if (noticeMapper.editById(noticeId, noticeEditRequest) != 1) {
            throw new ModifyFailedException();
        }
        NoticeEditResponse noticeEditResponse = noticeMapper.eidtfind(noticeEditRequest);

        NoticeResponse editResult = noticeMapper.findByIdAndIsDeleteFalse(noticeId);
        return NoticeEditResponse.of(editResult.getModifiedAt());
    }


}