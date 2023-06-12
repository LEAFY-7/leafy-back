package bucheon.leafy.domain.notice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeStatus {

    NOTICE("공지"), HOLD("질문 대기"), DONE("답변 완료");


    private final String text;

}
