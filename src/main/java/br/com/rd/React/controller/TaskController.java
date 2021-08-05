package br.com.rd.React.controller;

import br.com.rd.React.model.dto.TaskDTO;
import br.com.rd.React.model.entity.Task;
import br.com.rd.React.service.implementation.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")  //sempre no plural caso houver uma sub rota
public class TaskController {

    @Autowired
    TaskService taskService;

    /*
    @ResponseBody Generally used in combination with @Controller to return a JSON string:
    @Controller
    @ResponseBody

    And @ResponseStatus is generally used in combination with RestController:
    @RestController
    @ResponseStatus
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //@RequestBody - o objeto esta vindo da request
    public TaskDTO create(@RequestBody TaskDTO task){
        return this.taskService.create(task);
    }

    @GetMapping()
    public List<TaskDTO> findAll(){
        return this.taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskDTO find(@PathVariable("id") Long id){
        return this.taskService.find(id);
    }

    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable("id") Long id, @RequestBody TaskDTO task){
        return this.taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
        taskService.deleteById(id);
    }

    /*
    http://localhost:12345?values=firstValue,secondValue,thirdValue
    OR
    http://localhost:12345?values=firstValue&values=secondValue&values=thirdValue
    */
    @DeleteMapping("/deleteAll")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAllById(@RequestParam("ids") List<Long> ids){
//        List<Long> idss = new ArrayList<>();
//        for (String i: ids
//             ) {
//            Long j = Long.parseLong(i);
//            idss.add(j);
//        }
        taskService.deleteAllById(ids);
    }

//    @DeleteMapping("/deleteAll")
//    @ResponseStatus(code = HttpStatus.NO_CONTENT)
//    public void deleteAll(){
//        taskService.deleteAll();
//    }
}
