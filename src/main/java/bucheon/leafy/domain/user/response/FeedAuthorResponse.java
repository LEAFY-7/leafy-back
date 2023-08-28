package bucheon.leafy.domain.user.response;

import lombok.Builder;
import lombok.Data;

import static bucheon.leafy.path.S3Path.*;

@Data
public class FeedAuthorResponse {
    private Long userId;
    private String userNickName;
    private String profileImage;

    @Builder
    public FeedAuthorResponse(Long userId, String userNickName, String profileImage) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.profileImage = profileImage != null ? ABSOLUTE_PATH + USER_IMAGE_PATH + profileImage
                : ABSOLUTE_PATH + USER_IMAGE_PATH + DEFAULT_IMAGE;
    }

}
