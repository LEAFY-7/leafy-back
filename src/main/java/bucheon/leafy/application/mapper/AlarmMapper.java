package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.alarm.request.AlarmCheckRequest;
import bucheon.leafy.domain.alarm.request.AlarmRequest;
import bucheon.leafy.domain.alarm.response.AlarmResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper
public interface AlarmMapper {

    int saveAlarm(AlarmRequest request);

    int updateCheckAlarm(AlarmCheckRequest request);

    int deleteAlarm();

    List<AlarmResponse> findByUserId(Long userId);
}
