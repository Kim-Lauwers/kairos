package be.kairos.mapper;

import be.kairos.domain.Task;
import be.kairos.representation.TaskR;

import javax.inject.Named;

@Named
public class TaskMapper implements Mapper<Task, TaskR> {
    @Override
    public Task mapToDomain(final TaskR taskR) {
        return Task.builder()
                .id(taskR.getId())
                .title(taskR.getTitle())
                .notes(taskR.getNotes())
                .build();
    }

    @Override
    public TaskR mapToRepresenation(final Task task) {
        return TaskR.builder()
                .id(task.getId())
                .title(task.getTitle())
                .notes(task.getNotes())
                .build();
    }
}
