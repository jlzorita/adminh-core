package edu.uoc.tfg.core.infrastructure.repository.jpa;

import edu.uoc.tfg.core.domain.Comunidad;
import edu.uoc.tfg.core.domain.Entidad;
import edu.uoc.tfg.core.domain.Factura;
import edu.uoc.tfg.core.domain.Publicacion;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude={"entidades","publicaciones","presupuestos","facturas", "recibos"})
@Builder
@NoArgsConstructor
@Table(name = "comunidad")
public class ComunidadEntity implements DomainTranslatable<Comunidad> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "cif", nullable = false)
    private String cif;
    @Column(name = "cp", nullable = false)
    private String cp;
    @Column(name = "municipio", nullable = false)
    private String municipio;
    @Column(name = "provincia", nullable = false)
    private String provincia;
    @Column(name = "iban", nullable = false)
    private String iban;
    @Column(name = "presidente_id", nullable = false)
    private Long presidenteId;
    @Column(name = "administrador", nullable = false)
    private String administrador;
    @OneToMany(mappedBy="id")
    private Set<PublicacionEntity> publicaciones = new HashSet<>();
    @OneToMany(mappedBy="id")
    private Set<PresupuestoEntity> presupuestos = new HashSet<>();
    @OneToMany(mappedBy="id")
    private Set<EntidadEntity> entidades = new HashSet<>();
    @OneToMany(mappedBy="id")
    private Set<FacturaEntity> facturas = new HashSet<>();

    @OneToMany(mappedBy="id")
    private Set<ReciboEntity> recibos = new HashSet<>();

    public static ComunidadEntity fromDomain(Comunidad comunidad) {
        if (comunidad == null) {
            return null;
        }

        return ComunidadEntity.builder()
                .id(comunidad.getId())
                .nombre(comunidad.getNombre())
                .direccion(comunidad.getDireccion())
                .cif(comunidad.getCif())
                .cp(comunidad.getCp())
                .municipio(comunidad.getMunicipio())
                .provincia(comunidad.getProvincia())
                .iban(comunidad.getIban())
                .presidenteId(comunidad.getPresidenteId())
                .administrador(comunidad.getAdministrador())
                .publicaciones(comunidad.getPublicaciones().stream().map(PublicacionEntity::fromDomain).collect(Collectors.toSet()))
                .presupuestos(comunidad.getPresupuestos().stream().map(PresupuestoEntity::fromDomain).collect(Collectors.toSet()))
                .entidades(comunidad.getEntidades().stream().map(EntidadEntity::fromDomain).collect(Collectors.toSet()))
                .facturas(comunidad.getFacturas().stream().map(FacturaEntity::fromDomain).collect(Collectors.toSet()))
                .recibos(comunidad.getRecibos().stream().map(ReciboEntity::fromDomain).collect(Collectors.toSet()))
                .build();
    }
    @Override
    public Comunidad toDomain() {
        return Comunidad.builder()
                .id(this.getId())
                .nombre(this.getNombre())
                .direccion(this.getDireccion())
                .cif(this.getCif())
                .cp(this.getCp())
                .municipio(this.getMunicipio())
                .provincia(this.getProvincia())
                .iban(this.getIban())
                .presidenteId(this.getPresidenteId())
                .administrador(this.getAdministrador())
                .publicaciones((this.getPublicaciones().stream().map(PublicacionEntity::toDomain).collect(Collectors.toSet())))//*
                .presupuestos((this.getPresupuestos().stream().map(PresupuestoEntity::toDomain).collect(Collectors.toSet())))//*
                .entidades((this.getEntidades().stream().map(EntidadEntity::toDomain).collect(Collectors.toSet())))//*
                .recibos((this.getRecibos().stream().map(ReciboEntity::toDomain).collect(Collectors.toSet())))//*
                .facturas((this.getFacturas().stream().map(FacturaEntity::toDomain).collect(Collectors.toSet())))
                .build();
    }
}