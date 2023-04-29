package edu.uoc.tfg.core.infrastructure.repository.jpa;

import edu.uoc.tfg.core.domain.Partida;
import edu.uoc.tfg.core.domain.Presupuesto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@Table(name = "partida")
public class PartidaEntity implements DomainTranslatable<Partida> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "autorizacion", nullable = false)
    private Boolean autorizacion;
    @OneToMany(mappedBy="id")
    private Set<FacturaEntity> facturas = new HashSet<>();


    public static PartidaEntity fromDomain(Partida partida) {
        if (partida == null) {
            return null;
        }

        return PartidaEntity.builder()
                .id(partida.getId())
                .nombre(partida.getNombre())
                .autorizacion(partida.getAutorizacion())
                .facturas(partida.getFacturas().stream().map(FacturaEntity::fromDomain).collect(Collectors.toSet()))
                .build();
    }
    @Override
    public Partida toDomain() {
        return Partida.builder()
                .id(this.getId())
                .nombre(this.getNombre())
                .autorizacion(this.getAutorizacion())
                .facturas((this.getFacturas().stream().map(FacturaEntity::toDomain).collect(Collectors.toSet())))
                .build();
    }
}