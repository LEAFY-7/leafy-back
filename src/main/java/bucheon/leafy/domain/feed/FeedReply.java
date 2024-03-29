package bucheon.leafy.domain.feed;

import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Getter
@Entity
@SQLDelete(sql = "update feedReply set is_delete = true where feed_reply_id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedReply extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="feed_reply_id")
    private Long id;

    private String reply;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    private FeedReply(String reply) {
        this.reply = reply;
    }
}
