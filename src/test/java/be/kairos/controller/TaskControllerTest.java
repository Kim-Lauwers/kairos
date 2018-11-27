package be.kairos.controller;

import be.kairos.Application;
import be.kairos.gateway.TaskGateway;
import be.kairos.representation.TaskR;
import be.kairos.representation.TaskRAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static be.kairos.controller.TaskController.APPLICATION_V1_JSON_VALUE;
import static be.kairos.domain.TaskId.taskId;
import static be.kairos.representation.TaskRTestBuilder.defaultTaskR;
import static be.kairos.util.TestUtil.convertObjectToJsonBytes;
import static be.kairos.util.TestUtil.convertObjectToJsonString;
import static java.util.Arrays.asList;
import static java.util.UUID.fromString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.LENIENT;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = Application.class)
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private TaskGateway taskGateway;

    @Autowired
    private MockMvc mvc;

    @Test
    public void taskComplete() throws Exception {
        final TaskR taskR = defaultTaskR().build();
        final TaskR createdTaskR = taskGateway.create(taskR);

        new TaskRAssert(createdTaskR)
                .isNotCompleted();

        final MvcResult mvcResult = mvc.perform(patch("/api/tasks/" + createdTaskR.getId()+"/complete")
                .contentType(APPLICATION_V1_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_V1_JSON_VALUE))
                .andReturn();

        final String actual = mvcResult.getResponse().getContentAsString();
        createdTaskR.setCompleted(true);
        assertEquals(
                convertObjectToJsonString(createdTaskR), actual, STRICT);
    }

    @Test
    public void taskGet() throws Exception {
        final TaskR taskR = defaultTaskR().build();
        final TaskR createdTaskR = taskGateway.create(taskR);

        final MvcResult mvcResult = mvc.perform(get("/api/tasks/" + createdTaskR.getId())
                .contentType(APPLICATION_V1_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_V1_JSON_VALUE))
                .andReturn();

        final String actual = mvcResult.getResponse().getContentAsString();
        assertEquals(
                convertObjectToJsonString(createdTaskR), actual, STRICT);
    }

    @Test
    public void taskDelete() throws Exception {
        final TaskR taskR = defaultTaskR().build();
        final TaskR createdTask = taskGateway.create(taskR);

        mvc.perform(delete("/api/tasks/" + createdTask.getId())
                .contentType(APPLICATION_V1_JSON_VALUE))
                .andExpect(status().isOk());

        assertThat(taskGateway.get(taskId(fromString(createdTask.getId())))).isNull();
    }

    @Test
    public void taskAll() throws Exception {
        final TaskR taskR = defaultTaskR().build();
        final TaskR taskR2 = defaultTaskR().build();
        taskGateway.create(taskR);
        taskGateway.create(taskR2);

        final MvcResult mvcResult = mvc.perform(get("/api/tasks")
                .contentType(APPLICATION_V1_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_V1_JSON_VALUE))
                .andReturn();

        final String actual = mvcResult.getResponse().getContentAsString();
        taskR.setId(null);
        taskR2.setId(null);
        assertEquals(
                convertObjectToJsonString(asList(taskR, taskR2)), actual, LENIENT);
    }

    @Test
    public void taskCreate() throws Exception {
        final TaskR taskR = defaultTaskR().build();

        final MvcResult mvcResult = mvc.perform(
                post("/api/tasks")
                        .contentType(APPLICATION_V1_JSON_VALUE)
                        .content(convertObjectToJsonBytes(taskR))
                        .accept(APPLICATION_V1_JSON_VALUE)
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_V1_JSON_VALUE))
                .andReturn();

        final String actual = mvcResult.getResponse().getContentAsString();
        taskR.setId(null);
        assertEquals(
                convertObjectToJsonString(taskR), actual, LENIENT);
    }
}