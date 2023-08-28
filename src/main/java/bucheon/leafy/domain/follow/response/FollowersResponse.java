package bucheon.leafy.domain.follow.response;

import bucheon.leafy.domain.user.User;
import lombok.Builder;
import lombok.Data;

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
        return FollowersResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .profileImage(
                        user.getUserImage() != null ? ABSOLUTE_PATH + USER_IMAGE_PATH + user.getUserImage().getImage()
                                : ABSOLUTE_PATH + USER_IMAGE_PATH + DEFAULT_IMAGE
                )
                .build();
    }

}
