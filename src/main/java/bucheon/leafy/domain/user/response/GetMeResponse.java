package bucheon.leafy.domain.user.response;

import bucheon.leafy.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetMeResponse {

    private UserResponse userResponse;

    private int alarmCount;

    @Builder
    private GetMeResponse( UserResponse userResponse, int alarmCount) {
        this.userResponse = userResponse;
        this.alarmCount = alarmCount;
    }

    public static GetMeResponse of(User user, int alarmCount) {
        return GetMeResponse.builder()
                .userResponse( UserResponse.of(user) )
                .alarmCount(alarmCount)
                .build();
    }

}
