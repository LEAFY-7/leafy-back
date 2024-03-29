package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.repository.*;
import bucheon.leafy.domain.follow.Follow;
import bucheon.leafy.domain.follow.response.FollowersResponse;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.domain.user.UserRole;
import bucheon.leafy.util.response.JpaPageResponse;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static bucheon.leafy.path.S3Path.ABSOLUTE_PATH;
import static bucheon.leafy.path.S3Path.USER_IMAGE_PATH;
import static org.assertj.core.api.Assertions.assertThat;


@Transactional
class FollowServiceTest extends IntegrationTestSupport {

    @Autowired
    FollowService followService;

    @Autowired
    FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserImageRepository userImageRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private AlarmRepository alarmRepository;

    @AfterEach
    void tearDown(){
        userImageRepository.deleteAllInBatch();
        addressRepository.deleteAllInBatch();
        followRepository.deleteAllInBatch();
        alarmRepository.deleteAllInBatch();
        feedRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("사용자가 다른 회원을 팔로우 한다.")
    void testFollow(){
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        userRepository.saveAll( List.of(user1, user2) );

        //when
        followService.follow(user1.getId(), user2.getId());
        List<Follow> follows = followRepository.findAll();

        //then
        assertThat(follows).hasSize(1);
    }

    @Test
    @DisplayName("사용자가 다른 회원을 언팔로우 한다.")
    void testUnfollow(){
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        userRepository.saveAll( List.of(user1, user2) );

        followRepository.save( Follow.of(user1, user2) );

        //when
        followService.unfollow(user1.getId(), user2.getId());
        List<Follow> follows = followRepository.findAll();

        //then
        assertThat(follows).hasSize(0);
    }

    @Test
    @DisplayName("내가 팔로우한 회원들의 정보를 조회한다")
    void testGetFollowings(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(0, 3, sort);

        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        User user3 = createUser("qwer@gmail.com", "이수근");
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
        JpaPageResponse followers = followService.getFollowings(user1.getId(), pageable);

        //then
        assertThat(followers.getBody()).hasSize(pageable.getPageSize())
                .extracting("email", "nickName", "profileImage")
                .contains(
                        new Tuple("tyui@gmail.com", "강호동" , ABSOLUTE_PATH + USER_IMAGE_PATH + "이미지"),
                        new Tuple("zxcv@naver.com", "유재석" , ABSOLUTE_PATH + USER_IMAGE_PATH + "이미지"),
                        new Tuple("qwer@gmail.com", "이수근" , ABSOLUTE_PATH + USER_IMAGE_PATH + "이미지")
                );
    }

    @Test
    @DisplayName("나를 팔로우한 회원들의 정보를 조회한다")
    void testGetFollowers(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(0, 3, sort);

        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        User user3 = createUser("qwer@gmail.com", "이수근");
        User user4 = createUser("zxcv@naver.com", "유재석");
        User user5 = createUser("tyui@gmail.com", "강호동");

        userRepository.saveAll( List.of(user1, user2, user3, user4, user5) );

        Follow follow1 = Follow.of(user2, user1);
        Follow follow2 = Follow.of(user3, user1);
        Follow follow3 = Follow.of(user4, user1);
        Follow follow4 = Follow.of(user5, user1);

        List<Follow> followList = List.of(follow1, follow2, follow3, follow4);
        followRepository.saveAll(followList);

        //when
        JpaPageResponse followers = followService.getFollowers(user1.getId(), pageable);

        //then
        assertThat(followers.getBody()).hasSize(pageable.getPageSize())
                .extracting("email", "nickName", "profileImage")
                .contains(
                        new Tuple("tyui@gmail.com", "강호동" , ABSOLUTE_PATH + USER_IMAGE_PATH + "이미지"),
                        new Tuple("zxcv@naver.com", "유재석" , ABSOLUTE_PATH + USER_IMAGE_PATH + "이미지"),
                        new Tuple("qwer@gmail.com", "이수근" , ABSOLUTE_PATH + USER_IMAGE_PATH + "이미지")
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