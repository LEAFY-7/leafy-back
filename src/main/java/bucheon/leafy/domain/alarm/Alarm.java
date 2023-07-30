package bucheon.leafy.domain.alarm;

import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends BaseDeleteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    private AlarmType alarmType;

    private Boolean checked;

    private Long tableId;

    private String message;

    @Builder
    private Alarm(AlarmType alarmType, Boolean checked, Long tableId, String message) {
        this.alarmType = alarmType;
        this.checked = checked;
        this.tableId = tableId;
        this.message = message;
    }

//    public static Alarm of() {
//        return Alarm.builder().build();
//    }

}
