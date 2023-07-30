package bucheon.leafy.domain.user;

import bucheon.leafy.util.entity.BaseEntity;
import lombok.AccessLevel;
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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long number;

}
