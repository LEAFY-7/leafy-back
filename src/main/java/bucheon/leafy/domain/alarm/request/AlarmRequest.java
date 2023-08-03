package bucheon.leafy.domain.alarm.request;

import bucheon.leafy.domain.alarm.AlarmType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlarmRequest {

    private Long userId;
    private AlarmType type;
    private Long tableId;
    private String msg;

    @Builder
    public AlarmRequest(Long userId, AlarmType type, Long tableId, String msg) {
        this.userId = userId;
        this.type = type;
        this.tableId = tableId;
        this.msg = msg;
    }
}
