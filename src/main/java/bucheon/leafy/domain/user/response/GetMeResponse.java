package bucheon.leafy.domain.user.response;

import bucheon.leafy.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import static bucheon.leafy.domain.user.UserRole.ADMIN;

@Getter
public class GetMeResponse {

    private Long userId;

    private Boolean isAdmin;

    @Builder
    private GetMeResponse(Long userId, Boolean isAdmin) {
        this.userId = userId;
        this.isAdmin = isAdmin;
    }

    public static GetMeResponse of(User user) {
        return GetMeResponse.builder()
                .userId(user.getId())
                .isAdmin( user.getUserRole().equals(ADMIN) ? true : false )
                .build();
    }

}
