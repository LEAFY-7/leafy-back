package bucheon.leafy.domain.alarm.request;

import bucheon.leafy.domain.alarm.AlarmType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlarmCheckRequest {

    private Long userId;
    private AlarmType alarmType;
    private Long tableId;

    @Builder
    public AlarmCheckRequest(Long userId, AlarmType alarmType, Long tableId) {
        this.userId = userId;
        this.alarmType = alarmType;
        this.tableId = tableId;
    }
}
