package bucheon.leafy.application.controller;

import bucheon.leafy.application.service.AlarmService;
import bucheon.leafy.config.AuthUser;
import bucheon.leafy.domain.alarm.response.AlarmResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "알림")
@RestController
@RequestMapping("/api/v1/alarm")
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    // TODO 스크롤 적용해서 코드 수정하기
    @Operation(summary = "유저의 알림 리스트 조회")
    @GetMapping
    public ResponseEntity<List<AlarmResponse>> getAlarm(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user) {
        return ResponseEntity.ok().body(alarmService.getAlarm(user));
    }

    @Operation(summary = "유저의 새 알림 갯수 조회")
    @GetMapping("/count")
    public ResponseEntity<Integer> getNewAlarmCount(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user) {
        return ResponseEntity.ok().body(alarmService.getNewAlarmCount(user));
    }

    @Operation(summary = "유저의 알림 삭제 (단건)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlarm(@AuthenticationPrincipal @Parameter(hidden = true) AuthUser user,
                                              @PathVariable @Parameter(description = "삭제할 알림 id") long id){
        return ResponseEntity.ok().body(alarmService.deleteAlarm(user, id));
    }



}
