package be.kairos.representation;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskRAssert extends AbstractAssert<TaskRAssert, TaskR> {
    private final TaskR taskR;

    public TaskRAssert(final TaskR taskR) {
        super(taskR, TaskRAssert.class);
        assertThat(taskR).isNotNull();
        this.taskR = taskR;
    }

    public TaskRAssert hasId(final String id) {
        assertThat(this.taskR.getId()).isNotNull().isEqualTo(id);
        return this;
    }

    public TaskRAssert hasTitle(final String title) {
        assertThat(this.taskR.getTitle()).isNotNull().isEqualTo(title);
        return this;
    }

    public TaskRAssert hasNotes(final String notes) {
        assertThat(this.taskR.getNotes()).isNotNull().isEqualTo(notes);
        return this;
    }

    public TaskRAssert isCompleted() {
        assertThat(this.taskR.getCompleted()).isNotNull().isTrue();
        return this;
    }

    public TaskRAssert isNotCompleted() {
        assertThat(this.taskR.getCompleted()).isNotNull().isFalse();
        return this;
    }

    public TaskRAssert hasNoNotes() {
        assertThat(this.taskR.getNotes()).isNull();
        return this;
    }
}