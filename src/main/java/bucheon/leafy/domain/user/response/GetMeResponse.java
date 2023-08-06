package bucheon.leafy.domain.user.response;

import bucheon.leafy.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import static bucheon.leafy.domain.user.UserRole.ADMIN;

@Getter
public class GetMeResponse {

    private Long userId;

    private Boolean isAdmin;

    private int alarmCount;

    @Builder
    private GetMeResponse(Long userId, Boolean isAdmin, int alarmCount) {
        this.userId = userId;
        this.isAdmin = isAdmin;
        this.alarmCount = alarmCount;
    }

    public static GetMeResponse of(User user, int alarmCount) {
        return GetMeResponse.builder()
                .userId(user.getId())
                .isAdmin( user.getUserRole().equals(ADMIN) ? true : false )
                .alarmCount(alarmCount)
                .build();
    }

}
