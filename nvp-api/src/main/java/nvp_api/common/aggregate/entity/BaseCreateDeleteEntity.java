package nvp_api.common.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseCreateDeleteEntity extends CreateTimeEntity {

    @Column(name = "deleted_at")
    protected LocalDateTime deletedAt;

    public void delete(){
        this.deletedAt = LocalDateTime.now();
    }

}
