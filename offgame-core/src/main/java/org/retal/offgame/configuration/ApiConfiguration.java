package org.retal.offgame.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@Configuration
public class ApiConfiguration implements WebMvcConfigurer {

    @Value("${spring.servlet.rest.controller.prefix:}")
    private Optional<String> restPrefix;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        restPrefix.ifPresent(prefix -> configurer
                .addPathPrefix(prefix, HandlerTypePredicate.forAnnotation(RestController.class)));
    }
}
