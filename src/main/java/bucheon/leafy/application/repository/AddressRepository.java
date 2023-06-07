package bucheon.leafy.application.repository;

import bucheon.leafy.domain.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
