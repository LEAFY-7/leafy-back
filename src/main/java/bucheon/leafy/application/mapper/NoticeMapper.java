package bucheon.leafy.application.mapper;


import bucheon.leafy.domain.comment.request.QnaCommentSaveRequest;
import bucheon.leafy.domain.notice.request.NoticeEditRequest;
import bucheon.leafy.domain.notice.request.NoticeSaveRequest;
import bucheon.leafy.domain.notice.response.NoticeResponse;
import bucheon.leafy.util.request.PageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface NoticeMapper {
    int count();
    int adminNoticeCount();
    NoticeResponse findById(Long noticeId);
    NoticeResponse findByIdAndIsDeleteFalse(Long noticeId);
    NoticeResponse findByIdAndIsDeleteFalseAndIsHideFalse(Long noticeId);
    int viewCnt(Long noticeId);
    int deleteById(Long userId, Long noticeId);
    Long save(@Param("userId")Long userId, @Param("noticeSaveRequest")NoticeSaveRequest noticeSaveRequest);
    Long editById(@Param("userId")Long userId, @Param("noticeId")Long noticeId, @Param("noticeEditRequest")NoticeEditRequest noticeEditRequest);
    List<NoticeResponse> pageFindById(@Param("pageRequest") PageRequest pageRequest);
    List<NoticeResponse> adminPageFindById(@Param("pageRequest") PageRequest pageRequest);
    List<Long> findAllUserIds();

}