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
@Table(name = "message")
public class MessageEntity {

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

    @Column(name = "sender_id", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID senderId;

    @Column(name = "channel_id", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID channelId;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "send_date", updatable = false, nullable = false)
    @JdbcTypeCode(Types.VARCHAR)
    private LocalDate sendDate;

}
