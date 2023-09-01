package bucheon.leafy.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {

    CREATE_DATE("create_at"),
    FAVORITE("like_count"),
    MODIFY_DATE("modified_at");

    private final String text;

}
