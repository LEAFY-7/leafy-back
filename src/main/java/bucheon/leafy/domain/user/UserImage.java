package bucheon.leafy.domain.user;

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
    @Column(name = "user_image_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String image;

    @Builder
    private UserImage(String image, User user) {
        this.image = image;
        this.user = user;
    }

    // TODO : 서버 띄우고 서버 주소에 맞게 이미지의 경로 설정 및 인코딩 로직이 별도로 들어가야함
    public static UserImage of(String image, User user) {
        return UserImage.builder()
                .image(image)
                .user(user)
                .build();
    }

    public void update(String image){
        this.image = image;
    }

}
