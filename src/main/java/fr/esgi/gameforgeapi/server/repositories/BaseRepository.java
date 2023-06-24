package fr.esgi.gameforgeapi.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@   NoRepositoryBean

public interface BaseRepository<T,UUID extends Serializable> extends JpaRepository <T, UUID> {

}
