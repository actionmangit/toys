package com.actionman.swagger.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

/**
 * swegger 페이지상의 서버 셀렉트 설정을 위한 필터 클래스.
 */
@Component
public class SwaggerResolver implements WebMvcOpenApiTransformationFilter {

    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
        OpenAPI swagger = context.getSpecification();

        // 셀렉트 박스에 설정할 서버 세팅
        Server defaultServer = new Server();
        defaultServer.setDescription("default");
        defaultServer.setUrl("http://127.0.0.1:8080");

        Server localhostServer = new Server();
        localhostServer.setDescription("localhost");
        localhostServer.setUrl("http://localhost:8080");

        swagger.setServers(Arrays.asList(defaultServer, localhostServer));

        return swagger;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return delimiter.equals(DocumentationType.OAS_30);
    }
    
}
