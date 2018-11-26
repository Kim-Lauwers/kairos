package be.kairos.generator;

import javax.inject.Named;
import java.lang.reflect.Constructor;
import java.util.UUID;

@Named
public class EntityIdGenerator {

    public <T extends EntityId> T generate(final Class<T> clazz) {
        try {
            return instantiate(clazz);
        } catch (final Exception e) {
            throw new RuntimeException("Id could not be generated for class " + clazz.getSimpleName());
        }
    }

    private <T extends EntityId> T instantiate(final Class<T> clazz) throws Exception {
        final Constructor<T> uuidCons = clazz.getDeclaredConstructor(UUID.class);
        uuidCons.setAccessible(true);
        final T instance = uuidCons.newInstance(UUID.randomUUID());
        uuidCons.setAccessible(false);
        return instance;
    }
}