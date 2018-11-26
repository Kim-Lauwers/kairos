package be.kairos.generator;

import java.util.UUID;

class DummyEntityId extends EntityId {
    public DummyEntityId(final String uuidAsString) {
        super(UUID.fromString(uuidAsString));
    }
}