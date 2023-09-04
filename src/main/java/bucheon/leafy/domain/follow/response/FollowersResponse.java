package bucheon.leafy.domain.follow.response;

import bucheon.leafy.domain.user.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.StringUtils;

import static bucheon.leafy.path.S3Path.*;

@Data
public class FollowersResponse {

    private Long userId;
    private String email;
    private String nickName;

    private String profileImage;

    @Builder
    private FollowersResponse(Long userId, String email, String nickName, String profileImage) {
        this.userId = userId;
        this.email = email;
        this.nickName = nickName;
        this.profileImage = profileImage;
    }

    public static FollowersResponse of(User user) {
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

        return FollowersResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .profileImage(profileImage)
                .build();
    }

}
