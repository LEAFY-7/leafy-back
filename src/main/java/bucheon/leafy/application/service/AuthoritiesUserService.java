package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.config.jwt.JwtProperties;
import bucheon.leafy.config.jwt.JwtUtils;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.request.SignInRequest;
import bucheon.leafy.domain.user.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthoritiesUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity signUp(SignUpRequest signUpRequest) {
        User user = User.of(signUpRequest);
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        user.changePassword(encodedPassword);
        userRepository.save(user);
        return ResponseEntity.status(200).body("회원가입 완료");
    }

    public ResponseEntity signIn(SignInRequest signInRequest) {
        try {
            // 사용자 인증 수행
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
            );

            // 인증에 성공하면 JWT 토큰 생성 등의 추가 작업 수행
            AuthUser authUser = (AuthUser) authentication.getPrincipal();
            User user = authUser.getUser();
            String token = JwtUtils.createToken(user);

            // 쿠키 생성
            Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, token);
            cookie.setMaxAge(JwtProperties.EXPIRATION_TIME);
            cookie.setPath("/");

            // ResponseEntity를 사용하여 쿠키 데이터를 포함한 응답 생성
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
            ResponseEntity<String> responseEntity = new ResponseEntity<>("로그인 성공", headers, HttpStatus.OK);

            return responseEntity;
        } catch (AuthenticationException e) {
            // 인증에 실패하면 실패 응답을 반환
            return new ResponseEntity<>("로그인 실패", HttpStatus.UNAUTHORIZED);
        }
    }

}
