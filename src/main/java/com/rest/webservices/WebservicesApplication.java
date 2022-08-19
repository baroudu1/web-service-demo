package com.rest.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.spring.web.readers.operation.HandlerMethodResolver;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@SpringBootApplication
public class WebservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebservicesApplication.class, args);
    }
    @Bean
     public AcceptHeaderLocaleResolver localResolver() {
        AcceptHeaderLocaleResolver slr = new AcceptHeaderLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }
    @Bean
    public WebMvcRequestHandlerProvider webMvcRequestHandlerProvider(
            Optional<ServletContext> context,
            HandlerMethodResolver methodResolver,
            List<RequestMappingInfoHandlerMapping> handlerMappings) {
        handlerMappings = handlerMappings.stream()
                .filter(rh -> rh.getClass().getName().contains("RequestMapping")).toList();
        return new WebMvcRequestHandlerProvider(context, methodResolver, handlerMappings);
    }


}
