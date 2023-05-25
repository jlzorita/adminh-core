package edu.uoc.tfg.core.infrastructure.repository.jpa;

import edu.uoc.tfg.core.domain.Recibo;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude={"comunidad", "entidad"})
@Builder
@NoArgsConstructor
@Table(name = "recibo")
public class ReciboEntity implements DomainTranslatable<Recibo> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "concepto", nullable = false)
    private String concepto;

    @Column(name = "fecha_recibo", nullable = false)
    private Date fechaRecibo;

    @Column(name = "importe", nullable = false)
    private Float importe;

    @Column(name = "fecha_pago")
    private Date fechaPago;

    @Column(name = "pagado", nullable = false)
    private Boolean pagado;
    @ManyToOne
    @JoinColumn(name="entidad_id", referencedColumnName = "id")
    private EntidadEntity entidad;
    @ManyToOne
    @JoinColumn(name="comunidad_id", referencedColumnName = "id")
    private ComunidadEntity comunidad;



    public static ReciboEntity fromDomain(Recibo recibo) {
        if (recibo == null) {
            return null;
        }

        return ReciboEntity.builder()
                .id(recibo.getId())
                .concepto(recibo.getConcepto())
                .fechaRecibo(recibo.getFechaRecibo())
                .importe(recibo.getImporte())
                .fechaPago(recibo.getFechaPago())
                .pagado(recibo.getPagado())
                .comunidad(ComunidadEntity.fromDomain(recibo.getComunidad()))
                .entidad(EntidadEntity.fromDomain(recibo.getEntidad()))
                .build();
    }
    @Override
    public Recibo toDomain() {
        return Recibo.builder()
                .id(this.getId())
                .concepto(this.getConcepto())
                .fechaRecibo(this.getFechaRecibo())
                .importe(this.getImporte())
                .fechaPago(this.getFechaPago())
                .pagado(this.getPagado())
                //.comunidad(this.getComunidad().toDomain())//*
                .entidad(this.getEntidad().toDomain())//*

                .build();
    }
}