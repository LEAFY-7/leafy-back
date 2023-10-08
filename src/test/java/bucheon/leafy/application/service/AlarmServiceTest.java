package bucheon.leafy.application.service;

import bucheon.leafy.application.IntegrationTestSupport;
import bucheon.leafy.domain.alarm.AlarmType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.*;


@Transactional
class AlarmServiceTest extends IntegrationTestSupport {

    @Autowired
    AlarmService alarmService;


    @Test
    @DisplayName("알림을 저장한다")
    void testSaveAlarm(){
        AlarmService alarmService = mock(AlarmService.class);
        alarmService.createAlarm(1, AlarmType.NOTICE, 1);

        verify(alarmService, times(1)).createAlarm(1, AlarmType.NOTICE, 1);
    }

    @Test
    @DisplayName("알림 읽음 처리한다")
    void testUpdateCheckAlarm(){
        AlarmService alarmService = mock(AlarmService.class);
        alarmService.readAlarm(1, AlarmType.NOTICE, 1);

        verify(alarmService, times(1)).readAlarm(1, AlarmType.NOTICE, 1);

    }

}