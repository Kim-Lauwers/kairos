package be.kairos.domain;

import be.kairos.generator.EntityId;

import java.util.UUID;

public class TaskId extends EntityId {

    // Because Hibernate T_T
    private TaskId() {

    }

    private TaskId(final UUID value) {
        super(value);
    }

    public static TaskId taskId(final UUID value) {
        return new TaskId(value);
    }
}