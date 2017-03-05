package com.brickly.kit.service.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by alexnikolayevsky on 5/24/16.
 */
@EnableSwagger2
@Configuration
public class CoreConfig extends WebMvcConfigurerAdapter{

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                .exposedHeaders("Link", "X-Total-Count")
                .maxAge(3600);
    }

    @Bean
    public Docket kitApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("kit")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/kit.*"))
                .build();
    }

//
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Brickly Kit Service API")
                .description("Brickly KitServiceApi")
                .termsOfServiceUrl("")
                .contact("Alex Nikolayevsky")
                .license("Apache License Version 2.0")
                .licenseUrl("")
                .version("1.0")
                .build();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).
                favorParameter(true).
                parameterName("mediaType").
                useJaf(false).
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("xml", MediaType.APPLICATION_XML).
                mediaType("json", MediaType.APPLICATION_JSON);
    }


}
