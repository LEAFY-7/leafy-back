package bucheon.leafy.domain.user;

import bucheon.leafy.domain.user.request.SignUpRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    private String image;

    @Builder
    private UserImage(String image) {
        this.image = image;
    }

    // TODO : 서버 띄우고 서버 주소에 맞게 이미지의 경로 설정 및 인코딩 로직이 별도로 들어가야함
    public static UserImage of(SignUpRequest signUpRequest) {
        return UserImage.builder()
                .image(signUpRequest.getUserImage())
                .build();
    }
}
