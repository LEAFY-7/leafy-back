package bucheon.leafy.application.repository;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.domain.user.UserRole;
import bucheon.leafy.domain.userblock.UserBlock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Transactional
class UserBlockRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBlockRepository userBlockRepository;


    @TestFactory
    @DisplayName("회원과 차단한 회원을 매개변수로 넘겨서 차단 여부를 확인한다.")
    Collection<DynamicTest> testExistsByUserAndBlockUser() {
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        User user3 = createUser("qwer@daum.com", "유재석");
        userRepository.saveAll(List.of(user1, user2, user3));

        UserBlock userBlock = UserBlock.of(user1, user2);
        userBlockRepository.save(userBlock);

        return List.of(

                DynamicTest.dynamicTest("차단한 회원이면 True 를 반환한다.", () -> {
                    //when
                    Boolean isBlock = userBlockRepository.existsByUserAndBlockUser(user1, user2);

                    //then
                    Assertions.assertThat(isBlock).isTrue();
                }),

                DynamicTest.dynamicTest("차단을 하지 않은 회원이면 False 를 반환한다.", () -> {
                    //when
                    Boolean isBlock = userBlockRepository.existsByUserAndBlockUser(user1, user3);

                    //then
                    Assertions.assertThat(isBlock).isFalse();
                })

        );

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