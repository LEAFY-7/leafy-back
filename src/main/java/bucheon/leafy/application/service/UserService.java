package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.request.SignInRequest;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.domain.user.response.UserResponse;
import bucheon.leafy.exception.ExistException;
import bucheon.leafy.exception.UserNotFoundException;
import bucheon.leafy.exception.enums.ExceptionKey;
import bucheon.leafy.jwt.JwtFilter;
import bucheon.leafy.jwt.TokenProvider;
import bucheon.leafy.jwt.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public ResponseEntity signIn(SignInRequest signInRequest) {
        log.info("signIn : signInRequest = {}", signInRequest);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("signIn : SecurityContextHolder.getContext() = {}", SecurityContextHolder.getContext());

        String authority = authentication.getAuthorities().stream()
                .map(g -> g.getAuthority()).findFirst()
                .orElseThrow(UserNotFoundException::new);

        String role = authority.replace("ROLE_", "");

        String jwt = tokenProvider.createToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + refreshToken);

        return new ResponseEntity<>(new TokenResponse(jwt, role), httpHeaders, HttpStatus.OK);
    }

    public Long signUp(SignUpRequest signUpRequest) {

        userRepository.findByEmail(signUpRequest.getEmail())
                .ifPresent(u -> {
                    throw new ExistException(ExceptionKey.EMAIL);
                });

        User user = User.of(signUpRequest);

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        user.changePassword(encodedPassword);
        User saveUser = userRepository.save(user);
        return saveUser.getId();
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
