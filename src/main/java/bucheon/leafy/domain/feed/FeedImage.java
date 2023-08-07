package bucheon.leafy.domain.feed;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_image_id")
    private Long id;

    private String imageName;

    @Builder
    private FeedImage(String imageName) {
        this.imageName = imageName;
    }

    public static FeedImage of(String imageName) {
        return FeedImage.builder()
                .imageName(imageName)
                .build();
    }
}
