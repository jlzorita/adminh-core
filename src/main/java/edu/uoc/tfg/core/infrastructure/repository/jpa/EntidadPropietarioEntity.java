package edu.uoc.tfg.core.infrastructure.repository.jpa;


import edu.uoc.tfg.core.domain.EntidadPropietario;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Table(name = "entidad_propietario")
public class EntidadPropietarioEntity implements DomainTranslatable<EntidadPropietario> {
    @Column(name= "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "porcentaje_propiedad", nullable = false)
    private Float porcentajePropiedad;

    public static EntidadPropietarioEntity fromDomain(EntidadPropietario entidadPropietario) {
        return EntidadPropietarioEntity.builder()
                .clienteId(entidadPropietario.getClienteId())
                .porcentajePropiedad(entidadPropietario.getPorcentajePropiedad())
                .build();
    }

    @Override
    public EntidadPropietario toDomain() {
        return EntidadPropietario.builder()
                .clienteId(this.getClienteId())
                .porcentajePropiedad(this.getPorcentajePropiedad())
                .build();
    }
}


