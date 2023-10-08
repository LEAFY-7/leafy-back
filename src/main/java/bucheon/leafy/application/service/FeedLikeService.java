package bucheon.leafy.application.service;

import bucheon.leafy.application.event.LikeCancelEvent;
import bucheon.leafy.application.event.LikeEvent;
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


    // 레코드에 직접적으로 락을 걸기 때문에 성능이 떨어질 수 있음
    // 추후에 saveLikeInfo 메서드를 다른 트랜잭션(컨트롤러 레이어)으로 변경을 하거나 낙관적 락으로 변경을 고려
    public void saveLike(Long userId, Long feedId) {
        applicationEventPublisher.publishEvent( LikeEvent.of(feedId, userId) );
    }

    public void deleteLike(Long userId, Long feedId) {
        applicationEventPublisher.publishEvent( LikeCancelEvent.of(feedId, userId) );
    }

}
