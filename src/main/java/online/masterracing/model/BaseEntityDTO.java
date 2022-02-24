package online.masterracing.model;

import java.io.Serializable;

public class BaseEntityDTO implements Serializable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
