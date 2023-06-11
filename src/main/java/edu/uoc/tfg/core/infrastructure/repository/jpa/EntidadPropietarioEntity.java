package edu.uoc.tfg.core.infrastructure.repository.jpa;


import edu.uoc.tfg.core.domain.EntidadPropietario;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "entidad_propietario")
public class EntidadPropietarioEntity implements DomainTranslatable<EntidadPropietario> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "porcentaje_propiedad", nullable = false)
    private Float porcentajePropiedad;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="entidad_id", referencedColumnName = "id")
    private EntidadEntity entidadId;


    public static EntidadPropietarioEntity fromDomain(EntidadPropietario entidadPropietario) {

        if (entidadPropietario == null) {
            return null;
        }

        return EntidadPropietarioEntity.builder()
                .id(entidadPropietario.getId())
                .clienteId(entidadPropietario.getClienteId())
                .porcentajePropiedad(entidadPropietario.getPorcentajePropiedad())
                .entidadId(EntidadEntity.fromDomain(entidadPropietario.getEntidad()))
                .build();
    }

    @Override
    public EntidadPropietario toDomain() {
        return EntidadPropietario.builder()
                .id(this.getId())
                .clienteId(this.getClienteId())
                .porcentajePropiedad(this.getPorcentajePropiedad())
                .entidad(this.getEntidadId().toDomain())
                .build();
    }
}


