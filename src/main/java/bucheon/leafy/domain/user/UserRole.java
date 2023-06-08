package bucheon.leafy.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ADMIN("관리자"), NORMAL("일반 계정");

    private final String title;

}
