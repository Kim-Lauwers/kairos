package be.kairos.repository;

import java.util.List;
import java.util.Optional;

public interface Reposistory<T> {
    Optional<T> get(final Long id);
    void delete(final Long id);
    T create(final T aggregate);
    List<T> list();
}
