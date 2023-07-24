package fr.esgi.gameforgeapi.server.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy =  "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @Column(name = "lobby_id", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID lobbyId;

    @Column(name = "user_id", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID userId;



    @Column(name = "action_time", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private LocalDate actionTime;

    @Column(name = "action", nullable = false)
    private String action;

}
