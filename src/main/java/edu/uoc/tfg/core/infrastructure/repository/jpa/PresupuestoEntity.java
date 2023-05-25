package edu.uoc.tfg.core.infrastructure.repository.jpa;

import edu.uoc.tfg.core.domain.Comunidad;
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
@EqualsAndHashCode(exclude={"partidas"})
@Builder
@NoArgsConstructor
@Table(name = "presupuesto")
public class PresupuestoEntity implements DomainTranslatable<Presupuesto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_inicial", nullable = false)
    private Date fechaInicial;
    @Column(name = "fecha_final", nullable = false)
    private Date fechaFinal;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "saldo_inicial", nullable = false)
    private Float saldoInicial;
    @OneToMany(mappedBy="id")
    private Set<PartidaEntity> partidas = new HashSet<>();
    @OneToMany(mappedBy="id")
    private Set<ReciboEntity> recibos = new HashSet<>();
    @ManyToOne
    @JoinColumn(name="comunidad_id", referencedColumnName = "id")
    private ComunidadEntity comunidad;

    public static PresupuestoEntity fromDomain(Presupuesto presupuesto) {
        if (presupuesto == null) {
            return null;
        }

        return PresupuestoEntity.builder()
                .id(presupuesto.getId())
                .fechaInicial(presupuesto.getFechaInicial())
                .fechaFinal(presupuesto.getFechaFinal())
                .nombre(presupuesto.getNombre())
                .saldoInicial(presupuesto.getSaldoInicial())
                .partidas(presupuesto.getPartidas().stream().map(PartidaEntity::fromDomain).collect(Collectors.toSet()))
                .recibos(presupuesto.getRecibos().stream().map(ReciboEntity::fromDomain).collect(Collectors.toSet()))
                .comunidad(ComunidadEntity.fromDomain(presupuesto.getComunidad()))
                .build();
    }
    @Override
    public Presupuesto toDomain() {
        return Presupuesto.builder()
                .id(this.getId())
                .fechaInicial(this.getFechaInicial())
                .fechaFinal(this.getFechaFinal())
                .nombre(this.getNombre())
                .saldoInicial(this.getSaldoInicial())
                //.partidas((this.getPartidas().stream().map(PartidaEntity::toDomain).collect(Collectors.toSet())))
                //.recibos((this.getRecibos().stream().map(ReciboEntity::toDomain).collect(Collectors.toSet())))
                //.comunidad(this.getComunidad().toDomain())
                .build();
    }
}