package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.exception.ExistException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;


class UserServiceTest extends IntegrationTestSupport {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @TestFactory
    @DisplayName("회원의 아이디가 중복인제 체크한다.")
    Collection<DynamicTest> testDuplicationIdCheck(){
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");

        userRepository.save(user1);


        return List.of(

                DynamicTest.dynamicTest("아이디가 중복이 아니면 성공한다.", () -> {
                    //when
                    User user2 = createUser("abcd@gmail.com", "홍길동");
                    ResponseEntity responseEntity = userService.duplicationIdCheck(user2.getEmail());

                    //then
                    Assertions.assertThat(responseEntity.getStatusCodeValue())
                            .isEqualTo(200);
                }),

                DynamicTest.dynamicTest("아이디가 중복이면 예외가 발생한다.", () -> {
                    //when
                    User user2 = createUser("ekxk1234@naver.com", "홍길동");

                    //then
                    Assertions.assertThatThrownBy(() -> userService.duplicationIdCheck(user2.getEmail()))
                            .isInstanceOf(ExistException.class)
                            .hasMessage("이미 존재하는 이메일입니다.");

                })

        );

    }

    private User createUser(String email, String nickName) {
        Address address = Address.builder()
                .zipcode("01011")
                .street("bucheon")
                .lot("100")
                .reference("ref")
                .detail("hello world")
                .build();

        UserImage image = UserImage.builder()
                .image("이미지")
                .build();

        return User.builder()
                .address(address)
                .userImage(image)
                .email(email)
                .phone("01012341234")
                .nickName(nickName)
                .password("비밀번호")
                .build();
    }

}