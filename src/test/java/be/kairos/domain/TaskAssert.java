package be.kairos.domain;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskAssert extends AbstractAssert<TaskAssert, Task> {
    private final Task task;

    public TaskAssert(final Task task) {
        super(task, TaskAssert.class);
        assertThat(task).isNotNull();
        this.task = task;
    }

    public TaskAssert hasId(final Long id) {
        assertThat(this.task.getId()).isNotNull().isEqualTo(id);
        return this;
    }

    public TaskAssert hasTitle(final String title) {
        assertThat(this.task.getTitle()).isNotNull().isEqualTo(title);
        return this;
    }

    public TaskAssert hasNotes(final String notes) {
        assertThat(this.task.getNotes()).isNotNull().isEqualTo(notes);
        return this;
    }
}