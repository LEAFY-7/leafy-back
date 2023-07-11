package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.FeedImageMapper;
import bucheon.leafy.domain.feed.dto.request.FeedImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedImageService {

    private final FeedImageMapper mapper;

    public void saveImage(List<FeedImageRequest> requestList) {
        mapper.saveImage(requestList);
    }
}
