package br.com.rd.React.validator;

import br.com.rd.React.model.dto.TaskDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TaskValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        TaskDTO task = (TaskDTO) o;

        if ((task.getPercentage()  != null) && (task.getDone() != null)) {
//            if (task.getDone() == null) {
//                task.setDone(false);
//            }

            if ((task.getPercentage() == 100) && (task.getDone() != true)) {
                errors.reject("Invalid", "Valor da porcentagem incompatível com o status");
            } else if ((task.getPercentage() != 100) && (task.getDone() != false)) {
                errors.reject("Invalid", "Valor da porcentagem incompatível com o status");
            }
        }
    }

    public void validate(boolean exists, TaskDTO task, Errors errors){
        if (!exists){
            errors.reject("Missing", "Código inexistente");
        }else{
            validate(task, errors);
        }
    }
}
