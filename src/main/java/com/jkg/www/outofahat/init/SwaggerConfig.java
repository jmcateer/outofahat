package com.jkg.www.outofahat.init;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {

    @Value("${swagger.email")
    private String email;
    @Value("${swagger.description}")
    private String description;
    @Value("${swagger.version}")
    private String version;
    @Value("${swagger.title}")
    private String title;

    @Bean
    public Docket api() {
        final TypeResolver typeResolver = new TypeResolver();
        final AlternateTypeRule alternateTypeRule = new AlternateTypeRule(
                typeResolver.resolve(ResponseEntity.class), typeResolver.resolve(Void.class));
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .alternateTypeRules(alternateTypeRule)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                .globalOperationParameters(globalOperationParameters());
    }

    private ApiInfo apiInfo() {
        final Contact contact = new Contact(null, null, email);
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .contact(contact)
                .build();
    }

    List<Parameter> globalOperationParameters() {
        final List<Parameter> parameters = new ArrayList<>();
        final Parameter authorization = new ParameterBuilder()
                .name("Authorization")
                .description("Authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build();
        parameters.add(authorization);
        return parameters;
    }
}
