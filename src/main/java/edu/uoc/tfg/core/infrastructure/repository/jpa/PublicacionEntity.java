package edu.uoc.tfg.core.infrastructure.repository.jpa;

import edu.uoc.tfg.core.domain.Publicacion;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@Table(name = "publicacion")
public class PublicacionEntity implements DomainTranslatable<Publicacion> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private Date fechaFin;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "mensaje", nullable = false, length = 1024)
    private String mensaje;

    @Column(name = "fecha_evento", nullable = false)
    private Date fechaEvento;

    @ManyToOne
    @JoinColumn(name="comunidad_id", referencedColumnName = "id")
    private ComunidadEntity comunidad;

    public static PublicacionEntity fromDomain(Publicacion publicacion) {
        if (publicacion == null) {
            return null;
        }

        return PublicacionEntity.builder()
                .id(publicacion.getId())
                .fechaInicio(publicacion.getFechaInicio())
                .fechaFin(publicacion.getFechaFin())
                .titulo(publicacion.getTitulo())
                .mensaje(publicacion.getMensaje())
                .fechaEvento(publicacion.getFechaEvento())
                .comunidad(ComunidadEntity.fromDomain(publicacion.getComunidad()))
                .build();
    }
    @Override
    public Publicacion toDomain() {
        return Publicacion.builder()
                .id(this.getId())
                .fechaInicio(this.getFechaInicio())
                .fechaFin(this.getFechaFin())
                .titulo(this.getTitulo())
                .mensaje(this.getMensaje())
                .fechaEvento(this.getFechaEvento())
                .comunidad(this.getComunidad().toDomain())
                .build();
    }
}