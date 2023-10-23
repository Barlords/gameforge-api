package fr.esgi.gameforgeapi.bootstrap.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.esgi.gameforgeapi.bootstrap.config.client.ClientConfiguration;
import fr.esgi.gameforgeapi.bootstrap.config.client.MinioConfiguration;
import fr.esgi.gameforgeapi.bootstrap.config.domain.DomainConfiguration;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@Configuration
@EnableWebMvc
@Import({MinioConfiguration.class, DomainConfiguration.class, ClientConfiguration.class})
public class GameforgeApiConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
    }


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new VavrModule())
                .registerModule(new JavaTimeModule());
    }
}
