package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.AlarmMapper;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.alarm.request.AlarmCheckRequest;
import bucheon.leafy.domain.alarm.request.AlarmRequest;
import bucheon.leafy.domain.alarm.response.AlarmResponse;
import bucheon.leafy.exception.AlarmDataAccessException;
import bucheon.leafy.exception.AlarmNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public String deleteAlarm(AuthUser user, long id){
        Optional<HashMap<String, Object>> data = alarmMapper.findById(id);
        data.orElseThrow(AlarmNotExistException::new);

        if(Long.parseLong(data.get().get("user_id").toString()) != user.getUserId()){
            throw new AlarmNotExistException();
        }
        if(alarmMapper.deleteOneAlarm(id) != 1){
            throw new AlarmDataAccessException();
        }
        return "댓글 삭제 성공";
    }

    public List<AlarmResponse> getAlarm(AuthUser user) {
        return alarmMapper.findByUserId(user.getUserId());
    }

    public int getNewAlarmCount(AuthUser user) {
        return alarmMapper.findCountByUserId(user.getUserId());
    }
}
