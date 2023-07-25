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
@Table(name = "game")
public class GameEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "src_path", nullable = false, unique = true)
    private String srcPath;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "picture_presentation", nullable = false)
    private String picturePresentation;

    @Column(name = "creator_id", updatable = false, nullable = false)
    private UUID creatorId;

    @Column(name = "creation_date", updatable = false, nullable = false)
    private LocalDate creationDate;

}
