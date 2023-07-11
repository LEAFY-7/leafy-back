package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.exception.ExistException;
import bucheon.leafy.exception.enums.ExceptionKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity signUp(SignUpRequest signUpRequest) {

        userRepository.findByEmail(signUpRequest.getEmail())
                .ifPresent(u -> {
                    throw new ExistException(ExceptionKey.EMAIL);
                });

        User user = User.of(signUpRequest);
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        user.changePassword(encodedPassword);
        userRepository.save(user);
        return ResponseEntity.status(200).body("회원가입 완료");
    }

    public ResponseEntity duplicationIdCheck(String email) {
        userRepository.findByEmail(email)
                .ifPresent(u -> {
                    throw new ExistException(ExceptionKey.EMAIL);
                });

        return ResponseEntity.status(200).body("아이디가 사용 가능합니다.");
    }
}
