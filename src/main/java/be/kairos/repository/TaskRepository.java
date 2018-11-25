package be.kairos.repository;

import be.kairos.domain.Task;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Named
public class TaskRepository implements Reposistory<Task> {

    private final Map<Long, Task> db = new HashMap<>();

    @Override
    public Optional<Task> get(final Long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public void delete(final Long id) {
        db.remove(id);
    }

    @Override
    public Task create(final Task task) {
        db.put(db.size() + 1L, task);
        return task;
    }

    @Override
    public List<Task> list() {
        return new ArrayList<>(db.values());
    }
}