package be.kairos.generator;

import be.kairos.UnitTest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.Set;

public class EntityIdGeneratorTest extends UnitTest {

    private final EntityIdGenerator idGenerator = new EntityIdGenerator();
    private final Log logger = LogFactory.getLog(this.getClass().getName());

    // Suppress duplicates because we don't want to spend time on a gradle task
    // that runs this test from a common-jpa test dependency
    @SuppressWarnings("Duplicates")
    @Test
    public void generate_IsAbleToReturnAnEntityIdForAllEntityIdSubclasses() {
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(EntityId.class));
        final Set<BeanDefinition> entityIdCandidates = provider.findCandidateComponents("be.vsb.emma");

        final SoftAssertions softly = new SoftAssertions();
        entityIdCandidates.forEach(bean -> {
            final Class<? extends EntityId> specificEntityIdClass = getEntityIdClass(bean);
            logger.debug(String.format("Checking class %s", specificEntityIdClass));
            softly.assertThat(idGenerator.generate(specificEntityIdClass))
                    .hasFieldOrProperty("value")
                    .isInstanceOf(specificEntityIdClass)
                    .describedAs("Classes extending EntityId should have a private default constructor");
        });
        softly.assertAll();
    }

    private Class<? extends EntityId> getEntityIdClass(final BeanDefinition bean) {
        try {
            return (Class<? extends EntityId>) Class.forName(bean.getBeanClassName());
        } catch (final ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}