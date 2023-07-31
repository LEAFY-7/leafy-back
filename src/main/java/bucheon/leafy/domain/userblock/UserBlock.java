package bucheon.leafy.domain.userblock;

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
public class UserBlock extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_block_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 차단 대상
    @ManyToOne
    @JoinColumn(name = "block_user_id")
    private User blockUser;

    @Builder
    private UserBlock(User user, User blockUser) {
        this.user = user;
        this.blockUser = blockUser;
    }

    public static UserBlock of(User user, User blockUser) {
        return UserBlock.builder()
                .user(user)
                .blockUser(blockUser)
                .build();
    }

}
