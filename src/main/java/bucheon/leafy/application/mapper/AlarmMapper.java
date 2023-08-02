package bucheon.leafy.application.mapper;

import bucheon.leafy.domain.alarm.request.AlarmCheckRequest;
import bucheon.leafy.domain.alarm.request.AlarmRequest;
import bucheon.leafy.domain.alarm.response.AlarmResponse;
import bucheon.leafy.util.request.ScrollRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Mapper
public interface AlarmMapper {

    int saveAlarm(AlarmRequest request);

    int updateCheckAlarm(AlarmCheckRequest request);

    int deleteAlarm();

    int deleteOneAlarm(Long id);

    List<AlarmResponse> findByUserId(@Param("userId") Long userId, @Param("scrollRequest") ScrollRequest scrollRequest);

    int countByUserId(Long userId);

    Optional<HashMap<String, Object>> findById(long id);

    List<AlarmResponse> findFirstByUserId(@Param("userId") Long userId, @Param("scrollRequest") ScrollRequest scrollRequest);
}
