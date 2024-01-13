package bucheon.leafy.application.service;

import bucheon.leafy.application.event.request.LikeCancelEvent;
import bucheon.leafy.application.event.request.LikeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class FeedLikeService {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void saveLike(Long userId, Long feedId) {
        applicationEventPublisher.publishEvent( LikeEvent.of(feedId, userId) );
    }

    public void deleteLike(Long userId, Long feedId) {
        applicationEventPublisher.publishEvent( LikeCancelEvent.of(feedId, userId) );
    }

}
