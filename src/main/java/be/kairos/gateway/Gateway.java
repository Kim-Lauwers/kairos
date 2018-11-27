package be.kairos.gateway;

import be.kairos.domain.TaskId;

import java.util.List;

public interface Gateway<Id, Representation> {
    Representation get(final Id id);

    void delete(TaskId id);

    Representation create(final Representation representation);

    List<Representation> list();

    Representation complete(final Id taskId);
}