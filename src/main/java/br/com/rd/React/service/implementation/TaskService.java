package br.com.rd.React.service.implementation;

import br.com.rd.React.model.dto.TaskDTO;
import br.com.rd.React.model.entity.Task;
import br.com.rd.React.repository.TaskRepository;
import br.com.rd.React.service.contract.TaskServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TaskService implements TaskServiceInterface {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public TaskDTO create(TaskDTO dto) {
        Task task = modelMapper.map(dto, Task.class);
        Date now = new Date( );
//        SimpleDateFormat ft =
//                new SimpleDateFormat ("dd/MM/yyyy hh:mi:ss");
        task.setUpdateDate(now);
        Task response = taskRepository.save(task);

        return modelMapper.map(response, TaskDTO.class);
    }

    @Override
    public List<TaskDTO> findAll() {
        return null;
    }

    @Override
    public TaskDTO find() {
        return null;
    }

    @Override
    public TaskDTO update() {
        return null;
    }

    @Override
    public void deleteById() {

    }

    @Override
    public void deleteAllById() {

    }
}
