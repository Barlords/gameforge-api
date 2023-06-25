package fr.esgi.gameforgeapi.bootstrap.config.domain;

import fr.esgi.gameforgeapi.domain.functional.services.user.UserCreatorService;
import fr.esgi.gameforgeapi.domain.functional.services.user.UserFinderService;
import fr.esgi.gameforgeapi.domain.functional.services.user.UserLoggerService;
import fr.esgi.gameforgeapi.domain.ports.client.UserCreatorApi;
import fr.esgi.gameforgeapi.domain.ports.client.UserFinderApi;
import fr.esgi.gameforgeapi.domain.ports.client.UserLoggerApi;
import fr.esgi.gameforgeapi.domain.ports.server.UserPersistenceSpi;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public UserCreatorApi userCreatorApi(UserPersistenceSpi spi) {return new UserCreatorService(spi);}

    @Bean
    public UserFinderApi userFinderApi(UserPersistenceSpi spi) {return new UserFinderService(spi);}

    @Bean
    public UserLoggerApi userLoggerApi(UserPersistenceSpi spi) {return new UserLoggerService(spi);}

    @Bean
    public org.hibernate.Session session() {
        Session s = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        s.beginTransaction();
        return s;
    }

}
