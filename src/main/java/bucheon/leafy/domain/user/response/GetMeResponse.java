package bucheon.leafy.domain.user.response;

import bucheon.leafy.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import static bucheon.leafy.domain.user.UserRole.ADMIN;

@Getter
public class GetMeResponse {

    private Long userId;

    private UserResponse userResponse;

    private int alarmCount;

    @Builder
    private GetMeResponse(Long userId, UserResponse userResponse, int alarmCount) {
        this.userId = userId;
        this.userResponse = userResponse;
        this.alarmCount = alarmCount;
    }

    public static GetMeResponse of(User user, int alarmCount) {
        return GetMeResponse.builder()
                .userId(user.getId())
                .userResponse( UserResponse.of(user) )
                .alarmCount(alarmCount)
                .build();
    }

}
