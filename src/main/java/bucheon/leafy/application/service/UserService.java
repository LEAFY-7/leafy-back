package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.AlarmMapper;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserRole;
import bucheon.leafy.domain.user.request.PasswordRequest;
import bucheon.leafy.domain.user.request.SignInRequest;
import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.domain.user.request.UserRequest;
import bucheon.leafy.domain.user.response.GetMeResponse;
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

import static bucheon.leafy.exception.enums.ExceptionKey.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

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

        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        String authority = authentication.getAuthorities().stream()
                .map(g -> g.getAuthority()).findFirst()
                .orElseThrow(UserNotFoundException::new);

        String role = authority.replace("ROLE_", "");
        String jwt = tokenProvider.createToken(authentication);
        return TokenResponse.of(jwt, role, authUser.getUserId());
    }

    public void signUp(SignUpRequest signUpRequest) {
        comparePasswords( signUpRequest.getPassword(), signUpRequest.getConfirmPassword() );

        while (true) {
            try {
                duplicationNickNameCheck( signUpRequest.getNickName() );
                break;
            } catch (ExistException e) {
                signUpRequest.setNickName( SignUpRequest.generateRandomNickname() );
            }
        }

        duplicationEmailCheck(signUpRequest.getEmail());
        duplicationPhoneCheck(signUpRequest.getPhone());

        User user = User.of(signUpRequest);
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        user.changePassword(encodedPassword);
        userRepository.save(user);
    }

    public void updateUser(Long userId, UserRequest userRequest) {
        User user = getUserById(userId);

        if ( !user.getNickName().equals( userRequest.getNickName() ) ) {
            duplicationNickNameCheck( userRequest.getNickName() );
        }

        user.update(userRequest);

        PasswordRequest passwordRequest = PasswordRequest.of(userRequest);
        editPassword(userId, passwordRequest);
    }

    public void duplicationPhoneCheck(String phone) {
        Boolean exists = userRepository.existsByPhone(phone);
        if (exists) throw new ExistException(PHONE);
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

    public String getEmailByNameAndPhone(String name, String phone) {
        User user = userRepository.findByNameAndPhone(name, phone)
                .orElseThrow(UserNotFoundException::new);

        return user.getEmail();
    }

    public void editIsHide(Long userId) {
        User user = getUserById(userId);
        user.updateIsHide();
    }

    public void editIsAllNotifications(Long userId) {
        User user = getUserById(userId);
        user.updateIsAllNotifications();
    }

    public void editIsCommentNotifications(Long userId) {
        User user = getUserById(userId);
        user.updateIsCommentNotifications();
    }

    public void editRole(Long userId, UserRole userRole) {
        User user = getUserById(userId);
        user.giveRole(userRole);
    }


    private void comparePasswords(String password, String confirmPassword) {
        if ( !password.equals(confirmPassword) ){
            throw new PasswordNotMatchedException();
        }
    }

}
