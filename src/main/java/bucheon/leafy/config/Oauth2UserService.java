package bucheon.leafy.config;

import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.LoginType;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.ExistException;
import bucheon.leafy.exception.OAuth2InfoNotExistException;
import bucheon.leafy.exception.UserNotFoundException;
import bucheon.leafy.jwt.TokenProvider;
import bucheon.leafy.jwt.TokenResponse;
import bucheon.leafy.oauth.OauthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static bucheon.leafy.exception.enums.ExceptionKey.EMAIL;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class Oauth2UserService extends DefaultOAuth2UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public TokenResponse oauthLogin(OauthRequest oauthRequest) throws OAuth2AuthenticationException {

        if (oauthRequest.getProvider().equals("kakao")){
            oauthRequest.setLoginType(LoginType.KAKAO);
        } else if (oauthRequest.getProvider().equals("google")) {
            oauthRequest.setLoginType(LoginType.GOOGLE);
        }

        if (oauthRequest.getEmail() == null || oauthRequest.getName() == null) {
            throw new OAuth2InfoNotExistException();
        }

        Optional<User> optionalUser = userRepository.findByEmail( oauthRequest.getEmail() );
        oauthRequest.setPassword(oauthRequest.getPassword());

        User user;
        if (optionalUser.isEmpty()) {
            User newUser = User.of(oauthRequest);
            user = userRepository.save(newUser);
        }
        else {
            if (optionalUser.get().getLoginType().equals(LoginType.KAKAO)) {
                user = optionalUser.get();
            } else {
                throw new ExistException(EMAIL);
            }
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), oauthRequest.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        String authority = authentication.getAuthorities().stream()
                .map(g -> g.getAuthority()).findFirst()
                .orElseThrow(UserNotFoundException::new);

        String role = authority.replace("ROLE_", "");
        String jwt = tokenProvider.createToken(authentication);
        return TokenResponse.of(jwt, role, authUser.getUserId());
    }

}
