package be.kairos.mapper;

import be.kairos.domain.Task;
import be.kairos.representation.TaskR;

import javax.inject.Named;
import java.util.UUID;

import static be.kairos.domain.TaskId.taskId;

@Named
public class TaskMapper implements Mapper<Task, TaskR> {
    @Override
    public Task mapToDomain(final TaskR taskR) {
        return Task.builder()
                .id(taskId(taskR.getId() == null ? null : UUID.fromString(taskR.getId())))
                .title(taskR.getTitle())
                .notes(taskR.getNotes())
                .build();
    }

    @Override
    public TaskR mapToRepresenation(final Task task) {
        return TaskR.builder()
                .id(task.getId() == null ? null : task.getId().asString())
                .title(task.getTitle())
                .notes(task.getNotes())
                .build();
    }
}
