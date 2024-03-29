package bucheon.leafy.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserTest {

    @Test
    @DisplayName("유저 삭제 기능 테스트")
    void testUserDeleteFunction(){
        //given

        UserImage image = UserImage.builder()
                .image("이미지")
                .build();

        User user = User.builder()
                .userImage(image)
                .email("email@email.com")
                .phone("01012341234")
                .nickName("별명")
                .password("비밀번호")
                .build();

        //when
        user.delete();

        //then
        Assertions.assertThat(user.getIsDelete()).isTrue();
    }

}