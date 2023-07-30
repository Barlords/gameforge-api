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
@Table(name = "action")
public class ActionEntity {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "lobby_id", updatable = false, nullable = false)
    private UUID lobbyId;

    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "action_time", updatable = false, nullable = false)
    private LocalDate actionTime;

    @Column(name = "action", nullable = false)
    private String action;

}
