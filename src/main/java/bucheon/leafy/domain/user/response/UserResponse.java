package bucheon.leafy.domain.user.response;

import bucheon.leafy.domain.user.LoginType;
import bucheon.leafy.domain.user.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.StringUtils;

import static bucheon.leafy.domain.user.UserRole.ADMIN;
import static bucheon.leafy.path.S3Path.*;

@Data
public class UserResponse {

    private Long userId;

    private String email;

    private String name;

    private String nickName;

    private String phone;

    private String profileImage;

    private String backgroundImage;

    private String introduction;

    private LoginType loginType;

    private Boolean isHide;

    private Boolean isAdmin;

    private Boolean isAllNotifications;

    private Boolean isCommentNotifications;


    @Builder
    public UserResponse(Long userId, String email, String name, String nickName,
                        String phone, String profileImage, String backgroundImage,
                        String introduction, Boolean isAdmin, Boolean isHide,
                        Boolean isAllNotifications, Boolean isCommentNotifications,
                        LoginType loginType) {

        this.userId = userId;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.profileImage = profileImage;
        this.backgroundImage = backgroundImage;
        this.introduction = introduction;
        this.loginType = loginType;
        this.isHide = isHide;
        this.isAdmin = isAdmin;
        this.isAllNotifications = isAllNotifications;
        this.isCommentNotifications = isCommentNotifications;
    }

    public static UserResponse of(User user) {
        String profileImage;

        if(user.getUserImage() != null) {

            if (StringUtils.startsWithIgnoreCase(user.getUserImage().getImage(), "http")) {
                profileImage = user.getUserImage().getImage();
            } else {
                profileImage = ABSOLUTE_PATH + USER_IMAGE_PATH + user.getUserImage().getImage();
            }

        } else {
            profileImage = ABSOLUTE_PATH + USER_IMAGE_PATH + DEFAULT_IMAGE;
        }

        return UserResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .nickName(user.getNickName())
                .phone(user.getPhone())
                .profileImage(profileImage)
                .backgroundImage(
                        user.getUserBackgroundImage() != null ? ABSOLUTE_PATH + USER_BACKGROUND_IMAGE_PATH + user.getUserBackgroundImage().getImage()
                                : ABSOLUTE_PATH + USER_BACKGROUND_IMAGE_PATH + DEFAULT_IMAGE
                )
                .introduction(user.getIntroduction())
                .isHide(user.getIsHide())
                .isAllNotifications(user.getIsAllNotifications())
                .isCommentNotifications(user.getIsCommentNotifications())
                .isAdmin(
                        user.getUserRole().equals(ADMIN) ? true : false
                )
                .loginType(user.getLoginType())
                .build();
    }

}
