package fr.esgi.gameforgeapi.server.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.io.Serializable;
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
@Table(name = "rank")
public class RankEntity implements Serializable {

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

    @Column(name = "game_id", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID gameId;

    @Column(name = "user_id", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID userId;

    @Column(name = "played_game", nullable = false)
    private int playedGame;

    @Column(name = "win_game", updatable = false, nullable = false)
    private int winGame;

}
