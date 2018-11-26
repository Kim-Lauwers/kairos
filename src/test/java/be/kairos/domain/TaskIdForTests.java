package be.kairos.domain;

import java.util.UUID;

import static be.kairos.domain.TaskId.taskId;

public class TaskIdForTests {

    private TaskIdForTests() {
        throw new IllegalStateException("utility class");
    }

    public static TaskId randomTaskId() {
        return taskId(UUID.randomUUID());
    }
}