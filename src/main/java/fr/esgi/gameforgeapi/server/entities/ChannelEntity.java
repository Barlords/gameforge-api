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
@Table(name = "channel")
public class ChannelEntity {

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

    @Column(name = "friend1_id", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID friend1_id;

    @Column(name = "friend2_id", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID friend2_id;

    @Column(name = "creation_date", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private LocalDate creationDate;

    @Column(name = "token", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID token;

}
