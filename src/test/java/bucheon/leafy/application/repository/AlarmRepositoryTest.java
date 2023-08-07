
package bucheon.leafy.application.repository;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.service.FeedLikeService;
import bucheon.leafy.application.service.FollowService;
import bucheon.leafy.domain.alarm.Alarm;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static bucheon.leafy.domain.alarm.AlarmType.FEED_LIKE;
import static bucheon.leafy.domain.alarm.AlarmType.NEW_FOLLOW;
import static org.assertj.core.api.Assertions.assertThat;


@Transactional
class AlarmRepositoryTest extends IntegrationTestSupport {

    @Autowired
    AlarmRepository alarmRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowService followService;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    FeedLikeService feedLikeService;

    @Autowired
    FeedLikeRepository feedLikeRepository;


    @Test
    @DisplayName("사용자가 다른 회원을 팔로우하면 팔로우 당한 회원에게 알람이 저장된다.")
    void testFollowAlarm(){
        //given
        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동");
        userRepository.saveAll( List.of(user1, user2) );

        //when
        followService.follow(user1.getId(), user2.getId());
        Alarm alarms = alarmRepository.findByUser(user2)
                .orElseThrow(NullPointerException::new);

        //then
        assertThat(alarms)
                .extracting("user.nickName", "alarmType")
                .contains("홍길동", NEW_FOLLOW);
    }

    private User createUser(String email, String nickName) {
        return User.builder()
                .email(email)
                .phone("01012341234")
                .name("홍길동")
                .nickName(nickName)
                .password("비밀번호")
                .build();
    }

    private User createUser(String email, String nickName, Feed feed) {
        return User.builder()
                .email(email)
                .feeds( List.of(feed) )
                .phone("01012341234")
                .name("홍길동")
                .nickName(nickName)
                .password("비밀번호")
                .build();
    }

}