package br.com.rd.React;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {
    //.apis(RequestHandlerSelectors.any()) = http://localhost:8080/api/swagger-ui/index.html
    @Bean
    public Docket apiConfigDocs(){
        return new Docket(DocumentationType.SWAGGER_2) //tipo de documentação
                            .select()
                            .apis(RequestHandlerSelectors.basePackage("br.com.rd.React.controller")) //método de pesquisa (o pacote onde estão os api)
//                            .paths(PathSelectors.ant("/api/*")) //mapeando tudo que vem depois de /api
//                            .apis(RequestHandlerSelectors.any())
                            .paths(PathSelectors.any())
                            .build();
    }

    private ApiInfo infoDocs(){
        return new ApiInfo("Title - Rest TODO API", "First API", "1.0", "Terms", new Contact("Raphael", "www.teste.com","email"), "RD", "url", new ArrayList<VendorExtension>());
    }
}
