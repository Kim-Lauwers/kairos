package be.kairos.domain;

import java.util.Random;

import static be.kairos.domain.Task.builder;
import static be.kairos.domain.TaskIdForTests.randomTaskId;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class TaskTestBuilder {
    private final Task.TaskBuilder builder;

    private TaskTestBuilder() {
        builder = builder();
    }

    public static TaskTestBuilder task() {
        return new TaskTestBuilder();
    }

    public static TaskTestBuilder defaultTask() {
        return task()
                .withId(randomTaskId())
                .withTitle(randomAlphabetic(30))
                .withNotes(randomAlphabetic(80));
    }

    public TaskTestBuilder withId(final TaskId id) {
        builder.id(id);
        return this;
    }

    public TaskTestBuilder withTitle(final String title) {
        builder.title(title);
        return this;
    }

    public TaskTestBuilder withNotes(final String notes) {
        builder.notes(notes);
        return this;
    }

    public Task build() {
        return builder.build();
    }
}