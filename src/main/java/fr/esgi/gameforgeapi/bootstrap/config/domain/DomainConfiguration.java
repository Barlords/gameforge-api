package fr.esgi.gameforgeapi.bootstrap.config.domain;

import fr.esgi.gameforgeapi.domain.functional.services.user.*;
import fr.esgi.gameforgeapi.domain.ports.client.*;
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
    public UserCreatorApi userCreatorApi(UserPersistenceSpi spi, UserModifierService userModifierService) {return new UserCreatorService(spi, userModifierService);}

    @Bean
    public UserUpdaterApi userUpdaterApi(UserPersistenceSpi spi, UserModifierService userModifierService) {return new UserUpdaterService(spi, userModifierService);}

    @Bean
    public UserDeleterApi userDeleterApi(UserPersistenceSpi spi) {return new UserDeleterService(spi);}

    @Bean
    public UserFinderApi userFinderApi(UserPersistenceSpi spi) {return new UserFinderService(spi);}

    @Bean
    public UserLoggerApi userLoggerApi(UserPersistenceSpi spi) {return new UserLoggerService(spi);}

    @Bean
    public UserModifierService userModifierService() {return new UserModifierService();}


}
