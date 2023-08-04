package bucheon.leafy.application.component.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailRequest {

    private String address;
    private String title;
    private String message;

    @Builder
    public MailRequest(String address, String title, String message) {
        this.address = address;
        this.title = title;
        this.message = message;
    }
}
