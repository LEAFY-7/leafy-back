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
import java.util.LinkedList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmMapper alarmMapper;


    @Async
    @Transactional
    public void createAlarm(long userId, AlarmType alarmType, long tableId){
        AlarmRequest request = new AlarmRequest(userId, alarmType, tableId);
        alarmMapper.saveAlarm(request);
    }

    @Async
    @Transactional
    public void readAlarm(long userId, AlarmType alarmType, long tableId){
        AlarmCheckRequest request = new AlarmCheckRequest(userId, alarmType, tableId);
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
            LinkedList<AlarmResponse> alarms = alarmMapper.findByUserId(user.getUserId(), scrollRequest);
            ScrollRequest nextScrollRequest = getNextKey(alarms, scrollRequest);
            return ScrollResponse.of(nextScrollRequest, alarms);
        } else {
            LinkedList<AlarmResponse> alarms = alarmMapper.findFirstByUserId(user.getUserId(), scrollRequest);
            ScrollRequest nextScrollRequest = getNextKey(alarms, scrollRequest);
            return ScrollResponse.of(nextScrollRequest, alarms);
        }
    }


    private ScrollRequest getNextKey(LinkedList<AlarmResponse> alarms, ScrollRequest scrollRequest){
        if(alarms.size() < ScrollRequest.size){
            return scrollRequest.next(ScrollRequest.NONE_KEY);
        } else {
            long nextKey = alarms.getLast().getAlarmId();
            return scrollRequest.next(nextKey);
        }
    }


    public int getNewAlarmCount(AuthUser user) {
        return alarmMapper.countByUserId(user.getUserId());
    }
}
