package bucheon.leafy.application.repository;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.follow.Follow;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.exception.FollowNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class FollowRepositoryTest extends IntegrationTestSupport {

    @Autowired
    FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserImageRepository userImageRepository;

    @AfterEach
    void tearDown(){
        userImageRepository.deleteAllInBatch();
        addressRepository.deleteAllInBatch();
        followRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("페이징 처리를 적용하여 최신순으로 사용자(나)가 팔로우한 회원들을 조회한다.")
    void testFindAllByFollower(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(0, 3, sort);

        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        User user3 = createUser("qwer@gmail.com", "강호동");
        User user4 = createUser("zxcv@naver.com", "유재석");
        User user5 = createUser("tyui@gmail.com", "박호동");

        userRepository.saveAll( List.of(user1, user2, user3, user4, user5) );

        Follow follow1 = Follow.of(user1, user2);
        Follow follow2 = Follow.of(user1, user3);
        Follow follow3 = Follow.of(user1, user4);
        Follow follow4 = Follow.of(user1, user5);
        List<Follow> followList = List.of(follow1, follow2, follow3, follow4);

        followRepository.saveAll(followList);

        //when
        Page<Follow> follows = followRepository.findAllByFollower(user1, pageable);
        List<Follow> followers = follows.getContent();

        //then
        assertThat(followers).hasSize(pageable.getPageSize())
                .extracting("following")
                .contains(user5, user4, user3);

    }

    @Test
    @DisplayName("페이징 처리를 적용하여 최신순으로 사용자(나)를 팔로우한 회원들을 조회한다.")
    void testFindAllByFollowing(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(1, 2, sort);

        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        User user3 = createUser("qwer@gmail.com", "강호동");
        User user4 = createUser("zxcv@naver.com", "유재석");
        User user5 = createUser("tyui@gmail.com", "박호동");

        userRepository.saveAll( List.of(user1, user2, user3, user4, user5) );

        Follow follow1 = Follow.of(user2, user1);
        Follow follow2 = Follow.of(user3, user1);
        Follow follow3 = Follow.of(user4, user1);
        Follow follow4 = Follow.of(user5, user1);
        List<Follow> followList = List.of(follow1, follow2, follow3, follow4);

        followRepository.saveAll(followList);

        //when
        Page<Follow> follows = followRepository.findAllByFollowing(user1, pageable);
        List<Follow> followers = follows.getContent();

        //then
        assertThat(followers).hasSize(pageable.getPageSize())
                .extracting("follower")
                .contains(user3, user2);

    }

    @Test
    @DisplayName("팔로우한 회원과 팔로우 당한 회원을 정보로 팔로우 테이블에서 조회한다.")
    void testFindByFollowerAndFollowing(){
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        userRepository.saveAll( List.of(user1, user2) );

        followRepository.save( Follow.of(user1, user2) );

        //when
        Follow follow = followRepository.findByFollowerAndFollowing(user1, user2)
                .orElseThrow(FollowNotFoundException::new);

        //then
        assertThat(follow.getFollower()).isEqualTo(user1);
        assertThat(follow.getFollowing()).isEqualTo(user2);
    }

    @Test
    @DisplayName("회원이 팔로우한 회원의 수를 조회한다.")
    void testCountByFollower(){
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        User user3 = createUser("qwer@gmail.com", "강호동");
        User user4 = createUser("zxcv@naver.com", "유재석");
        User user5 = createUser("tyui@gmail.com", "박호동");

        userRepository.saveAll( List.of(user1, user2, user3, user4, user5) );

        Follow follow1 = Follow.of(user1, user2);
        Follow follow2 = Follow.of(user1, user3);
        Follow follow3 = Follow.of(user1, user4);
        Follow follow4 = Follow.of(user1, user5);
        List<Follow> followList = List.of(follow1, follow2, follow3, follow4);

        followRepository.saveAll(followList);

        //when
        Long count = followRepository.countByFollower(user1);

        //then
        assertThat(count).isEqualTo(4L);
    }

    @Test
    @DisplayName("회원을 팔로잉한 회원의 수를 조회한다.")
    void testCountByFollowing(){
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        User user3 = createUser("qwer@gmail.com", "강호동");
        User user4 = createUser("zxcv@naver.com", "유재석");

        userRepository.saveAll( List.of(user1, user2, user3, user4) );

        Follow follow1 = Follow.of(user2, user1);
        Follow follow2 = Follow.of(user3, user1);
        Follow follow3 = Follow.of(user4, user1);
        List<Follow> followList = List.of(follow1, follow2, follow3);

        followRepository.saveAll(followList);

        //when
        Long count = followRepository.countByFollowing(user1);

        //then
        assertThat(count).isEqualTo(3L);
    }

    @Test
    @DisplayName("회원이 특정 회원을 팔로우 했다면 True 를 반환한다.")
    void testExistsByFollowerAndFollowing(){
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");

        userRepository.saveAll( List.of(user1, user2) );

        Follow follow1 = Follow.of(user2, user1);
        List<Follow> followList = List.of(follow1);

        followRepository.saveAll(followList);

        //when
        Boolean exists = followRepository.existsByFollowerAndFollowing(user2, user1);

        //then
        assertThat(exists).isTrue();
    }


    private User createUser(String email, String nickName) {
        Address address = Address.builder()
                .zoneCode("01011")
                .address("bucheon")
                .jibunAddress("100")
                .roadAddress("ref")
                .detailAddress("hello world")
                .isHide(false)
                .build();

        UserImage image = UserImage.builder()
                .image("이미지")
                .build();

        return User.builder()
                .address(address)
                .userImage(image)
                .email(email)
                .phone("01012341234")
                .name("홍길동")
                .nickName(nickName)
                .password("비밀번호")
                .build();
    }

}