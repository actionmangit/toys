package com.actionman.swagger.config;

import com.actionman.swagger.config.common.ErrorRes;
import com.fasterxml.classmate.TypeResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.RequiredArgsConstructor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableWebMvc
@Configuration
@RequiredArgsConstructor
class SwaggerConfig {

    private final TypeResolver typeResolver;
    
    /**
     * swagger default package
     */
    private static final String DEFAULT_BASE_PACKAGE = "com.actionman.swagger.inbound";

    @Bean
    Docket api() {
        // @formatter:off
        return new Docket(DocumentationType.OAS_30)
            .useDefaultResponseMessages(false)
            // swagger 공통 schema 등록
            .additionalModels(
                typeResolver.resolve(ErrorRes.class)
            )
            .select()
            .apis(RequestHandlerSelectors.basePackage(DEFAULT_BASE_PACKAGE))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
        // @formatter:on
    }

    private ApiInfo apiInfo() {
        // @formatter:off
        return new ApiInfoBuilder()
            .title("Example Swagger")
            .description("example swagger")
            .version("1.0")
            .build();
        // @formatter:on
    }
}
