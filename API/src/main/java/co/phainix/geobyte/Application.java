package co.phainix.geobyte;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan
@RestController
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class Application {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/api/v1")
    String api() {
        return "Hello API!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
