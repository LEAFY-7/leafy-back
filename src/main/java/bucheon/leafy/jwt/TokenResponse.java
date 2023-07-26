package bucheon.leafy.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenResponse {

    private String token;
    private String userAuth;
    private Long userId;

    public TokenResponse(String token, String userAuth) {
        this.token = token;
        this.userAuth = userAuth;
    }

    public void addUserId(Long userId){
        this.userId = userId;
    }

}
