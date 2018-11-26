package be.kairos.repository;

import be.kairos.domain.TaskId;

import java.util.List;
import java.util.Optional;

public interface Reposistory<T> {
    Optional<T> get(final TaskId id);
    void delete(final TaskId id);
    T create(final T aggregate);
    List<T> list();
}
