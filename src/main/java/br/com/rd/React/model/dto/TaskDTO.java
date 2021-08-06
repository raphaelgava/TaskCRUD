package br.com.rd.React.model.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TaskDTO {
    private Long id;
    //@NotNull @NotEmpty(message = "Please provide the description")
    @NotNull @NotEmpty(message = "{dto.description.empty}")
    private String description;
    private Boolean done;
}
