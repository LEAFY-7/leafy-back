package bucheon.leafy.domain.userInfo;

import bucheon.leafy.domain.feed.Feed;
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
public class UserInfo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Feed feed;

    @Builder
    private UserInfo(User user, Feed feed) {
        this.user = user;
        this.feed = feed;
    }

    public static UserInfo of(User user, Feed feed){
        return UserInfo.builder()
                .user(user)
                .feed(feed)
                .build();
    }

}

