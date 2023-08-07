package bucheon.leafy.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ADMIN("관리자"), MEMBER("일반 계정"), READ_ONLY("읽기 계정");

    private final String title;

}
