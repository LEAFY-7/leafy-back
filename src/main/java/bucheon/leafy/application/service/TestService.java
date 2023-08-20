package bucheon.leafy.application.service;

import bucheon.leafy.application.component.ImageComponent;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import bucheon.leafy.domain.feed.FeedTag;
import bucheon.leafy.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static bucheon.leafy.path.S3Path.FEED_PATH;
import static org.jeasy.random.FieldPredicates.named;
import static org.jeasy.random.FieldPredicates.ofType;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final UserRepository userRepository;

    private final ImageComponent imageComponent;

    public void createDummyFeed(List<MultipartFile> files) {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .randomize(Long.class, () -> ThreadLocalRandom.current().nextLong(0, 501))
                .excludeField(named("feed_id").and(ofType(Long.class)))
                .excludeField(named("id").and(ofType(Long.class)));

        EasyRandom easyRandom = new EasyRandom(parameters);

        List<User> users = userRepository.findAll();

        for (int i = 0; i < 10; i++) {
            FeedLikeCount feedLikeCount = easyRandom.nextObject(FeedLikeCount.class);
            Feed feed = easyRandom.nextObject(Feed.class);
            for (int j = 0; j < 3; j++) {
                FeedTag feedTag = FeedTag.of( generatePlantNames() );
                feed.getFeedTags().add(feedTag);
            }

            feed.initFeedLikeCount(feedLikeCount);
            feedLikeCount.initFeed(feed);

            List<String> fileNames = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                String fileName = imageComponent.createUUID();
                imageComponent.uploadImage(FEED_PATH, files.get(((int) (Math.random() * files.size()))), fileName);
                fileNames.add(fileName);
            }

//            feed.addFeedImages(fileNames);

            User randomUser = users.get((int) (Math.random() * users.size()));
            randomUser.getFeeds().add(feed);
        }

        userRepository.saveAll(users);
    }

    private static String generatePlantNames(){
        Random random = new Random();
        int index = random.nextInt(plantNames.length);

        return plantNames[index];
    }

    private static String[] plantNames = {
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

}