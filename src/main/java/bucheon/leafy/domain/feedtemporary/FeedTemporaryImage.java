package bucheon.leafy.domain.feedtemporary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedTemporaryImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feed_temporary_image_id")
    private Long id;

    private String imageName;

    private String imagePath;

    private Boolean isThumb;

}
