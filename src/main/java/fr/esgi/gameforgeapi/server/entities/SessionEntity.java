package fr.esgi.gameforgeapi.server.entities;


import fr.esgi.gameforgeapi.domain.functional.models.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "session")
public class SessionEntity implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "lobby_id", updatable = false, nullable = false)
    private UUID lobbyId;

    @Column(name = "join_date", updatable = false, nullable = false)
    private LocalDate joinDate;

    @Column(name = "quit_date")
    private LocalDate quitDate;

}
