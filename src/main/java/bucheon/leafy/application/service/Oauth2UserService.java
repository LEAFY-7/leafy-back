package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.user.LoginType;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.OAuth2InfoNotExistException;
import bucheon.leafy.oauth.GoogleUserInfo;
import bucheon.leafy.oauth.KakaoUserInfo;
import bucheon.leafy.oauth.OAuth2UserInfo;
import bucheon.leafy.oauth.OauthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class Oauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("요청 들어옴 : {}", userRequest);
        OAuth2User oAuth2User = super.loadUser(userRequest);

        LoginType loginType = LoginType.NORMAL;
        String image;

        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals(LoginType.GOOGLE)) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
            loginType = LoginType.GOOGLE;
        } else if (userRequest.getClientRegistration().getRegistrationId().equals(LoginType.KAKAO)) {
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
            loginType = LoginType.KAKAO;
        }

        if (oAuth2UserInfo == null) {
            throw new OAuth2InfoNotExistException();
        }

        String email = oAuth2UserInfo.getEmail();
        String name = oAuth2UserInfo.getNickname();

        Optional<User> optionalUser = userRepository.findByEmail(email);
        String password = "oauth login password";

        User user;
        if (optionalUser.isEmpty()) {
            OauthRequest oauthRequest = OauthRequest.builder()
                    .email(email)
                    .name(name)
                    .password(password)
                    .loginType(loginType)
                    .build();

            User newUser = User.of(oauthRequest);
            user = userRepository.save(newUser);
        } else {
            user = optionalUser.get();
        }

        return new AuthUser(user, oAuth2User.getAttributes());
    }

}
