package bucheon.leafy.application.repository;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
class UserRepositoryTest extends IntegrationTestSupport {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserImageRepository userImageRepository;

    @AfterEach
    void tearDown(){
        addressRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
        userImageRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("유저 정보를 저장한다.")
    void testSaveUser(){
        //given
        User user = createUser("email@email.com", "별명", "01012341234");

        //when
        User result = userRepository.save(user);

        //then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("닉네임이 중복되면 true 를 반환한다.")
    void testExistsByNickname(){
        //given
        User user = createUser("email@email.com", "별명", "01012341234");
        userRepository.save(user);
        String nickname = "별명";

        //when
        boolean exists = userRepository.existsByNickName(nickname);

        //then
        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("이메일이 중복되면 true 를 반환한다.")
    void testExistsByEmail(){
        //given
        User user = createUser("email@email.com", "별명", "01012341234");
        userRepository.save(user);
        String email = "email@email.com";

        //when
        boolean exists = userRepository.existsByEmail(email);

        //then
        assertThat(exists).isTrue();
    }

    @TestFactory
    @DisplayName("이메일과 핸드폰 번호로 사용자를 조회한다.")
    Collection<DynamicTest> testFindByEmailAndPhone(){
        //given
        User user = createUser("email@email.com", "별명", "01012341234");
        userRepository.save(user);

        String email = "google@naver.com";
        String phone = "01012341234";

        return List.of(
                DynamicTest.dynamicTest("핸드폰 번호와 이메일이 일치하지 않으면 NPE 가 발생한다.", () -> {
                    //when then
                    assertThatThrownBy(() -> userRepository.findByEmailAndPhone(email, phone)
                            .orElseThrow(NullPointerException::new))
                            .isInstanceOf(NullPointerException.class);
                }),

                DynamicTest.dynamicTest("핸드폰 번호와 이메일이 일치하면 그에 맞는 User 객체가 반환된다.", () -> {
                    //when
                    User findUser = userRepository.findByEmailAndPhone(user.getEmail(), user.getPhone())
                            .orElseThrow(NullPointerException::new);

                    //then
                    assertThat(findUser)
                            .extracting("email", "nickName", "phone")
                            .contains(
                                    "email@email.com", "별명", "01012341234"
                            );
                })
        );
    }

    @Test
    @DisplayName("@EntityGraph 로 UserImage 까지 fetch join 조회")
    void testFindAllWithUserImageByIdIn(){
        //given
        User user1 = createUser("email@email.com", "별명1", "01012341234");
        User user2 = createUser("abcd@gmail.com", "별명2", "01111111111");
        User user3 = createUser("qwer@naver.com", "별명3", "01234567890");

        List<User> users = userRepository.saveAll(List.of(user1, user2, user3));
        List<Long> ids = users.stream()
                .map(user -> user.getId())
                .collect(Collectors.toList());

        //when
        List<User> result = userRepository.findAllById(ids);

        //then
        assertThat(result).isNotNull();
        assertThat(result)
                .hasSize(3)
                .extracting("email", "nickName")
                .containsExactlyInAnyOrder(
                        new Tuple("email@email.com", "별명1"),
                        new Tuple("abcd@gmail.com", "별명2"),
                        new Tuple("qwer@naver.com", "별명3")
                );

    }


    private User createUser(String email, String nickName, String phone) {

        UserImage image = UserImage.builder()
                .image("이미지")
                .build();

        return User.builder()
                .userImage(image)
                .email(email)
                .phone(phone)
                .name("홍길동")
                .nickName(nickName)
                .password("비밀번호")
                .build();
    }

}