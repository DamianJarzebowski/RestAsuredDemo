package dj.restasureddemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestAsuredDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestAsuredDemoApplication.class, args);

        var mapper = new ObjectMapper();
    }


}
