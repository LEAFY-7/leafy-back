package bucheon.leafy.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ADMIN("관리자"), MEMBER("일반 계정");

    private final String title;

    public static UserRole getRole(String userRole){
        if (userRole.equals("ADMIN")) {
            return ADMIN;
        }
        else if (userRole.equals("MEMBER")) {
                return MEMBER;
        }
       throw new IllegalArgumentException("잘못된 데이터가 넘어왔습니다.");
    }

}
