package bucheon.leafy.domain.notice;

import bucheon.leafy.domain.feed.Feed;
import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeReply extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_reply_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "feed_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Feed feed;

    private String comment;

    @Builder
    private NoticeReply(User user, Feed feed, String comment) {
        this.user = user;
        this.feed = feed;
        this.comment = comment;
    }

}
