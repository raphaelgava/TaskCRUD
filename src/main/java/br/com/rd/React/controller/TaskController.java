package br.com.rd.React.controller;

import br.com.rd.React.model.dto.TaskDTO;
import br.com.rd.React.model.entity.Task;
import br.com.rd.React.service.implementation.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(value="API REST - Model Task") //swagger - ja entende parte do que o spring faz e já documenta
@RestController
@RequestMapping(value="/tasks"
//        , method = RequestMethod.GET,
//        , produces = {MediaType.APPLICATION_JSON_VALUE}
)  //sempre no plural caso houver uma sub rota
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

    @ApiOperation(value="Create task data in data base")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    //@RequestBody - o objeto esta vindo da request
//    public TaskDTO create(@RequestBody TaskDTO task){
//        return this.taskService.create(task);
//    }
    //@Valid faz a validação do objeto recebido!
    //? = Wildcard é o nome dado ao identificador ? em códigos genéricos. Ele representa um tipo desconhecido, e pode ser utilizado em algumas situações como um tipo de parâmetro ou uma variável local. O Wildcard sozinho é um sintaxe sugar para <? extends Object>
    public ResponseEntity<?> create(@Valid @RequestBody TaskDTO task, Errors errors){
        if (!errors.hasErrors()){
            return this.taskService.create(task, errors);
        }
//        return ResponseEntity
//                .badRequest()
//                .body(errors
//                        .getAllErrors()
//                        .stream()
//                        .map(msg -> msg.getDefaultMessage())
//                        .collect(Collectors.joining(",")));
        return new ResponseEntity<>(errors
                                        .getAllErrors()
                                        .stream()
                                        .map(e -> (e.getCode() + " - " + e.getDefaultMessage()))
                                        .collect(Collectors.toList()),
                                        HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value="Find all task data in data base")
    @GetMapping(produces="application/json")
//    public List<TaskDTO> findAll(){
//        return this.taskService.findAll();
//    }
    public ResponseEntity<?> findAll(){
//        return ResponseEntity.ok(this.taskService.findAll());
        return new ResponseEntity<List>(this.taskService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value="Find task data by id in data base")
    @GetMapping("/{id}")
//    public TaskDTO find(@PathVariable("id") Long id){
//        return this.taskService.find(id);
//    }
    public ResponseEntity<?> find(@PathVariable("id") Long id){
//        return ResponseEntity.ok(this.taskService.find(id));
        return new ResponseEntity<TaskDTO>(this.taskService.find(id), HttpStatus.OK);
    }

    @ApiOperation(value="Update task data in data base")
    @PutMapping("/{id}")
//    public TaskDTO update(@PathVariable("id") Long id, @RequestBody TaskDTO task){
//        return this.taskService.update(id, task);
//    }
    public ResponseEntity<?>  update(@PathVariable("id") Long id, @RequestBody TaskDTO task, Errors errors){
        if (!errors.hasErrors()){
            //return ResponseEntity.ok(this.taskService.create(task)); //retorna status 200
            //return new ResponseEntity<TaskDTO>(this.taskService.update(id, task), HttpStatus.OK);
            return this.taskService.update(id, task, errors);
        }
        return ResponseEntity
                .badRequest()
                .body(errors
                        .getAllErrors()
                        .stream()
                        .map(msg -> msg.getDefaultMessage())
                        .collect(Collectors.joining(",")));
    }

    @ApiOperation(value="Remove task data by id in data base")
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
    @ApiOperation(value="Remove all task data in data base")
    @DeleteMapping("/deleteAllIds")
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

    @DeleteMapping("/deleteAll")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll(){
        taskService.deleteAll();
    }
}
