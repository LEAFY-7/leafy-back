package bucheon.leafy.domain.user;

import bucheon.leafy.domain.user.request.SignUpRequest;
import bucheon.leafy.util.entity.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseDeleteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String zipcode;

    private String street;

    private String lot;

    private String detail;

    private String reference;

    private Boolean isHide;

    @Builder
    private Address(String zipcode, String street, String lot, String detail, String reference, Boolean isHide) {
        this.zipcode = zipcode;
        this.street = street;
        this.lot = lot;
        this.detail = detail;
        this.reference = reference;
        this.isHide = isHide;
    }

    public static Address of(SignUpRequest signUpRequest) {
        return Address.builder()
                .zipcode(signUpRequest.getZipcode())
                .street(signUpRequest.getStreet())
                .lot(signUpRequest.getLot())
                .detail(signUpRequest.getDetail())
                .reference(signUpRequest.getReference())
                .isHide(signUpRequest.getAddressIsHide())
                .build();
    }

}
