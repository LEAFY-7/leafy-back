package bucheon.leafy.application.repository;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feed.FeedTag;
import bucheon.leafy.domain.feed.response.FeedMonthlyInformation;
import bucheon.leafy.domain.feed.response.FeedMonthlyResponse;
import bucheon.leafy.domain.user.Address;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.domain.user.UserImage;
import bucheon.leafy.domain.user.request.SignUpRequest;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;


class FeedRepositoryTest extends IntegrationTestSupport {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedRepository feedRepository;

    @Test
    @DisplayName("회원의 1년 동안의 월별 활동 내역을 카운트하여 가져온다.")
    @Transactional
    void testGroupByMonthlyCountByUserId(){
        //given
        Feed feed1 = createFeed("1월 게시물");
        Feed feed2 = createFeed("2월 게시물");
        Feed feed3 = createFeed("7월 게시물");
        Feed feed4 = createFeed("7월 게시물");

        List<Feed> feeds = List.of(feed1, feed2, feed3, feed4);

        feedRepository.saveAll(feeds);

        feed1.updateCreatedAt( LocalDateTime.of(2023, 1, 1, 12 , 0) );
        feed2.updateCreatedAt( LocalDateTime.of(2023, 2, 1, 12 , 0) );
        feed3.updateCreatedAt( LocalDateTime.of(2023, 7, 1, 12 , 0) );
        feed4.updateCreatedAt( LocalDateTime.of(2023, 7, 1, 12 , 0) );

        feedRepository.saveAll(feeds);

        User user = createUser("ekxk1234@naver.com", "정철희", feeds);
        User saveUser = userRepository.save(user);

        //when
        List<FeedMonthlyInformation> feedMonthlyResponse = feedRepository.groupByMonthlyCountByUserId(saveUser.getId());

        List<FeedMonthlyResponse> feedMonthlyResponses = feedMonthlyResponse.stream()
                .map(FeedMonthlyResponse::of)
                .collect(Collectors.toList());

        //then
        Assertions.assertThat(feedMonthlyResponses)
                .extracting("year", "month", "count")
                .containsExactlyInAnyOrder(
                        new Tuple(2023, 1, 1L),
                        new Tuple(2023, 2, 1L),
                        new Tuple(2023, 7, 2L)
                );

    }

    @Test
    @Commit
    @Transactional
    @DisplayName("일주일 동안 가장 인기 있는 게시물 100개 중에 가장 많이 존재하는 태그 10개를 조회한다.")
    void testGetPopular10TagsInTop100Feeds(){
        // given
        EasyRandomParameters parameters = new EasyRandomParameters()
                .randomize(Long.class, () -> ThreadLocalRandom.current().nextLong(0, 50001))
                .excludeField(named("feed_id").and(ofType(Long.class)))
                .excludeField(named("id").and(ofType(Long.class)));

        EasyRandom easyRandom = new EasyRandom(parameters);

        List<User> users = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            SignUpRequest signUpRequest = easyRandom.nextObject(SignUpRequest.class);
            User user = User.of(signUpRequest);
            users.add(user);
        }

        for (int i = 0; i < 1000; i++) {
            FeedLikeCount feedLikeCount = easyRandom.nextObject(FeedLikeCount.class);
            Feed feed = easyRandom.nextObject(Feed.class);

            for (int j = 0; j < 3; j++) {
                //
                FeedTag feedTag = FeedTag.of( generatePlantNames() );
                feed.getFeedTags().add(feedTag);
            }

            feed.initFeedLikeCount(feedLikeCount);
            feedLikeCount.initFeed(feed);

            User randomUser = users.get((int) (Math.random() * users.size()));

            randomUser.getFeeds().add(feed);
        }

        userRepository.saveAll(users);

        // when
        List<String> popular10TagsInTop100Feeds = feedRepository.getPopular10TagsInTop100Feeds();

        // then
        Assertions.assertThat(popular10TagsInTop100Feeds).hasSize(10);

        for (String popular10TagsInTop100Feed : popular10TagsInTop100Feeds) {
            System.out.println("popular10TagsInTop100Feed = " + popular10TagsInTop100Feed);
        }

    }

    private Feed createFeed(String title) {
        FeedLikeCount likeCount = FeedLikeCount.of( 0L );

        return Feed.builder()
                .title(title)
                .content("내용")
                .feedLikeCount(likeCount)
                .build();
    }

    private User createUser(String email, String nickName, List<Feed> feeds) {
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
                .address(address)
                .userImage(image)
                .email(email)
                .phone("01012341234")
                .nickName(nickName)
                .password("비밀번호")
                .feeds(feeds)
                .build();
    }

    public static String generatePlantNames(){

        String[] plantNames = {
                "Rose", "Tulip", "Sunflower", "Lily", "Daisy", "Orchid", "Hydrangea", "Carnation",
                "Peony", "Chrysanthemum", "Hibiscus", "Violet", "Cactus", "Fern", "Begonia", "Marigold",
                "Aloe Vera", "Bamboo", "Bonsai", "Camellia", "Cyclamen", "Jasmine", "Lavender", "Maple",
                "Olive", "Palm", "Ivy", "Poinsettia", "Rhododendron", "Succulent", "Zinnia", "Aster",
                "Azalea", "Bougainvillea", "Clematis", "Daffodil", "Gardenia", "Geranium", "Holly",
                "Kalanchoe", "Mimosa", "Narcissus", "Oregano", "Primrose", "Snapdragon", "Thyme",
                "Yarrow", "Zebra Plant", "Fiddle Leaf Fig", "Peace Lily", "Spider Plant", "Money Plant",
                "Rubber Plant", "Monstera", "String of Pearls", "Snake Plant", "ZZ Plant", "African Violet",
                "Boston Fern", "Bird of Paradise", "Lavender", "Mint", "Pansy", "Petunia", "Salvia",
                "Verbena", "Viola", "Wisteria", "Angel's Trumpet", "Cherry Blossom", "Dogwood", "Forsythia",
                "Ginkgo", "Jasmine", "Magnolia", "Nandina", "Poppy", "Ranunculus", "Sage", "Tansy",
                "Tulsi", "Valerian", "Wallflower", "Yucca", "Amaryllis", "Bleeding Heart", "Chicory",
                "Dandelion", "Eucalyptus", "Foxglove", "Ginseng", "Heather", "Iris", "Jonquil", "Kale",
                "Lantana", "Mistletoe", "Narcissus", "Nasturtium", "Oregano", "Poinsettia", "Ragwort",
                "Snapdragon", "Thistle", "Yarrow"
        };

        Random random = new Random();
        int index = random.nextInt(plantNames.length);

        return plantNames[index];
    }

}