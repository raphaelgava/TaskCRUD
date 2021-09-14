package br.com.rd.React.model.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class TaskDTO{// implements Serializable {
    //private static final long serialVersionUID = 1L;
    private Long id;
    //@NotNull @NotEmpty(message = "Please provide the description")
    @NotNull @NotEmpty(message = "{dto.description.empty}")
    @Size(min=0, max=200, message = "{dto.description.size}")
    private String description;
    private Boolean done;
    @Min(value=0, message = "{dto.description.size}")
    @Max(value=100, message = "{dto.percentage.size}")
    private Integer percentage;
}
