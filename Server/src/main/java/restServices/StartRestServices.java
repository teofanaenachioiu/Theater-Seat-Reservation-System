package restServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@ComponentScans(value = {@ComponentScan("service"),
        @ComponentScan("persistence")})
@SpringBootApplication
public class StartRestServices {
    public static void main(String[] args) {

        SpringApplication.run(StartRestServices.class, args);
    }
}

