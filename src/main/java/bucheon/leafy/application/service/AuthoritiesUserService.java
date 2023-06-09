package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.dto.request.SignInRequest;
import bucheon.leafy.domain.user.dto.request.SignUpRequest;
import bucheon.leafy.exception.PasswordNotMatchedException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthoritiesUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public ResponseEntity signUp(SignUpRequest signUpRequest) {
        User user = User.of(signUpRequest);
        String encodePassword = encodePassword(signUpRequest.getPassword());
        user.changePassword(encodePassword);
        userRepository.save(user);
        return ResponseEntity.status(200).body("회원가입 완료");
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public ResponseEntity signIn(SignInRequest signInRequest) {
        User user = userRepository.findByCustomerId(signInRequest.getCustomerId())
                .orElseThrow(UserNotFoundException::new);

        if (passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())){
            return ResponseEntity.status(200).body("로그인 성공");
        }

        throw new PasswordNotMatchedException();
    }
}
