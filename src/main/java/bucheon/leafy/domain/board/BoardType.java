package bucheon.leafy.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardType {

    PRIVATE("비공개"), PUBLIC("공개");

    private final String title;

}
