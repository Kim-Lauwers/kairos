package be.kairos.generator;

import be.kairos.domain.ValueObject;

import java.io.Serializable;
import java.util.UUID;

public abstract class EntityId extends ValueObject implements Serializable {
    private UUID value;

    // Because Hibernate T_T
    protected EntityId() {
    }

    protected EntityId(final UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    public String asString() {
        return value.toString();
    }
}