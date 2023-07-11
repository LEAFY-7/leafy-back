package bucheon.leafy.domain.qna;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QnaType {

    HOLD("질문 대기"), DONE("답변 완료");


    private final String text;

}
