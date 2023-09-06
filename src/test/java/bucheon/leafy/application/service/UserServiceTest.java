package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.domain.user.UserRole;
import bucheon.leafy.exception.ExistException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class UserServiceTest extends IntegrationTestSupport {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원의 아이디가 중복이면 예외가 발생한다")
    void testDuplicationEmailCheck(){
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        userRepository.save(user1);

        //when
        String email = "ekxk1234@naver.com";

        //then
        Assertions.assertThatThrownBy(() -> userService.duplicationEmailCheck(email))
                .isInstanceOf(ExistException.class)
                .hasMessage("이미 존재하는 이메일입니다.");
    }

    private User createUser(String email, String nickName) {

        UserImage image = UserImage.builder()
                .image("이미지")
                .build();

        return User.builder()
                .userImage(image)
                .email(email)
                .phone("01012341234")
                .name("홍길동")
                .nickName(nickName)
                .password("비밀번호")
                .isAllNotifications(true)
                .isCommentNotifications(true)
                .isHide(false)
                .userRole(UserRole.MEMBER)
                .build();
    }

}