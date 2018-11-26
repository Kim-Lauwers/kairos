package be.kairos.generator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EntityIdTest {
    @Test
    public void asString_ReturnsToStringFromInternalUuid() {
        final String uuidAsString = "82147141-fbd8-4e70-b562-4eb3873c0918";

        final EntityId entityId = new DummyEntityId(uuidAsString);

        assertThat(entityId.asString()).isEqualTo(uuidAsString);
    }
}