package bucheon.leafy.domain.alarm.response;

import bucheon.leafy.domain.alarm.AlarmType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.Date;

@Data
@NoArgsConstructor
public class AlarmResponse {

    private Long alarmId;
    private Long userId;
    private AlarmType alarmType;
    private Boolean checked;
    private Long tableId;
    private String message;
    private Date createdAt;

    @Builder
    public AlarmResponse(Long alarm_id, Long user_id, AlarmType alarmType,
                         Boolean checked, Long table_id, String message, Date created_at) {
        this.alarmId = alarm_id;
        this.userId = user_id;
        this.alarmType = alarmType;
        this.checked = checked;
        this.tableId = table_id;
        this.message = message;
        this.createdAt = created_at;
    }
}
