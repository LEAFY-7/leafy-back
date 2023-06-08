package bucheon.leafy.domain.follow;

import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends BaseEntity {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User follower;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User following;

    @Builder
    private Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }

}
