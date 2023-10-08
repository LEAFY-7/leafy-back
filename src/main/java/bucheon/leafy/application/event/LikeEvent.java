package bucheon.leafy.application.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LikeEvent {

    private Long feedId;
    private Long userId;

    @Builder
    private LikeEvent(Long feedId, Long userId) {
        this.feedId = feedId;
        this.userId = userId;
    }

    public static LikeEvent of(Long feedId, Long userId) {
        return LikeEvent.builder()
                .feedId(feedId)
                .userId(userId)
                .build();
    }
}
