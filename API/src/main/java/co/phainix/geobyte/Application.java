package co.phainix.geobyte;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
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
