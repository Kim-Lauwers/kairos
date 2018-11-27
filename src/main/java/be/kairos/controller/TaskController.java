package be.kairos.controller;

import be.kairos.domain.TaskId;
import be.kairos.gateway.Gateway;
import be.kairos.representation.TaskR;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static be.kairos.domain.TaskId.taskId;
import static java.util.UUID.fromString;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;

@RestController
@RequestMapping("api")
@Api(description = "Set of endpoints for communication about tasks.")
public class TaskController {
    static final String APPLICATION_V1_JSON_VALUE = "application/be.kairos.api-v1+json";

    private Gateway<TaskId, TaskR> taskGateway;

    public TaskController(final Gateway<TaskId, TaskR> taskGateway) {
        this.taskGateway = taskGateway;
    }

    @RequestMapping(value = "tasks/{taskId}", method = GET, consumes = APPLICATION_V1_JSON_VALUE, produces = APPLICATION_V1_JSON_VALUE)
    @ApiOperation(value = "Get the task with the specific id.", tags = {"task"}, consumes = APPLICATION_V1_JSON_VALUE, produces = APPLICATION_V1_JSON_VALUE)
    public TaskR get(@PathVariable("taskId") final String taskId) {
        return taskGateway.get(taskId(fromString(taskId)));
    }

    @RequestMapping(value = "tasks/{taskId}", method = DELETE, consumes = APPLICATION_V1_JSON_VALUE)
    @ApiOperation(value = "Delete the task.", tags = {"task"}, consumes = APPLICATION_V1_JSON_VALUE)
    public void delete(@PathVariable("taskId") final String taskId) {
        taskGateway.delete(taskId(fromString(taskId)));
    }

    @RequestMapping(value = "tasks/{taskId}/complete", method = PATCH, consumes = APPLICATION_V1_JSON_VALUE, produces = APPLICATION_V1_JSON_VALUE)
    @ApiOperation(value = "Complete the task.", tags = {"task"}, consumes = APPLICATION_V1_JSON_VALUE, produces = APPLICATION_V1_JSON_VALUE)
    public TaskR complete(@PathVariable("taskId") final String taskId) {
        return taskGateway.complete(taskId(fromString(taskId)));
    }

    @RequestMapping(value = "tasks", method = GET, consumes = APPLICATION_V1_JSON_VALUE, produces = APPLICATION_V1_JSON_VALUE)
    @ApiOperation(value = "List all the tasks.", tags = {"task"}, consumes = APPLICATION_V1_JSON_VALUE, produces = APPLICATION_V1_JSON_VALUE)
    public List<TaskR> all() {
        return taskGateway.list();
    }

    @RequestMapping(value = "tasks/completed", method = GET, consumes = APPLICATION_V1_JSON_VALUE, produces = APPLICATION_V1_JSON_VALUE)
    @ApiOperation(value = "List all the completed tasks.", tags = {"task"}, consumes = APPLICATION_V1_JSON_VALUE, produces = APPLICATION_V1_JSON_VALUE)
    public List<TaskR> allCompletedTasks() {
        return taskGateway.listCompletedTasks();
    }

    @RequestMapping(value = "tasks/todo", method = GET, consumes = APPLICATION_V1_JSON_VALUE, produces = APPLICATION_V1_JSON_VALUE)
    @ApiOperation(value = "List all the todo tasks.", tags = {"task"}, consumes = APPLICATION_V1_JSON_VALUE, produces = APPLICATION_V1_JSON_VALUE)
    public List<TaskR> allTodoTasks() {
        return taskGateway.listTodoTasks();
    }

    @PostMapping(value = "tasks", consumes = APPLICATION_V1_JSON_VALUE, produces = APPLICATION_V1_JSON_VALUE)
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Create a new task.", tags = {"task"}, consumes = APPLICATION_V1_JSON_VALUE, produces = APPLICATION_V1_JSON_VALUE)
    public TaskR create(
            @ApiParam("Task information for a new task to be created.")
            @RequestBody final TaskR taskR) {
        return taskGateway.create(taskR);
    }
}