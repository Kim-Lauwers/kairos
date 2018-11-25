package be.kairos.mapper;

import be.kairos.domain.Task;
import be.kairos.domain.TaskAssert;
import be.kairos.representation.TaskR;
import org.junit.Test;

import static be.kairos.representation.TaskRTestBuilder.defaultTaskR;

public class TaskMapperTest {
    final Mapper<Task, TaskR> mapper = new TaskMapper();

    @Test
    public void mapToDomain() {
        final TaskR taskR = defaultTaskR().build();

        final Task task = mapper.mapToDomain(taskR);

        new TaskAssert(task)
                .hasId(taskR.getId())
                .hasTitle(taskR.getTitle())
                .hasNotes(taskR.getNotes());
    }
}