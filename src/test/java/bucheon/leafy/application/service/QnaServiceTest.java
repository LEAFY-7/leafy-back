package bucheon.leafy.application.service;

import bucheon.leafy.application.mapper.QnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class QnaServiceTest {

    @Autowired
    private QnaMapper qnaMapper;
    @Autowired
    private AlarmService alarmService;

//    @Test
//    void getRead(Long qnaId, AuthUser user){
//        Long userId = user.getUserId();
//        // 유저가 인증되어 있고, 알림을 처리해야 할 경우
//        if (userId != null) {
//            List<QnaAlamResponse> resultIds = qnaMapper.selectQnaCommentIdAndQnaReplyIdByQnaId(qnaId);
//            if (resultIds != null) {
//                for (int i = 0; i < resultIds.size(); i++) {
//                    QnaAlamResponse response = resultIds.get(i);
//                    if (response != null) {
//                        if (response.getQnaComment() != null) {
//                            alarmService.readAlarm(user.getUserId(), AlarmType.QNA_COMMENT, response.getQnaComment());
//                        }
//                        if (response.getQnaReply() != null) {
//                            alarmService.readAlarm(user.getUserId(), AlarmType.QNA_REPLY, response.getQnaReply());
//                        }
//                    }
//                }
//            }
//        }
//    }



}