package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.FeedLikeRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLikeCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedLikeService {

    private final FeedLikeRepository feedLikeRepository;

    public void saveLike(Feed feed) {
        FeedLikeCount feedLikeCount = FeedLikeCount.of(feed);
        feedLikeCount.like();

        feedLikeRepository.save(feedLikeCount);
    }

    public void deleteLike(Feed feed) {
        FeedLikeCount feedLikeCount = FeedLikeCount.of(feed);
        feedLikeCount.likeCancel();

        feedLikeRepository.save(feedLikeCount);
    }

}
