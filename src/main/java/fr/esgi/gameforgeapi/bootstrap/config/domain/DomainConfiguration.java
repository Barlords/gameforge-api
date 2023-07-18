package fr.esgi.gameforgeapi.bootstrap.config.domain;

import fr.esgi.gameforgeapi.domain.functional.services.friend.FriendCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.friend.FriendFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.user.*;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.user.*;
import fr.esgi.gameforgeapi.domain.ports.server.FriendPersistenceSpi;
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
    public FriendFinderApi friendFinderApi(FriendPersistenceSpi spi, UserFinderApi userFinderApi) {
        return new FriendFinderService(spi, userFinderApi);
    }

    @Bean
    public FriendCreatorApi friendCreatorApi(FriendPersistenceSpi spi, UserFinderApi userFinderApi) {
        return new FriendCreatorService(spi, userFinderApi);
    }


    @Bean
    public UserCreatorApi userCreatorApi(UserPersistenceSpi spi, UserModifierService userModifierService) {
        return new UserCreatorService(spi, userModifierService);}

    @Bean
    public UserUpdaterApi userUpdaterApi(UserPersistenceSpi spi, UserModifierService userModifierService) {
        return new UserUpdaterService(spi, userModifierService);}

    @Bean
    public UserDeleterApi userDeleterApi(UserPersistenceSpi spi) {return new UserDeleterService(spi);}

    @Bean
    public UserFinderApi userFinderApi(UserPersistenceSpi spi) {return new UserFinderService(spi);}

    @Bean
    public UserLoggerApi userLoggerApi(UserPersistenceSpi spi) {return new UserLoggerService(spi);}

    @Bean
    public UserModifierService userModifierService() {return new UserModifierService();}
}
