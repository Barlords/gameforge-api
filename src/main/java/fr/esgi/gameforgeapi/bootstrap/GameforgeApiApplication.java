package fr.esgi.gameforgeapi.bootstrap;

import fr.esgi.gameforgeapi.bootstrap.config.GameforgeApiConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import({GameforgeApiConfiguration.class})
@SpringBootApplication(scanBasePackages = "fr.esgi")
@EnableTransactionManagement
public class GameforgeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameforgeApiApplication.class, args);
    }


}
