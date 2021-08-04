package br.com.rd.React.service.contract;

import br.com.rd.React.model.dto.TaskDTO;
import br.com.rd.React.model.entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface TaskServiceInterface {

    public TaskDTO create(TaskDTO dto);
    public List<TaskDTO> findAll();
    public TaskDTO find();
    public TaskDTO update();
    public void deleteById();
    public void deleteAllById();

}
