package bucheon.leafy.jwt;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponse {

    private String token;
    private String userAuth;
    private Long userId;

    @Builder
    private TokenResponse(String token, String userAuth, Long userId) {
        this.token = token;
        this.userAuth = userAuth;
        this.userId = userId;
    }

    public static TokenResponse of(String token, String userAuth, Long userId) {
        return TokenResponse.builder()
                .token(token)
                .userAuth(userAuth)
                .userId(userId)
                .build();
    }

}
