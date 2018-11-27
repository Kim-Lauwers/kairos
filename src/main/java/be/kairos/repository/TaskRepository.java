package be.kairos.repository;

import be.kairos.domain.Task;
import be.kairos.domain.TaskId;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Named
public class TaskRepository implements Reposistory<Task> {

    private final Map<TaskId, Task> db = new HashMap<>();

    @Override
    public Optional<Task> get(final TaskId id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public void delete(final TaskId id) {
        db.remove(id);
    }

    @Override
    public Task saveOrUpdate(final Task task) {
        db.put(task.getId(), task);
        return task;
    }

    @Override
    public List<Task> list() {
        return new ArrayList<>(db.values());
    }

    @Override
    public List<Task> listCompleted() {
        return db.values().stream().filter(task -> task.getCompleted()).collect(toList());
    }
}