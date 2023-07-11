package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.application.repository.FeedLikeInfoRepository;
import bucheon.leafy.application.repository.FeedLikeRepository;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feedLikeInfo.FeedLikeInfo;
import bucheon.leafy.domain.user.User;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class FeedLikeServiceTest extends IntegrationTestSupport {

    @Autowired
    FeedLikeService feedLikeService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    FeedLikeRepository feedLikeRepository;

    @Autowired
    FeedLikeInfoRepository feedLikeInfoRepository;

    @AfterEach
    void tearDown(){
        feedLikeInfoRepository.deleteAllInBatch();
        feedLikeRepository.deleteAllInBatch();
        feedRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("게시글에 좋아요를 눌렀을 때 좋아요가 1 증가한다.")
    void testIncreaseLikeCount(){
        //given
        User user = createUser("email@email.com", "별명1");
        userRepository.save(user);

        Feed feed = createFeed(0L);
        feedRepository.save(feed);

        //when
        feedLikeService.increaseLikeCount(feed);

        //then
        assertThat(feed.getFeedLikeCount().getLikeCount().get())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("게시글에 좋아요를 눌렀을 때 좋아요가 1 감소한다.")
    void testDecreaseLikeCount(){
        //given
        User user = createUser("email@email.com", "별명1");
        userRepository.save(user);

        Feed feed = createFeed(1L);
        feedRepository.save(feed);

        //when
        feedLikeService.decreaseLikeCount(feed);

        //then
        assertThat(feed.getFeedLikeCount().getLikeCount().get())
                .isEqualTo(0);
    }

    @Test
    @DisplayName("좋아요를 눌렀을 때 좋아요 정보 테이블에 사용자와 게시글의 정보가 입력된다.")
    void testSaveLikeInfo(){
        //given
        User user = createUser("email@email.com", "별명1");
        userRepository.save(user);

        Feed feed = createFeed(0L);
        feedRepository.save(feed);

        //when
        feedLikeService.saveLikeInfo(user, feed);
        List<FeedLikeInfo> result = feedLikeInfoRepository.findAll();

        //then
        assertThat(result).hasSize(1)
                .extracting("user", "feed")
                .containsExactlyInAnyOrder(
                        Tuple.tuple(user, feed)
                );
    }

    @Test
    @DisplayName("좋아요 취소를 눌렀을 때 좋아요 정보 테이블에 사용자와 게시글의 정보가 삭제된다.")
    void testDeleteLikeInfo(){
        //given
        User user = createUser("email@email.com", "별명1");
        userRepository.save(user);

        Feed feed = createFeed(1L);
        feedRepository.save(feed);

        FeedLikeInfo feedLikeInfo = FeedLikeInfo.of(user, feed);
        feedLikeInfoRepository.save(feedLikeInfo);

        //when
        feedLikeService.deleteLikeInfo(user, feed);
        List<FeedLikeInfo> result = feedLikeInfoRepository.findAll();

        //then
        assertThat(result).hasSize(0);
    }

    @Test
    @DisplayName("좋아요를 눌렀을 때 게시글의 좋아요가 1 증가되고 좋아요 정보 테이블에 사용자와 게시글의 정보가 입력된다.")
    void testSaveLike(){
        //given
        User user = createUser("email@email.com", "별명1");
        userRepository.save(user);

        Feed feed = createFeed(3L);
        feedRepository.save(feed);

        //when
        feedLikeService.saveLike(user.getId(), feed.getId());

        long feedLikeCount = feed.getFeedLikeCount().getLikeCount().get();
        List<FeedLikeInfo> feedLikeInfos = feedLikeInfoRepository.findAll();

        //then
        assertThat(feedLikeCount).isEqualTo(4L);
        assertThat(feedLikeInfos).hasSize(1)
                .extracting("user", "feed")
                .containsExactlyInAnyOrder(
                        Tuple.tuple(user, feed)
                );
    }

    @Test
    @DisplayName("좋아요를 눌렀을 때 게시글의 좋아요가 1 감소되고 좋아요 정보 테이블에 사용자와 게시글의 정보가 삭제된다.")
    void testDeleteLike(){
        //given
        User user = createUser("email@email.com", "별명1");
        userRepository.save(user);

        Feed feed = createFeed(4L);
        feedRepository.save(feed);

        FeedLikeInfo feedLikeInfo = FeedLikeInfo.of(user, feed);
        feedLikeInfoRepository.save(feedLikeInfo);

        //when
        feedLikeService.deleteLike(user.getId(), feed.getId());

        long feedLikeCount = feed.getFeedLikeCount().getLikeCount().get();
        List<FeedLikeInfo> feedLikeInfos = feedLikeInfoRepository.findAll();

        //then
        assertThat(feedLikeCount).isEqualTo(3L);
        assertThat(feedLikeInfos).hasSize(0);
    }

    private Feed createFeed(Long likeCount) {

        FeedLikeCount feedLikeCount = FeedLikeCount.of( new AtomicLong(likeCount) );

        return Feed.builder()
                .title("타이틀")
                .content("내용")
                .isHide(false)
                .isDelete(false)
                .feedLikeCount(feedLikeCount)
                .build();

    }

    private User createUser(String email, String nickName) {

        return User.builder()
                .email(email)
                .phone("01012341234")
                .nickName(nickName)
                .password("비밀번호")
                .build();

    }


}