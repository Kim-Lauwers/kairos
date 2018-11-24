package be.kairos.gateway;

import java.util.List;

public interface Gateway<Representation> {
    Representation get(Long id);

    void delete(Long id);

    Representation create(Representation representation);

    List<Representation> list();
}