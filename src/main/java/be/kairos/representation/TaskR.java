package be.kairos.representation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static io.swagger.annotations.ApiModelProperty.AccessMode.READ_ONLY;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@ApiModel(description = "Class representing a task a person must finish.")
public class TaskR {
    @ApiModelProperty(notes = "Id of the task, will be provided by the service", example = "13", accessMode = READ_ONLY)
    private long id;

    @ApiModelProperty(notes = "Title of the task.", example = "Support the cancer research", required = true)
    private String title;

    @ApiModelProperty(notes = "Description of the task.", example = "Pay 63 euro's on bankaccount BE45 0000 0000 8989")
    private String notes;
}