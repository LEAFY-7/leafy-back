package bucheon.leafy.application.repository;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feedLikeInfo.FeedLikeInfo;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.exception.FeedLikeInfoNotFoundException;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
class FeedLikeInfoRepositoryTest extends IntegrationTestSupport {

    @Autowired
    FeedLikeInfoRepository feedLikeInfoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    FeedLikeRepository feedLikeRepository;

    @Test
    @DisplayName("사용자의 정보와 게시글의 정보로 회원을 찾는다.")
    void testFindFeedLikeInfoByUserAndFeed(){
        //given
        User user = createUser("email@email.com", "별명1");
        userRepository.save(user);

        Feed feed = createFeed("타이틀");
        feedRepository.save(feed);

        FeedLikeInfo feedLikeInfo = FeedLikeInfo.of(user, feed);
        feedLikeInfoRepository.save(feedLikeInfo);

        //when
        FeedLikeInfo findFeedLikeInfo = feedLikeInfoRepository.findByUserAndFeed(user, feed)
                .orElseThrow(FeedLikeInfoNotFoundException::new);

        //then
        Assertions.assertThat(findFeedLikeInfo).isNotNull();
        Assertions.assertThat(findFeedLikeInfo.getUser()).isEqualTo(user);
        Assertions.assertThat(findFeedLikeInfo.getFeed()).isEqualTo(feed);
    }

    @Test
    @DisplayName("사용자 ID로 좋아요를 누른 게시물들을 페이지네이션만큼 조회한다.")
    void testFindAllByUserId(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(0, 3, sort);

        User user = createUser("email@email.com", "별명1");
        User saveUser = userRepository.save(user);

        Feed feed1 = createFeed("title");
        Feed feed2 = createFeed("title2");
        Feed feed3 = createFeed("title3");
        Feed feed4 = createFeed("title4");

        List<Feed> feeds = List.of(feed1, feed2, feed3, feed4);
        feedRepository.saveAll(feeds);

        FeedLikeInfo feedLikeInfo1 = FeedLikeInfo.of(user, feed1);
        FeedLikeInfo feedLikeInfo2 = FeedLikeInfo.of(user, feed2);
        FeedLikeInfo feedLikeInfo3 = FeedLikeInfo.of(user, feed3);
        FeedLikeInfo feedLikeInfo4 = FeedLikeInfo.of(user, feed4);

        List<FeedLikeInfo> feedLikeInfos = List.of(feedLikeInfo1, feedLikeInfo2, feedLikeInfo3, feedLikeInfo4);
        feedLikeInfoRepository.saveAll(feedLikeInfos);

        //when
        List<FeedLikeInfo> feedLikeInfoResult = feedLikeInfoRepository.findAllWithFeedByUserId(saveUser.getId(), pageable).getContent();

        List<Feed> feedResults = feedLikeInfoResult.stream()
                .map(FeedLikeInfo::getFeed)
                .collect(Collectors.toList());

        //then
        Assertions.assertThat(feedResults).hasSize(3)
                .extracting("title", "content")
                .contains(
                        Tuple.tuple("title4", "내용"),
                        Tuple.tuple("title3", "내용"),
                        Tuple.tuple("title2", "내용")
                );


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