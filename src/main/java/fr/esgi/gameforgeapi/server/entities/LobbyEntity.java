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
@Table(name = "lobby")
public class LobbyEntity {

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

    @Column(name = "game_id", nullable = false)
    private UUID gameId;

    @Column(name = "creation_date", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private LocalDate creationDate;

    @Column(name = "start_date")
    @JdbcTypeCode(Types.VARCHAR)
    private LocalDate startDate;

    @Column(name = "finished", nullable = false)
    private boolean finished;

    @Column(name = "winner", nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID winner;
}
