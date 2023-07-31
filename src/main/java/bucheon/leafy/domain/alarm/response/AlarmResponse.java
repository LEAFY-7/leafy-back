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
    private AlarmType type;
    private Boolean checked;
    private Long tableId;
    private String msg;
    private Date createdAt;

    @Builder
    public AlarmResponse(Long alarm_id, Long user_id, AlarmType type,
                         Boolean checked, Long table_id, String msg, Date created_at) {
        this.alarmId = alarm_id;
        this.userId = user_id;
        this.type = type;
        this.checked = checked;
        this.tableId = table_id;
        this.msg = msg;
        this.createdAt = created_at;
    }
}
