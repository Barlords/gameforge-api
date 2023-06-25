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
@Table(name = "session")
public class SessionEntity implements Serializable {

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

    @Column(name = "user_id", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID userId;

    @Column(name = "lobby_id", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID lobbyId;

    @Column(name = "join_date", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private LocalDate joinDate;

    @Column(name = "quit_date", updatable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private LocalDate quitDate;

}
