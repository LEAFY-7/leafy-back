package bucheon.leafy.util.entity;

import lombok.Getter;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@Where(clause = "isDelete = false")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDeleteEntity extends BaseEntity {

    private Boolean isDelete = false;

    public void delete(){
        isDelete = true;
    }

}
