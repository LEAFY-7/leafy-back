package bucheon.leafy.domain.qna;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailSend {
        private Long qnaId;
        private String answerContent;
        private Long id;

}
