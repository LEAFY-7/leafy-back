package bucheon.leafy.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginType {

    NORMAL("기본 로그인"), KAKAO("카카오 로그인"), GOOGLE("구글 로그인");

    private final String title;

}
