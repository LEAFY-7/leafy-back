package bucheon.leafy.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserBackgroundImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_background_image_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private String image;

    @Builder
    private UserBackgroundImage(String image, User user) {
        this.image = image;
        this.user = user;
    }

    public static UserBackgroundImage of(String image, User user) {
        return UserBackgroundImage.builder()
                .image(image)
                .user(user)
                .build();
    }


    public void update(String image) {
        this.image = image;
    }
}
