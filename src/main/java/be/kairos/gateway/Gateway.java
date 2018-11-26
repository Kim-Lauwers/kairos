package be.kairos.gateway;

import be.kairos.domain.TaskId;

import java.util.List;

public interface Gateway<Representation> {
    Representation get(TaskId id);

    void delete(TaskId id);

    Representation create(Representation representation);

    List<Representation> list();
}