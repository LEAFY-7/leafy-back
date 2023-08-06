package bucheon.leafy.domain.follow;

import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.entity.BaseEntity;
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
    @Column(name = "follow_id")
    private Long id;

    // 나, 본인
    @ManyToOne(fetch = FetchType.LAZY)
    private User follower;

    // 내가 팔로우하는 대상
    @ManyToOne(fetch = FetchType.LAZY)
    private User following;

    @Builder
    private Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }

    public static Follow of(User user, User followTarget) {
        return Follow.builder()
                .follower(user)
                .following(followTarget)
                .build();
    }

}
