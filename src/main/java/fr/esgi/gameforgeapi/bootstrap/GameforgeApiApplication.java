package fr.esgi.gameforgeapi.bootstrap;

import fr.esgi.gameforgeapi.bootstrap.config.GameforgeApiConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Import({GameforgeApiConfiguration.class})
@SpringBootApplication(scanBasePackages = "fr.esgi")
public class GameforgeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameforgeApiApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/gameforge-api").allowedOrigins("http://localhost:4200");
            }
        };
    }

}
