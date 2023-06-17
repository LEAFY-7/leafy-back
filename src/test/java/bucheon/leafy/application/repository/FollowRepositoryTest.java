package bucheon.leafy.application.repository;

import bucheon.leafy.domain.follow.Follow;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.exception.FollowNotFoundException;
import org.assertj.core.api.Assertions;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class FollowRepositoryTest {

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
    @DisplayName("페이징 처리를 적용하여 최신순으로 사용자가 팔로우한 회원들을 조회한다.")
    void testFindAllByFollower(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(0, 3, sort);

        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        User user3 = createUser("qwer@gmail.com", "강호동");
        User user4 = createUser("zxcv@naver.com", "유재석");
        User user5 = createUser("tyui@gmail.com", "강호동");

        userRepository.saveAll( List.of(user1, user2, user3, user4, user5) );

        Follow follow1 = Follow.of(user1, user2);
        Follow follow2 = Follow.of(user1, user3);
        Follow follow3 = Follow.of(user1, user4);
        Follow follow4 = Follow.of(user1, user5);
        List<Follow> followList = List.of(follow1, follow2, follow3, follow4);

        followRepository.saveAll(followList);

        //when
        List<Follow> followers = followRepository.findAllByFollower(user1, pageable);

        //then
        assertThat( followers.size() ).isLessThan( followList.size() );

        assertThat(followers).hasSize(3)
                .extracting("id")
                .contains(4L, 3L, 2L);

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
                .address(List.of(address))
                .userImage(image)
                .email(email)
                .phone("01012341234")
                .nickName(nickName)
                .password("비밀번호")
                .build();
    }

}