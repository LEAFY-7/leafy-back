package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.FeedBlockRepository;
import bucheon.leafy.application.repository.FeedRepository;
import bucheon.leafy.application.repository.UserRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.response.FeedResponse;
import bucheon.leafy.domain.feedblock.FeedBlock;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.exception.FeedNotFoundException;
import bucheon.leafy.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class FeedBlockService {

    private final UserRepository userRepository;
    private final FeedRepository feedRepository;
    private final FeedBlockRepository feedBlockRepository;

    public List<FeedResponse> getBlockedFeeds(Long userId, Pageable pageable) {
        List<FeedBlock> feedBlocks = feedBlockRepository.findByUserId(userId, pageable);

        List<Feed> feeds = feedBlocks.stream()
                .map(FeedBlock::getFeed)
                .collect(Collectors.toList());

        List<Long> feedIds = feeds.stream()
                .map(Feed::getId)
                .collect(Collectors.toList());

//        List<User> users = userRepository.findAllUserByFeedIn(feedIds);

        return null;
    }

    public void blockFeed(Long userId, Long feedId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(FeedNotFoundException::new);

        FeedBlock feedBlock = FeedBlock.of(user, feed);
        feedBlockRepository.save(feedBlock);
    }

    public void noneBlockFeed(Long userId, Long feedId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(FeedNotFoundException::new);

        feedBlockRepository.deleteByUserAndFeed(user, feed);
    }

}
