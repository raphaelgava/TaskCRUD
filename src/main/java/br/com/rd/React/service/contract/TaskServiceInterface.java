package br.com.rd.React.service.contract;

import br.com.rd.React.model.dto.TaskDTO;
import br.com.rd.React.model.entity.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface TaskServiceInterface {

    public TaskDTO create(TaskDTO dto);
    public List<TaskDTO> findAll();
    public TaskDTO find(Long id);
    public TaskDTO update(Long id, TaskDTO dto);
    public void deleteById(Long id);
    public void deleteAllById(List<Long> ids);
    public void deleteAll();

}
