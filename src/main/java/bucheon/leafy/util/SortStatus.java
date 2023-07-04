package bucheon.leafy.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortStatus {

    ASC("오름차순"), DESC("내림차순");

    private final String text;

}
