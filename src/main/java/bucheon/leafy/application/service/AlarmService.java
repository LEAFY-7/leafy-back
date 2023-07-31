package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.AlarmMapper;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.AlarmType;
import bucheon.leafy.domain.alarm.request.AlarmCheckRequest;
import bucheon.leafy.domain.alarm.request.AlarmRequest;
import bucheon.leafy.domain.alarm.response.AlarmResponse;
import bucheon.leafy.exception.AlarmDataAccessException;
import bucheon.leafy.exception.AlarmNotExistException;
import bucheon.leafy.util.request.ScrollRequest;
import bucheon.leafy.util.response.ScrollResponse;
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
    public void createAlarm(long userId, AlarmType type, long tableId, String msg){
        AlarmRequest request = new AlarmRequest(userId, type, tableId, msg);
        alarmMapper.saveAlarm(request);
    }

    @Async
    @Transactional
    public void readAlarm(long userId, AlarmType type, long tableId){
        AlarmCheckRequest request = new AlarmCheckRequest(userId, type, tableId);
        alarmMapper.updateCheckAlarm(request);
    }

    @Async
    @Transactional
    public void deleteAlarm(){alarmMapper.deleteAlarm();}

    @Transactional
    public void deleteAlarm(AuthUser user, long id){
        Optional<HashMap<String, Object>> data = alarmMapper.findById(id);
        data.orElseThrow(AlarmNotExistException::new);

        if(Long.parseLong(data.get().get("user_id").toString()) != user.getUserId()){
            throw new AlarmNotExistException();
        }
        if(alarmMapper.deleteOneAlarm(id) != 1){
            throw new AlarmDataAccessException();
        }
    }

    public ScrollResponse getAlarm(AuthUser user, ScrollRequest scrollRequest) {
        if(scrollRequest.hasKey()){
            List<AlarmResponse> alarms =  alarmMapper.findByUserId(user.getUserId(), scrollRequest);
            ScrollRequest nextScrollRequest = getNextKey(alarms, scrollRequest);
            return new ScrollResponse(nextScrollRequest, alarms);
        } else {
            List<AlarmResponse> alarms = alarmMapper.findFirstByUserId(user.getUserId(), scrollRequest);
            ScrollRequest nextScrollRequest = getNextKey(alarms, scrollRequest);
            return new ScrollResponse(nextScrollRequest, alarms);
        }
    }

    private ScrollRequest getNextKey(List<AlarmResponse> alarms, ScrollRequest scrollRequest){
        if(alarms.size() < ScrollRequest.size){
            return scrollRequest.next(ScrollRequest.NONE_KEY);
        } else {
            long nextKey = alarms.stream()
                    .reduce((first, second) -> second)
                    .map(AlarmResponse::getAlarmId)
                    .get();
            return scrollRequest.next(nextKey);
        }
    }


    public int getNewAlarmCount(AuthUser user) {
        return alarmMapper.countByUserId(user.getUserId());
    }
}
