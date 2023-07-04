package bucheon.leafy.util.entity;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseDeleteEntity extends BaseEntity {

    private Boolean isDelete = false;

    public void delete(){
        isDelete = true;
    }

}
