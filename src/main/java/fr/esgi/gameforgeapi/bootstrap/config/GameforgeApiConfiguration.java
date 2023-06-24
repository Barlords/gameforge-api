package fr.esgi.gameforgeapi.bootstrap.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esgi.gameforgeapi.bootstrap.config.domain.DomainConfiguration;
import fr.esgi.gameforgeapi.domain.functional.services.user.UserLoggerService;
import fr.esgi.gameforgeapi.domain.ports.client.UserLoggerApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import fr.esgi.gameforgeapi.server.adapters.UserDatabaseAdapter;
import fr.esgi.gameforgeapi.server.repositories.LobbyRepository;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.beans.factory.annotation.Autowired;
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
