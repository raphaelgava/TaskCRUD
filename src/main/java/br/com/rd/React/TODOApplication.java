package br.com.rd.React;

import br.com.rd.React.model.entity.Task;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
		Task t = new Task();

		SpringApplication.run(TODOApplication.class, args);
	}

}
