package fr.esgi.gameforgeapi.bootstrap.config.domain;

import fr.esgi.gameforgeapi.domain.functional.services.Lobby.LobbyCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.Lobby.LobbyFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.functional.services.friend.FriendCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.friend.FriendFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.game.GameCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.game.GameFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.message.MessageCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.message.MessageFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.rating.RatingCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.rating.RatingFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.session.SessionCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.session.SessionFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.session.SessionUpdaterService;
import fr.esgi.gameforgeapi.domain.functional.services.user.*;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.rating.RatingCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.rating.RatingFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.session.SessionUpdaterApi;
import fr.esgi.gameforgeapi.domain.ports.client.user.*;
import fr.esgi.gameforgeapi.domain.ports.server.*;
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
    public RatingCreatorApi ratingCreatorApi(RatingPersistenceSpi spi, TokenControllerService tokenControllerService) {
        return new RatingCreatorService(spi, tokenControllerService);
    }

    @Bean
    public RatingFinderApi ratingFinderApi(RatingPersistenceSpi spi, TokenControllerService tokenControllerService) {
        return new RatingFinderService(spi, tokenControllerService);
    }


    @Bean
    public MessageCreatorApi messageCreatorApi(MessagePersistenceSpi spi, TokenControllerService tokenControllerService) {
        return new MessageCreatorService(spi, tokenControllerService);
    }

    @Bean
    public MessageFinderApi messageFinderApi(MessagePersistenceSpi spi, TokenControllerService tokenControllerService) {
        return new MessageFinderService(spi, tokenControllerService);
    }


    @Bean
    public TokenControllerService tokenControllerService(UserFinderApi userFinderApi) {return new TokenControllerService(userFinderApi);}


    @Bean
    public FriendFinderApi friendFinderApi(FriendPersistenceSpi spi, TokenControllerService tokenControllerService, UserFinderApi userFinderApi) {
        return new FriendFinderService(spi, tokenControllerService, userFinderApi);
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
    public UserLoggerApi userLoggerApi(UserPersistenceSpi spi, TokenControllerService tokenControllerService) {return new UserLoggerService(spi, tokenControllerService);}

    @Bean
    public UserModifierService userModifierService() {return new UserModifierService();}

    @Bean
    public LobbyCreatorApi lobbyCreatorApi(LobbyPersistenceSpi spi) {
        return new LobbyCreatorService(spi);}

    @Bean
    public LobbyFinderApi lobbyFinderApi(LobbyPersistenceSpi spi) {return new LobbyFinderService(spi);}

    @Bean
    public GameCreatorApi gameCreatorApi(GamePersistenceSpi spi) {
        return new GameCreatorService(spi);}

    @Bean
    public GameFinderApi gameFinderApi(GamePersistenceSpi spi) {return new GameFinderService(spi);}


    @Bean
    public SessionFinderApi sessionFinderApi(SessionPersistenceSpi spi) {return new SessionFinderService(spi);}

    @Bean
    public SessionFinderService sessionFinderService(SessionPersistenceSpi spi) {
        return new SessionFinderService(spi);
    }
    @Bean
    public SessionCreatorApi sessionCreatorApi(SessionPersistenceSpi spi,SessionFinderService sessionFinderService) {
        return new SessionCreatorService(spi,sessionFinderService);}

    @Bean
    public SessionUpdaterApi sessionUpdaterApi(SessionPersistenceSpi spi,UserPersistenceSpi userPersistenceSpi ) {
        return new SessionUpdaterService(spi,userPersistenceSpi);}
}
