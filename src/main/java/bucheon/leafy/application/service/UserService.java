package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.request.SignInRequest;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.domain.user.response.UserResponse;
import bucheon.leafy.exception.ExistException;
import bucheon.leafy.exception.PasswordNotMatchedException;
import bucheon.leafy.exception.UserNotFoundException;
import bucheon.leafy.exception.enums.ExceptionKey;
import bucheon.leafy.jwt.TokenProvider;
import bucheon.leafy.jwt.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public TokenResponse signIn(SignInRequest signInRequest) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String authority = authentication.getAuthorities().stream()
                .map(g -> g.getAuthority()).findFirst()
                .orElseThrow(UserNotFoundException::new);

        String role = authority.replace("ROLE_", "");

        String jwt = tokenProvider.createToken(authentication);
//        String refreshToken = tokenProvider.createRefreshToken(authentication);

        return new TokenResponse(jwt, role);
    }

    public Long signUp(SignUpRequest signUpRequest) {
        String password = signUpRequest.getPassword();
        String confirmPassword = signUpRequest.getConfirmPassword();

        comparePasswords(password, confirmPassword);

        userRepository.findByEmail(signUpRequest.getEmail())
                .ifPresent(u -> {
                    throw new ExistException(ExceptionKey.EMAIL);
                });

        User user = User.of(signUpRequest);

        String encodedPassword = passwordEncoder.encode(password);
        user.changePassword(encodedPassword);
        User saveUser = userRepository.save(user);

        return saveUser.getId();
    }

    private void comparePasswords(String password, String confirmPassword) {
       if ( !password.equals(confirmPassword) ){
           throw new PasswordNotMatchedException();
       }
    }

    public ResponseEntity<String> duplicationIdCheck(String email) {
        userRepository.findByEmail(email)
                .ifPresent(u -> {
                    throw new ExistException(ExceptionKey.EMAIL);
                });

        return ResponseEntity.status(200).body("아이디가 사용 가능합니다.");
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public UserResponse getUserResponseByUserId(Long userId) {
        User user = getUserById(userId);
        return UserResponse.of(user);
    }

    public GetMeResponse getMe(Long userId) {
        User user = getUserById(userId);
        return GetMeResponse.of(user);
    }

}
