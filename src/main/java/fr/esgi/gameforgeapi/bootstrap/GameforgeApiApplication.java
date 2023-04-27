package fr.esgi.gameforgeapi.bootstrap;

import fr.esgi.gameforgeapi.bootstrap.config.GameforgeApiConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({GameforgeApiConfiguration.class})
@SpringBootApplication
public class GameforgeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameforgeApiApplication.class, args);
    }


}
