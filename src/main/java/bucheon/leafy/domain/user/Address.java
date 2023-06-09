package bucheon.leafy.domain.user;

import bucheon.leafy.domain.user.dto.request.SignUpRequest;
import bucheon.leafy.util.BaseDeleteEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseDeleteEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zipcode;

    private String street;

    private String lot;

    private String detail;

    private String reference;

    @Builder
    private Address(String zipcode, String street, String lot, String detail, String reference) {
        this.zipcode = zipcode;
        this.street = street;
        this.lot = lot;
        this.detail = detail;
        this.reference = reference;
    }

    public static Address of(SignUpRequest signUpRequest) {
        return Address.builder()
                .zipcode(signUpRequest.getZipcode())
                .street(signUpRequest.getStreet())
                .lot(signUpRequest.getLot())
                .detail(signUpRequest.getDetail())
                .reference(signUpRequest.getReference())
                .build();
    }

}
