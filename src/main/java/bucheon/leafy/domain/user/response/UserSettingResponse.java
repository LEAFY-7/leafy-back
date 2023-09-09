package bucheon.leafy.domain.user.response;

import bucheon.leafy.domain.user.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserSettingResponse {

    private Boolean isHide;

    private Boolean isAllNotifications;

    private Boolean isCommentNotifications;


    @Builder
    public UserSettingResponse(Boolean isHide, Boolean isAllNotifications, Boolean isCommentNotifications) {
        this.isHide = isHide;
        this.isAllNotifications = isAllNotifications;
        this.isCommentNotifications = isCommentNotifications;
    }

    public static UserSettingResponse of(User user) {
        return UserSettingResponse.builder()
                .isHide(user.getIsHide())
                .isAllNotifications(user.getIsAllNotifications())
                .isCommentNotifications(user.getIsCommentNotifications())
                .build();
    }

}
