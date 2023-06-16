package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedMapper;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.request.FeedRequest;
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

    public List<Feed> getFeeds() {
        return mapper.findFeedListFirst();
    }

    public List<Feed> getFeeds(Long id) {
        return mapper.findFeedListScroll(id);
    }


    public Feed getFeedById(Long id) {
        return mapper.findFeedById(id).orElseThrow(FeedNotFoundException::new);
    }

    public Long saveFeed(FeedRequest feed) {
        Long id = mapper.saveFeed(feed);

        return id;
    }

    public Long updateFeed(FeedRequest feed) {
        mapper.editFeed(feed);

        Long id = feed.getId();

        return id;
    }

    public boolean deleteFeed(Long id) {
        return mapper.softDeleteFeed(id) == 1;
    }
}
