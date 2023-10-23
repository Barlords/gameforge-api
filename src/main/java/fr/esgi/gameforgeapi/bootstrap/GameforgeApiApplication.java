package fr.esgi.gameforgeapi.bootstrap;

import fr.esgi.gameforgeapi.bootstrap.config.GameforgeApiConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({GameforgeApiConfiguration.class})
@SpringBootApplication(scanBasePackages = {"fr.esgi"}, exclude = {SecurityAutoConfiguration.class})
public class GameforgeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameforgeApiApplication.class, args);
    }

}
