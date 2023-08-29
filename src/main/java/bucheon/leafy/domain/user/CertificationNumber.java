package bucheon.leafy.domain.user;

import bucheon.leafy.util.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CertificationNumber extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="certification_number_id")
    private Long id;

    private String email;

    private String number;

    @Builder
    private CertificationNumber(String email, String number) {
        this.email = email;
        this.number = number;
    }

    public static CertificationNumber of(String email, String number) {
        return CertificationNumber.builder()
                .email(email)
                .number(number)
                .build();
    }
}
