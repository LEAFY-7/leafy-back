package bucheon.leafy.domain.follow.response;

import bucheon.leafy.domain.user.User;
import lombok.Builder;
import lombok.Data;

import static bucheon.leafy.path.S3Path.ABSOLUTE_PATH;
import static bucheon.leafy.path.S3Path.USER_IMAGE_PATH;

@Data
public class FollowersResponse {

    private Long id;
    private String email;
    private String nickName;

    private String image;

    @Builder
    private FollowersResponse(Long id, String email, String nickName, String image) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.image = image;
    }

    public static FollowersResponse of(User user) {
        return FollowersResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .image(
                        user.getUserImage() != null ? ABSOLUTE_PATH + USER_IMAGE_PATH + user.getUserImage().getImage() : null
                )
                .build();
    }

}
