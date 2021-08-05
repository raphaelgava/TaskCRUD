package br.com.rd.React.service.implementation;

import br.com.rd.React.model.dto.TaskDTO;
import br.com.rd.React.model.entity.Task;
import br.com.rd.React.repository.TaskRepository;
import br.com.rd.React.service.contract.TaskServiceInterface;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements TaskServiceInterface {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public TaskDTO create(TaskDTO dto) {
        Task task = modelMapper.map(dto, Task.class);

        if (task.getDone() == null){
            task.setDone(false);
        }

        Date now = new Date( );
//        SimpleDateFormat ft =
//                new SimpleDateFormat ("dd/MM/yyyy hh:mi:ss");
        task.setUpdateDate(now);
        Task response = taskRepository.save(task);

        return modelMapper.map(response, TaskDTO.class);
    }

    @Override
    public List<TaskDTO> findAll() {
        List<Task> responseList = taskRepository.findAll();
//        List<TaskDTO> dtoList = responseList.stream().
//                                map(task -> modelMapper.map(task, TaskDTO.class)).
//                                collect(Collectors.toList());
//        return dtoList;

        Type listType = new TypeToken<List<TaskDTO>>(){}.getType();

        return modelMapper.map(responseList,listType);
    }

    @Override
    public TaskDTO find(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        TaskDTO response = null;

        if (task.isPresent()){
            response = modelMapper.map(task.get(), TaskDTO.class);
        }

        return response;

//        TaskDTO response = null;
//        if (taskRepository.existsById(id)) {
//            response = modelMapper.map(taskRepository.getById(id), TaskDTO.class);
//        }
//        return response;
    }

    @Override
    public TaskDTO update(Long id, TaskDTO dto) {
        TaskDTO response = null;

        if (taskRepository.existsById(id)){
            Task updatedTask = taskRepository.getById(id);
            updatedTask.setId(id);
            if (dto.getDescription() != null) {
                updatedTask.setDescription(dto.getDescription());
            }
            if (dto.getDone() != null){
                updatedTask.setDone(dto.getDone());
            }
            updatedTask.setUpdateDate(new Date());


            taskRepository.save(updatedTask);

            response = modelMapper.map(updatedTask, TaskDTO.class);
        }

        return response;
    }

    @Override
    public void deleteById(Long id) {
        if (taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }
//        Optional<Task> task = taskRepository.findById(id);
//        if (task.isPresent()) {
//            taskRepository.delete(task.get());
//        }
    }

    @Override
    public void deleteAllById(List<Long> ids) {
        List<Long> deleteId = new ArrayList<>();
        for (Long id : ids) {
            if (taskRepository.existsById(id))
                deleteId.add(id);
        }
        taskRepository.deleteAllById(deleteId);
//        try {
//            taskRepository.deleteAllById(ids);
//        } catch (EmptyResultDataAccessException e) {
//            System.out.println("ERROR: " + e.toString());
//        }
    }

    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }
}
