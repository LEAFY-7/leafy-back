package bucheon.leafy.application.event;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.event.request.LikeCancelEvent;
import bucheon.leafy.application.event.request.LikeEvent;
import bucheon.leafy.application.repository.*;
import bucheon.leafy.domain.alarm.Alarm;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feedlikeinfo.FeedLikeInfo;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserRole;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static bucheon.leafy.domain.alarm.AlarmType.FEED_LIKE;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class LikeEventHandlerTest extends IntegrationTestSupport {

    @Autowired
    LikeEventHandler likeEventHandler;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    FeedLikeRepository feedLikeRepository;

    @Autowired
    AlarmRepository alarmRepository;

    @Autowired
    FeedLikeInfoRepository feedLikeInfoRepository;

    @AfterEach
    void tearDown(){
        feedLikeInfoRepository.deleteAllInBatch();
        feedLikeRepository.deleteAllInBatch();
        feedRepository.deleteAllInBatch();
        alarmRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("게시글에 좋아요를 눌렀을 때 좋아요가 1 증가한다.")
    void testIncreaseLikeCount() throws Exception {
        //given
        User user = createUser("email@email.com", "별명1", "01012341234");
        userRepository.save(user);

        Feed feed = createFeed(0L);
        feedRepository.save(feed);

        LikeEvent likeEvent = LikeEvent.of(feed.getId(), user.getId());

        //when
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            likeEventHandler.increaseLikeCount(likeEvent);
        });

        // wait
        future.get();

        //then
        assertThat(feed.getFeedLikeCount().getLikeCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글에 좋아요를 눌렀을 때 좋아요가 1 감소한다.")
    void testDecreaseLikeCount() throws Exception {
        //given
        User user = createUser("email@email.com", "별명1", "01012341234");
        userRepository.save(user);

        Feed feed = createFeed(1L);
        feedRepository.save(feed);

        LikeCancelEvent likeCancelEvent = LikeCancelEvent.of(feed.getId(), user.getId());

        //when
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            likeEventHandler.decreaseLikeCount(likeCancelEvent);
        });

        // wait
        future.get();

        //then
        assertThat(feed.getFeedLikeCount().getLikeCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("좋아요를 눌렀을 때 좋아요 정보 테이블에 사용자와 게시글의 정보가 입력된다.")
    void testSaveLikeInfo() throws Exception {
        //given
        User user = createUser("email@email.com", "별명1", "01012341234");
        userRepository.save(user);

        Feed feed = createFeed(0L);
        feedRepository.save(feed);

        LikeEvent likeEvent = LikeEvent.of(feed.getId(), user.getId());

        //when
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            likeEventHandler.saveLikeInfo(likeEvent);
        });

        // wait
        future.get();

        //then
        List<FeedLikeInfo> result = feedLikeInfoRepository.findAll();

        assertThat(result).hasSize(1)
                .extracting("user.id", "feed.id")
                .containsExactlyInAnyOrder(
                        Tuple.tuple(user.getId(), feed.getId())
                );
    }

    @Test
    @DisplayName("좋아요 취소를 눌렀을 때 좋아요 정보 테이블에 사용자와 게시글의 정보가 삭제된다.")
    void testDeleteLikeInfo() throws Exception {
        //given
        User user = createUser("email@email.com", "별명1", "01012341234");
        userRepository.save(user);

        Feed feed = createFeed(1L);
        feedRepository.save(feed);

        FeedLikeInfo feedLikeInfo = FeedLikeInfo.of(user, feed);
        feedLikeInfoRepository.save(feedLikeInfo);

        LikeCancelEvent likeCancelEvent = LikeCancelEvent.of(feed.getId(), user.getId());

        //when
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            likeEventHandler.deleteLikeInfo(likeCancelEvent);
        });

        // wait
        future.get();

        //then
        List<FeedLikeInfo> result = feedLikeInfoRepository.findAll();
        assertThat(result).hasSize(0);
    }

    @Test
    @DisplayName("사용자가 게시물에 좋아요를 하면 게시물을 올린 회원에게 알람이 저장된다.")
    void testSaveFeedLikeAlarm() throws Exception {
        //given
        Feed feed = createFeed(0L);
        Feed saveFeed = feedRepository.save(feed);

        User user1 = createUser("ekxk1234@naver.com", "정철희", "01012341234");
        User user2 = createUser("abcd@gmail.com", "홍길동", "01099991234", saveFeed);
        userRepository.saveAll( List.of(user1, user2) );

        LikeEvent likeEvent = LikeEvent.of(feed.getId(), user1.getId());

        //when
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            likeEventHandler.saveFeedLikeAlarm(likeEvent);
        });

        // wait
        future.get();

        //then
        Alarm alarm = alarmRepository.findByUser(user2)
                .orElseThrow(NullPointerException::new);

        assertThat(alarm)
                .extracting("user.nickName", "alarmType")
                .contains("홍길동", FEED_LIKE);
    }

    private Feed createFeed(Long likeCount) {
        Feed feed = Feed.builder()
                .title("타이틀")
                .content("내용")
                .build();

        FeedLikeCount feedLikeCount = FeedLikeCount.of(likeCount, feed);
        feedLikeRepository.save(feedLikeCount);
        feed.initFeedLikeCount(feedLikeCount);
        return feed;
    }

    private User createUser(String email, String nickName, String phone) {

        return User.builder()
                .email(email)
                .phone(phone)
                .name("홍길동")
                .nickName(nickName)
                .password("비밀번호")
                .isAllNotifications(true)
                .isCommentNotifications(true)
                .isHide(false)
                .userRole(UserRole.MEMBER)
                .build();
    }

    private User createUser(String email, String nickName, String phone, Feed feed) {
        return User.builder()
                .email(email)
                .feeds( List.of(feed) )
                .phone(phone)
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