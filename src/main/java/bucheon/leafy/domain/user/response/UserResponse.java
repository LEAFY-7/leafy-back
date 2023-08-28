package bucheon.leafy.domain.user.response;

import bucheon.leafy.domain.user.User;
import lombok.Builder;
import lombok.Data;

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

    private Boolean isHide;

    private Boolean isAdmin;


    @Builder
    public UserResponse(Long userId, String email, String name, String nickName,
                        String phone, String profileImage, String backgroundImage,
                        String introduction, Boolean isAdmin, Boolean isHide) {

        this.userId = userId;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.profileImage = profileImage;
        this.backgroundImage = backgroundImage;
        this.introduction = introduction;
        this.isHide = isHide;
        this.isAdmin = isAdmin;
    }

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .nickName(user.getNickName())
                .phone(user.getPhone())
                .profileImage(
                        user.getUserImage() != null ? ABSOLUTE_PATH + USER_IMAGE_PATH + user.getUserImage().getImage()
                                : ABSOLUTE_PATH + USER_IMAGE_PATH + DEFAULT_IMAGE
                )
                .backgroundImage(
                        user.getUserBackgroundImage() != null ? ABSOLUTE_PATH + USER_BACKGROUND_IMAGE_PATH + user.getUserBackgroundImage().getImage()
                                : ABSOLUTE_PATH + USER_BACKGROUND_IMAGE_PATH + DEFAULT_IMAGE
                )
                .introduction(user.getIntroduction())
                .isHide(user.getIsHide())
                .isAdmin(
                        user.getUserRole().equals(ADMIN) ? true : false
                )
                .build();
    }

}
