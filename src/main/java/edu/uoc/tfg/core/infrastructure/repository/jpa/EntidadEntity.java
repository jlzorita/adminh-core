package edu.uoc.tfg.core.infrastructure.repository.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.uoc.tfg.core.domain.Entidad;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude={"recibos","propietarios", "comunidad"})
@Builder
@NoArgsConstructor
@Table(name = "entidad")
public class EntidadEntity implements DomainTranslatable<Entidad> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "coeficiente", nullable = false)
    private Float coeficiente;

    @OneToMany(mappedBy="id")
    private Set<ReciboEntity> recibos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="comunidad_id", referencedColumnName = "id")
    private ComunidadEntity comunidad;

    @OneToMany(mappedBy="id")
    private Set<EntidadPropietarioEntity> propietarios = new HashSet<>();

    public static EntidadEntity fromDomain(Entidad entidad) {
        if (entidad == null) {
            return null;
        }

        return EntidadEntity.builder()
                .id(entidad.getId())
                .nombre(entidad.getNombre())
                .coeficiente(entidad.getCoeficiente())
                .recibos(entidad.getRecibos().stream().map(ReciboEntity::fromDomain).collect(Collectors.toSet()))
                .propietarios(entidad.getPropietarios().stream().map(EntidadPropietarioEntity::fromDomain).collect(Collectors.toSet()))
                .comunidad(ComunidadEntity.fromDomain(entidad.getComunidad()))
                .build();
    }
        @Override
        public Entidad toDomain() {
            return Entidad.builder()
                    .id(this.getId())
                    .nombre(this.getNombre())
                    .coeficiente(this.getCoeficiente())
                    //.recibos((this.getRecibos().stream().map(ReciboEntity::toDomain).collect(Collectors.toSet())))//*
                    //.propietarios((this.getPropietarios().stream().map(EntidadPropietarioEntity::toDomain).collect(Collectors.toSet())))//*
                    //.comunidad(this.getComunidad().toDomain())
                    .build();
        }
}