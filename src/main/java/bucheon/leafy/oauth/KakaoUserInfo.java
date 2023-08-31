package bucheon.leafy.oauth;

import bucheon.leafy.domain.user.LoginType;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public LoginType getProvider() {
        return LoginType.KAKAO;
    }

    @Override
    public String getEmail() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        return (String) account.get("email");
    }

    @Override
    public String getNickname() {
        Map<String, Object> profile = (Map<String, Object>) attributes.get("profile");
        return (String) profile.get("nickname");
    }

    public String getImage() {
        Map<String, Object> profile = (Map<String, Object>) attributes.get("image");
        Object image = profile.get("image");

        if (image == null) return null;
        return (String) image;
    }

}
