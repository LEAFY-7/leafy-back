package bucheon.leafy.application.service;

import bucheon.leafy.application.component.MailComponent;
import bucheon.leafy.application.mapper.AlarmMapper;
import bucheon.leafy.application.mapper.CertificationNumberMapper;
import bucheon.leafy.application.repository.CertificationNumberRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.CertificationNumber;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.request.PasswordRequest;
import bucheon.leafy.domain.user.request.SignInRequest;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.domain.user.response.CertificationNumberResponse;
import bucheon.leafy.domain.user.response.GetMeResponse;
import bucheon.leafy.domain.user.response.UserResponse;
import bucheon.leafy.exception.ExistException;
import bucheon.leafy.exception.PasswordNotMatchedException;
import bucheon.leafy.exception.UserNotFoundException;
import bucheon.leafy.jwt.TokenProvider;
import bucheon.leafy.jwt.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static bucheon.leafy.exception.enums.ExceptionKey.EMAIL;
import static bucheon.leafy.exception.enums.ExceptionKey.NICKNAME;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final MailComponent mailComponent;
    private final CertificationNumberRepository certificationNumberRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AlarmMapper alarmMapper;


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

        return new TokenResponse(jwt, role);
    }

    public Long signUp(SignUpRequest signUpRequest) {
        comparePasswords( signUpRequest.getPassword(), signUpRequest.getConfirmPassword() );

        duplicationNickNameCheck(signUpRequest.getNickName());
        duplicationEmailCheck(signUpRequest.getEmail());

        User user = User.of(signUpRequest);

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        user.changePassword(encodedPassword);
        User saveUser = userRepository.save(user);

        return saveUser.getId();
    }

    public CertificationNumberResponse sendCertificationNumber(String email) {
        String number = mailComponent.sendCode(email);
        CertificationNumber certificationNumber = CertificationNumber.builder().email(email).number(number).build();

        CertificationNumberResponse response = CertificationNumberResponse.builder()
                .number(number).createdAt(certificationNumberRepository.save(certificationNumber).getCreatedAt()).build();
        return response;
    }


    public void duplicationEmailCheck(String email) {
        Boolean exists = userRepository.existsByEmail(email);
        if (exists) throw new ExistException(EMAIL);
    }

    public void duplicationNickNameCheck(String nickName) {
        boolean exists = userRepository.existsByNickName(nickName);
        if (exists) throw new ExistException(NICKNAME);
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
        int alarmCount = alarmMapper.countByUserId(userId);
        return GetMeResponse.of(user, alarmCount);
    }

    public void editPassword(Long userId, PasswordRequest passwordRequest) {
        comparePasswords( passwordRequest.getPassword(), passwordRequest.getConfirmPassword() );

        User user = getUserById(userId);
        String encodedPassword = passwordEncoder.encode(passwordRequest.getPassword());
        user.changePassword(encodedPassword);
    }

    public void updateTemporaryPassword(String email, String phone) {
        User user = userRepository.findByEmailAndPhone(email, phone)
                .orElseThrow(UserNotFoundException::new);

        String password = randomPassword(10);

        String encodedPassword = passwordEncoder.encode(password);
        user.changePassword(encodedPassword);
        userRepository.save(user);

        // TODO 추후 임시비밀번호 메일 발송 로직 구현
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

    private void comparePasswords(String password, String confirmPassword) {
        if ( !password.equals(confirmPassword) ){
            throw new PasswordNotMatchedException();
        }
    }
}
