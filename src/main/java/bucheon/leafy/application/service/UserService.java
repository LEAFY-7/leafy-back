package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.request.SignInRequest;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.domain.user.response.UserResponse;
import bucheon.leafy.exception.*;
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

import java.util.Random;

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

        signUpRequest.comparePasswords( signUpRequest.getPassword(), signUpRequest.getConfirmPassword() );

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

        return ResponseEntity.status(201).body("아이디가 사용 가능합니다.");
    }

    public void duplicationNickNameCheck(String nickName) {
        userRepository.findByNickName(nickName)
                .ifPresent(u -> {
                    throw new ExistException(ExceptionKey.NICKNAME);
                });
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


    public void updateTemporaryPassword(String email, String phone) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        verifyUser(user, phone);

        String password = randomPassword(10);
        System.out.println(password);

        // mybatis
//        String encodedPassword = passwordEncoder.encode(password);
//        if(userMapper.updatePassword(email, encodedPassword) != 1){
//            throw new UserPasswordDataAccessException();
//        }

        // jpa
        String encodedPassword = passwordEncoder.encode(password);
        user.changePassword(encodedPassword);
        userRepository.save(user);


        // TODO 추후 임시비밀번호 메일 발송 로직 구현
    }

    public void verifyUser(User user, String phone) {
        if (!user.getPhone().equals(phone)){
            throw new UserNotVerifiedException();
        }
    }

    private String randomPassword(int length){
        String upperAlphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabets = upperAlphabets.toLowerCase();
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()";

        String allCharacters = upperAlphabets + lowerAlphabets + numbers + specialCharacters;

        Random random = new Random();
        StringBuilder randomString = new StringBuilder();

        randomString.append(upperAlphabets.charAt(random.nextInt(upperAlphabets.length())));
        randomString.append(lowerAlphabets.charAt(random.nextInt(lowerAlphabets.length())));
        randomString.append(numbers.charAt(random.nextInt(numbers.length())));
        randomString.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        for (int i = 4; i < length; i++) {
            randomString.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        char[] charArray = randomString.toString().toCharArray();
        for (int i = charArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = charArray[index];
            charArray[index] = charArray[i];
            charArray[i] = temp;
        }

        return new String(charArray);
    }
}
