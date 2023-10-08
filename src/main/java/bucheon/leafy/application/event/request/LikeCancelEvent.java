package bucheon.leafy.application.event.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeCancelEvent {

    private Long feedId;
    private Long userId;

    @Builder
    private LikeCancelEvent(Long feedId, Long userId) {
        this.feedId = feedId;
        this.userId = userId;
    }

    public static LikeCancelEvent of(Long feedId, Long userId) {
        return LikeCancelEvent.builder()
                .feedId(feedId)
                .userId(userId)
                .build();
    }
}
