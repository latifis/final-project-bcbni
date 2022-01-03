package com.latif.userservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static com.latif.userservice.utils.ProjectConstants.PROJECT_BASE_PACKAGE;

@Configuration
@EnableSwagger2
@PropertySource(value = "classpath:swagger-information.properties")
public class SwaggerConfiguration {
    @Value("${swagger.app-info.name}")
    private String appName;

    @Value("${swagger.app-info.description}")
    private String appDescription;

    @Value("${swagger.app-info.version}")
    private String appVersion;

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(getHeaderParams())
                .select()
                .apis(RequestHandlerSelectors.basePackage(PROJECT_BASE_PACKAGE))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(getApiInformation());
    }

    private ApiInfo getApiInformation() {
        return new ApiInfoBuilder()
                .title(appName)
                .version(appVersion)
                .description(appDescription)
                .build();
    }

    private List<Parameter> getHeaderParams(){

        final Parameter parameter = new ParameterBuilder()
                .required(false)
                .name("Authorization")
                .parameterType("header")
                .modelRef(new ModelRef("string"))
                .build();

        return Collections.singletonList(parameter);
    }
}
