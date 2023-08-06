package bucheon.leafy.domain.user.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CertificationNumberResponse {

    private String email;
    private String number;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder

    public CertificationNumberResponse(String email, String number, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.email = email;
        this.number = number;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
