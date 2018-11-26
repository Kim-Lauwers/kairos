package be.kairos.gateway;

import be.kairos.domain.Task;
import be.kairos.domain.TaskId;
import be.kairos.generator.EntityIdGenerator;
import be.kairos.mapper.Mapper;
import be.kairos.repository.Reposistory;
import be.kairos.representation.TaskR;

import javax.inject.Named;
import java.util.List;

import static java.util.stream.Collectors.toList;

@be.kairos.common.Gateway
@Named
public class TaskGateway implements Gateway<TaskR> {

    private final Mapper<Task, TaskR> taskMapper;
    private final Reposistory<Task> taskReposistory;
    private final EntityIdGenerator entityIdGenerator;

    public TaskGateway(final Mapper<Task, TaskR> taskMapper, final Reposistory<Task> taskReposistory, final EntityIdGenerator entityIdGenerator) {
        this.taskMapper = taskMapper;
        this.taskReposistory = taskReposistory;
        this.entityIdGenerator = entityIdGenerator;
    }

    @Override
    public TaskR get(final TaskId id) {
        return taskReposistory.get(id).map(taskMapper::mapToRepresenation).orElse(null);
    }

    @Override
    public void delete(final TaskId id) {
        taskReposistory.delete(id);
    }

    @Override
    public TaskR create(final TaskR taskR) {
        final Task task = taskMapper.mapToDomain(taskR);
        task.setId(entityIdGenerator.generate(TaskId.class));
        final Task createdTask = taskReposistory.create(task);
        return taskMapper.mapToRepresenation(createdTask);
    }

    @Override
    public List<TaskR> list() {
        return taskReposistory.list().stream().map(task -> taskMapper.mapToRepresenation(task)).collect(toList());
    }
}