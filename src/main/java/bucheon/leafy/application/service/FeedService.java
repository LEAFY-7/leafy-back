package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedMapper;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.exception.FeedNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;

    public List<Feed> getFeedList() {
        return mapper.findFeedFirst();
    }

    public List<Feed> getFeedList(Long id) {
        return mapper.findFeedScroll(id);
    }

    public Feed getFeedById(Long id) {
        Optional<Feed> optFeed = Optional.of(mapper.findFeedById(id));

        Feed feed = optFeed.orElseThrow(FeedNotFoundException::new);

        return feed;
    }

    public Long saveFeed(Feed feed) {
        Long id = mapper.saveFeed(feed);

        return id;
    }

    public Long updateFeed(Feed feed) {
        mapper.editFeed(feed);

        Long id = feed.getId();

        return id;
    }

    public boolean deleteFeed(Long id) {

        return mapper.softDeleteFeed(id) == 1;
    }
}
