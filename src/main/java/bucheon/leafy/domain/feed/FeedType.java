package bucheon.leafy.domain.feed;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FeedType {

    PRIVATE("비공개"), PUBLIC("공개");

    private final String title;

}
