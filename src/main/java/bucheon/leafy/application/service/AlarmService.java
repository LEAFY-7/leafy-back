package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.AlarmMapper;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.alarm.request.AlarmCheckRequest;
import bucheon.leafy.domain.alarm.request.AlarmRequest;
import bucheon.leafy.domain.alarm.response.AlarmResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmMapper alarmMapper;

    @Async
    @Transactional
    public void crateAlarm(long userId, AlarmType type, long tableId, String msg){
        AlarmRequest request = new AlarmRequest();
        request.setUserId(userId);
        request.setType(type);
        request.setTableId(tableId);
        request.setMsg(msg);
        alarmMapper.saveAlarm(request);
    }

    @Async
    @Transactional
    public void raedAlarm(long userId, AlarmType type, long tableId){
        AlarmCheckRequest request = new AlarmCheckRequest();
        request.setUserId(userId);
        request.setType(type);
        request.setTableId(tableId);
        alarmMapper.updateCheckAlarm(request);
    }

    @Async
    @Transactional
    public void deleteAlarm(){alarmMapper.deleteAlarm();}


    public List<AlarmResponse> getAlarm(AuthUser user) {
        return alarmMapper.findByUserId(user.getUserId());
    }
}
