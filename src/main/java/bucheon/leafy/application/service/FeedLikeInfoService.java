package bucheon.leafy.application.service;

import bucheon.leafy.application.repository.FeedLikeInfoRepository;
import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.feed.response.FeedWithLikeCountResponse;
import bucheon.leafy.domain.feedLikeInfo.FeedLikeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedLikeInfoService {

    private final FeedLikeInfoRepository feedLikeInfoRepository;

    public List<FeedWithLikeCountResponse> getFeedByUserId(Long userId, Pageable pageable) {
        List<FeedLikeInfo> feedLikeInfos = feedLikeInfoRepository.findAllWithFeedByUserId(userId, pageable).getContent();

        List<Feed> feeds = feedLikeInfos.stream()
                .map(FeedLikeInfo::getFeed)
                .collect(Collectors.toList());

        return feeds.stream()
                .map(FeedWithLikeCountResponse::of)
                .collect(Collectors.toList());
    }
}
