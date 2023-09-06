package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.repository.AlarmRepository;
import bucheon.leafy.application.repository.FeedLikeRepository;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.alarm.Alarm;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static bucheon.leafy.domain.alarm.AlarmType.FEED_LIKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@Transactional
class AlarmServiceTest extends IntegrationTestSupport {

    @Autowired
    AlarmService alarmService;

    @Autowired
    AlarmRepository alarmRepository;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedLikeRepository feedLikeRepository;


    @Test
    @DisplayName("알림을 저장한다")
    void testSaveAlarm(){
        AlarmService alarmService = mock(AlarmService.class);
        alarmService.createAlarm(1, AlarmType.NOTICE, 1);

        verify(alarmService, times(1)).createAlarm(1, AlarmType.NOTICE, 1);
    }

    @Test
    @DisplayName("알림 읽음 처리한다")
    void testUpdateCheckAlarm(){
        AlarmService alarmService = mock(AlarmService.class);
        alarmService.readAlarm(1, AlarmType.NOTICE, 1);

        verify(alarmService, times(1)).readAlarm(1, AlarmType.NOTICE, 1);

    }
    @Test
    @DisplayName("사용자가 게시물에 좋아요를 하면 게시물을 올린 회원에게 알람이 저장된다.")
    void testSaveFeedLikeAlarm(){
        //given
        Feed feed = createFeed("게시물");
        Feed saveFeed = feedRepository.save(feed);

        User user1 = createUser("ekxk1234@naver.com", "정철희");
        User user2 = createUser("abcd@gmail.com", "홍길동", saveFeed);
        userRepository.saveAll( List.of(user1, user2) );

        //when
        alarmService.saveFeedLikeAlarm(saveFeed.getId());
        Alarm alarm = alarmRepository.findByUser(user2)
                .orElseThrow(NullPointerException::new);;

        //then
        assertThat(alarm)
                .extracting("user.nickName", "alarmType")
                .contains("홍길동", FEED_LIKE);
    }

    private Feed createFeed(String title) {
        Feed feed = Feed.builder()
                .title(title)
                .content("내용")
                .build();

        FeedLikeCount feedLikeCount = FeedLikeCount.of(0L, feed);
        feedLikeRepository.save(feedLikeCount);
        feed.initFeedLikeCount(feedLikeCount);
        return feed;
    }

    private User createUser(String email, String nickName) {
        return User.builder()
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

    private User createUser(String email, String nickName, Feed feed) {
        return User.builder()
                .email(email)
                .feeds( List.of(feed) )
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