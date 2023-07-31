package bucheon.leafy.domain.alarm.request;

import bucheon.leafy.domain.alarm.AlarmType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlarmRequest {

    private Long userId;
    private AlarmType alarmType;
    private Long tableId;
    private String message;

    @Builder
    public AlarmRequest(Long userId, AlarmType alarmType, Long tableId, String message) {
        this.userId = userId;
        this.alarmType = alarmType;
        this.tableId = tableId;
        this.message = message;
    }
}
