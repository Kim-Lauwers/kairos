package be.kairos.mapper;

import be.kairos.domain.Task;
import be.kairos.domain.TaskAssert;
import be.kairos.representation.TaskR;
import be.kairos.representation.TaskRAssert;
import org.junit.Test;

import static be.kairos.domain.TaskTestBuilder.defaultTask;
import static be.kairos.representation.TaskRTestBuilder.defaultTaskR;

public class TaskMapperTest {
    final Mapper<Task, TaskR> mapper = new TaskMapper();

    @Test
    public void mapToRepresentation() {
        final Task task = defaultTask().build();

        final TaskR taskR = mapper.mapToRepresenation(task);

        new TaskRAssert(taskR)
                .hasId(taskR.getId())
                .hasTitle(taskR.getTitle())
                .hasNotes(taskR.getNotes());
    }

    @Test
    public void mapToRepresentationNullId() {
        final Task task = defaultTask().withId(null).build();

        final TaskR taskR = mapper.mapToRepresenation(task);

        new TaskRAssert(taskR)
                .hasTitle(taskR.getTitle())
                .hasNotes(taskR.getNotes());
    }

    @Test
    public void mapToRepresentationNullNotes() {
        final Task task = defaultTask()
                .withNotes(null)
                .build();

        final TaskR taskR = mapper.mapToRepresenation(task);

        new TaskRAssert(taskR)
                .hasId(taskR.getId())
                .hasTitle(taskR.getTitle())
                .hasNoNotes();
    }

    @Test
    public void mapToDomain() {
        final TaskR taskR = defaultTaskR().build();

        final Task task = mapper.mapToDomain(taskR);

        new TaskAssert(task)
                .hasId(task.getId())
                .hasTitle(task.getTitle())
                .hasNotes(task.getNotes());
    }

    @Test
    public void mapToDomainNullId() {
        final TaskR taskR = defaultTaskR().withId(null).build();

        final Task task = mapper.mapToDomain(taskR);

        new TaskAssert(task)
                .hasTitle(task.getTitle())
                .hasNotes(task.getNotes());
    }

    @Test
    public void mapToDomainNullNotes() {
        final TaskR taskR = defaultTaskR().withNotes(null).build();

        final Task task = mapper.mapToDomain(taskR);

        new TaskAssert(task)
                .hasId(task.getId())
                .hasTitle(task.getTitle())
                .hasNoNotes();
    }
}