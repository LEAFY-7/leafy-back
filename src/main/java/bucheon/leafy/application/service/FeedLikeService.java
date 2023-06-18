package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.FeedLikeRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.FeedLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedLikeService {

    private final FeedLikeRepository feedLikeRepository;

    public void saveLike(Feed feed) {
        FeedLike feedLike = FeedLike.of(feed);
        feedLike.like();

        feedLikeRepository.save(feedLike);
    }

    public void deleteLike(Feed feed) {
        FeedLike feedLike = FeedLike.of(feed);
        feedLike.likeCancel();

        feedLikeRepository.save(feedLike);
    }

}
