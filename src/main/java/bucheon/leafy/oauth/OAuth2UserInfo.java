package bucheon.leafy.oauth;

import bucheon.leafy.domain.user.LoginType;

public interface OAuth2UserInfo {
    String getProviderId();
    LoginType getProvider();
    String getEmail();
    String getNickname();
}
