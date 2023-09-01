package bucheon.leafy.oauth;

import bucheon.leafy.domain.user.LoginType;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
import java.util.regex.Matcher;

@Data
public class OauthRequest {

    private String email;

    private String name;

    private String password;
    private String encodedPassword;
    private String image;
    private String provider;
    private LoginType loginType;

    private String nickName = generateRandomNickname();

    @Builder
    private OauthRequest(String email, String name, String password, String encodedPassword,
                         LoginType loginType, String provider, String image) {
        this.password = password;
        this.encodedPassword = encodedPassword;
        this.email = email;
        this.name = name;
        this.loginType = loginType;
        this.provider = provider;
        this.image = image;
    }

    public static String generateRandomNickname() {
        String randomNickname;
        do {
            randomNickname = UUID.randomUUID().toString()
                    .replace("-", "").substring(0, 12);
        } while (!isValidNickname(randomNickname));
        return randomNickname;
    }

    private static final java.util.regex.Pattern NICKNAME_PATTERN
            = java.util.regex.Pattern.compile("^(?!admin|leafy)(?!.*\\s{2,})(?!.*\\s$)(?=.*[a-zA-Z0-9가-힣])[a-zA-Z0-9_가-힣\\s]{3,12}$");

    private static boolean isValidNickname(String nickname) {
        Matcher matcher = NICKNAME_PATTERN.matcher(nickname);
        return matcher.matches();
    }
}
