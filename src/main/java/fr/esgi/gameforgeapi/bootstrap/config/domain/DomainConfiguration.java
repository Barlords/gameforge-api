package fr.esgi.gameforgeapi.bootstrap.config.domain;

import fr.esgi.gameforgeapi.client.services.EmailSenderService;
import fr.esgi.gameforgeapi.client.services.websocket.WebSocketHandler;
import fr.esgi.gameforgeapi.domain.functional.services.TokenControllerService;
import fr.esgi.gameforgeapi.domain.functional.services.action.ActionCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.action.ActionFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.friend.FriendCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.friend.FriendDeleterService;
import fr.esgi.gameforgeapi.domain.functional.services.friend.FriendFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.friend.FriendUpdaterService;
import fr.esgi.gameforgeapi.domain.functional.services.game.GameCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.game.GameDeleterService;
import fr.esgi.gameforgeapi.domain.functional.services.game.GameFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.lobby.LobbyCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.lobby.LobbyDeleterService;
import fr.esgi.gameforgeapi.domain.functional.services.lobby.LobbyFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.message.MessageCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.message.MessageFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.rank.RankFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.rating.RatingCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.rating.RatingFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.rating.RatingPatcherService;
import fr.esgi.gameforgeapi.domain.functional.services.session.SessionCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.session.SessionFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.session.SessionUpdaterService;
import fr.esgi.gameforgeapi.domain.functional.services.user.*;
import fr.esgi.gameforgeapi.domain.ports.client.action.ActionCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendDeleterApi;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.friend.FriendUpdaterApi;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameDeleterApi;
import fr.esgi.gameforgeapi.domain.ports.client.game.GameFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyDeleterApi;
import fr.esgi.gameforgeapi.domain.ports.client.lobby.LobbyFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.message.MessageFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.rank.RankFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.rating.RatingCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.rating.RatingFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.rating.RatingPatcherApi;
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
    public RankFinderApi rankFinderApi(RankPersistenceSpi spi) {
        return new RankFinderService(spi);
    }

    @Bean
    public RatingPatcherApi ratingPatcherApi(RatingPersistenceSpi spi, TokenControllerService tokenControllerService) {
        return new RatingPatcherService(spi, tokenControllerService);
    }

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
    public FriendUpdaterApi friendUpdaterApi(FriendPersistenceSpi spi,TokenControllerService tokenControllerService) {
        return new FriendUpdaterService(spi,tokenControllerService);
    }

    @Bean
    public FriendDeleterApi friendDeleterApi(FriendPersistenceSpi spi,TokenControllerService tokenControllerService) {
        return new FriendDeleterService(spi,tokenControllerService);
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
    public FriendFinderService friendFinderService( FriendPersistenceSpi spi,
                                                    TokenControllerService tokenControllerService,
                                                    UserFinderApi userFinderApi) {return new FriendFinderService(spi, tokenControllerService, userFinderApi);}

    @Bean
    public UserFinderApi userFinderApi(UserPersistenceSpi spi, FriendPersistenceSpi friendPersistenceSpi) {return new UserFinderService(spi,friendPersistenceSpi);}

    @Bean
    public UserLoggerApi userLoggerApi(UserPersistenceSpi spi, TokenControllerService tokenControllerService) {return new UserLoggerService(spi, tokenControllerService);}

    @Bean
    public UserModifierService userModifierService() {return new UserModifierService();}

    @Bean
    public UserVerifierApi userVerifierApi(UserPersistenceSpi spi) {
        return new UserVerifierService(spi);
    }

    @Bean
    public LobbyCreatorApi lobbyCreatorApi(LobbyPersistenceSpi spi, TokenControllerService tokenControllerService) {
        return new LobbyCreatorService(spi, tokenControllerService);}

    @Bean
    public LobbyFinderApi lobbyFinderApi(LobbyPersistenceSpi spi) {return new LobbyFinderService(spi);}

    @Bean
    public LobbyDeleterApi lobbyDeleterApi(LobbyPersistenceSpi spi, TokenControllerService tokenControllerService) {
        return new LobbyDeleterService(spi, tokenControllerService);
    }


    @Bean
    public GameCreatorApi gameCreatorApi(GamePersistenceSpi spi, TokenControllerService tokenControllerService) {
        return new GameCreatorService(spi, tokenControllerService);
    }

    @Bean
    public GameFinderApi gameFinderApi(GamePersistenceSpi spi) {return new GameFinderService(spi);}

    @Bean
    public GameDeleterApi gameDeleterApi(GamePersistenceSpi spi, TokenControllerService tokenControllerService) {
        return new GameDeleterService(spi, tokenControllerService);
    }

    @Bean
    public SessionFinderApi sessionFinderApi(SessionPersistenceSpi spi) {return new SessionFinderService(spi);}

    @Bean
    public SessionFinderService sessionFinderService(SessionPersistenceSpi spi) {
        return new SessionFinderService(spi);
    }
    @Bean
    public SessionCreatorApi sessionCreatorApi(SessionPersistenceSpi spi,SessionFinderService sessionFinderService,TokenControllerService tokenControllerService) {
        return new SessionCreatorService(spi,sessionFinderService,tokenControllerService);}

    @Bean
    public SessionUpdaterApi sessionUpdaterApi(SessionPersistenceSpi spi,UserPersistenceSpi userPersistenceSpi ) {
        return new SessionUpdaterService(spi,userPersistenceSpi);}

    @Bean
    public ActionFinderService actionFinderService(ActionPersistenceSpi spi) {
        return new ActionFinderService(spi);
    }
    @Bean
    public ActionCreatorApi actionCreatorApi(ActionPersistenceSpi spi,TokenControllerService tokenControllerService) {
        return new ActionCreatorService(spi,tokenControllerService);}
}
