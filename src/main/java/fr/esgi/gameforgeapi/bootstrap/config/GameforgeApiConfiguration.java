package fr.esgi.gameforgeapi.bootstrap.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esgi.gameforgeapi.bootstrap.config.domain.DomainConfiguration;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DomainConfiguration.class)
public class GameforgeApiConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new VavrModule());
    }

}
