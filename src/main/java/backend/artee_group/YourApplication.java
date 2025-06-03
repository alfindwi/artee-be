package backend.artee_group;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YourApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(YourApplication.class);
        Map<String, Object> props = new HashMap<>();
        String port = System.getenv("PORT");
        if (port != null) {
            props.put("server.port", port);
        }
        app.setDefaultProperties(props);
        app.run(args);
    }
}

