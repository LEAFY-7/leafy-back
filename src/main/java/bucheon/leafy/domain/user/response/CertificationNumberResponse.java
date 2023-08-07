package bucheon.leafy.domain.user.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CertificationNumberResponse {

    private String number;
    private LocalDateTime createdAt;


    @Builder
    public CertificationNumberResponse(String number, LocalDateTime createdAt) {
        this.number = number;
        this.createdAt = createdAt;
    }
}
