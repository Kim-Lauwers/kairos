package be.kairos.representation;

import java.util.Random;

import static be.kairos.representation.TaskR.builder;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class TaskRTestBuilder {
    private final TaskR.TaskRBuilder builder;

    private TaskRTestBuilder() {
        builder = builder();
    }

    public static TaskRTestBuilder taskR() {
        return new TaskRTestBuilder();
    }

    public static TaskRTestBuilder defaultTaskR() {
        return taskR()
                .withId(new Random().nextLong())
                .withTitle(randomAlphabetic(30))
                .withNotes(randomAlphabetic(80));
    }

    public TaskRTestBuilder withId(final Long id) {
        builder.id(id);
        return this;
    }

    public TaskRTestBuilder withTitle(final String title) {
        builder.title(title);
        return this;
    }

    public TaskRTestBuilder withNotes(final String notes) {
        builder.notes(notes);
        return this;
    }

    public TaskR build() {
        return builder.build();
    }
}