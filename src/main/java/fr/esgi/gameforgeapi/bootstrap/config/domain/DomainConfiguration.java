package fr.esgi.gameforgeapi.bootstrap.config.domain;

import fr.esgi.gameforgeapi.domain.functional.services.UserCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.UserFinderService;
import fr.esgi.gameforgeapi.domain.ports.client.UserCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.UserFinderApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"fr.esgi.gameforgeapi.server.entities"})
@EnableJpaRepositories(basePackages = {"fr.esgi.gameforgeapi.server.repositories"})
@ComponentScan(basePackages = {"fr.esgi.gameforgeapi.server.adapters"})
public class DomainConfiguration {

    @Bean
    public UserCreatorApi userCreatorApi(UserPersistenceSpi spi) {return new UserCreatorService(spi);}

    @Bean
    public UserFinderApi userFinderApi(UserPersistenceSpi spi) {return new UserFinderService(spi);}

}
