package fr.esgi.gameforgeapi.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@   NoRepositoryBean

public interface GenericRepository<T,UUID extends Serializable> extends BaseRepository <T, UUID> {

}
