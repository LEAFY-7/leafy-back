package bucheon.leafy.domain.alarm;

import bucheon.leafy.domain.user.User;
import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * TABLE_ID 설명
 * 공지 = 공지 ID
 * QNA = QNA ID
 * 새 팔로워 = 팔로워의 USER ID
 * 내 피드 댓글 = 내 피드 ID
 * 댓글의 답글 = 내 피드 ID
 * 팔로잉한 유저의 피드 = 내 피드 ID
 * 피드에 좋아요 = 내 피드 ID
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private AlarmType alarmType;

    private Boolean checked;

    private Long tableId;


    @Builder
    private Alarm(AlarmType alarmType, Boolean checked, Long tableId, User user) {
        this.alarmType = alarmType;
        this.checked = checked;
        this.tableId = tableId;
        this.user = user;
    }

    public static Alarm of(User user, AlarmType alarmType, Long tableId) {
        return Alarm.builder()
                .user(user)
                .alarmType(alarmType)
                .tableId(tableId)
                .checked(false)
                .build();
    }

}
