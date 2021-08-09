package br.com.rd.React;

import br.com.rd.React.model.entity.Task;
import br.com.rd.React.repository.TaskRepository;
import br.com.rd.React.service.implementation.TaskService;
import br.com.rd.React.validator.TaskValidator;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/*
@SpringBootApplication acts also as a @Configuration annotation

 */
@SpringBootApplication
//@ComponentScan(basePackageClasses = HelloWorld.class) // se quiser ler um bean de uma outra classe!!!
public class TODOApplication {
	/*
         @Bean tells that a method produces a bean to be managed by the Spring container. It is a method-level annotation. During Java configuration (@Configuration), the method is executed and its return value is registered as a bean within a BeanFactory.
         */
	@Bean
	public ModelMapper modelMapper() {
		//return new ModelMapper();
		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration()
//				.setFieldMatchingEnabled(true) //permite ver
//				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE); //Can compare private fields
		return modelMapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(TODOApplication.class, args);
	}

}
