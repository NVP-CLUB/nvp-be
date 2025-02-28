package nvp_api.common.aggregate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseFullEntity extends BaseCreateUpdateEntity{

    @Column(name = "deleted_at")
    protected LocalDateTime deletedAt;

    public void delete(){
        this.deletedAt = LocalDateTime.now();
    }

}
