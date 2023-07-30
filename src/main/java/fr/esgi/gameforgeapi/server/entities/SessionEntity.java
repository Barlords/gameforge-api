package fr.esgi.gameforgeapi.server.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

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
public class SessionEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "lobby_id", updatable = false, nullable = false)
    private UUID lobbyId;

    @Column(name = "join_date", updatable = false, nullable = false)
    private LocalDate joinDate;

    @Column(name = "quit_date", updatable = false)
    private LocalDate quitDate;

}
