package com.aws.AWSFIRSTTODOAPP.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


    @Configuration
    public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("AWS First To-Do App API")
                            .version("1.0")
                            .description("API documentation for the AWS To-Do App"));
        }
    }


//Swagger UI → http://localhost:8080/swagger-ui.html
//API Docs (JSON) → http://localhost:8080/v3/api-docs