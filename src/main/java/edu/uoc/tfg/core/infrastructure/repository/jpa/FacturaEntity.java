package edu.uoc.tfg.core.infrastructure.repository.jpa;

import edu.uoc.tfg.core.domain.Factura;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude={"comunidad"})
@Builder
@NoArgsConstructor
@Table(name = "factura")
public class FacturaEntity implements DomainTranslatable<Factura> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_factura", nullable = false)
    private Date fechaFactura;
    @Column(name = "numeroFactura")
    private String numeroFactura;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "importe", nullable = false)
    private Float importe;
    @Column(name = "pagada", nullable = false)
    private Boolean pagada;
    @Column(name = "autorizada", nullable = false)
    private Boolean autorizada;
    @Column(name = "fecha_pago")
    private Date fechaPago;
    @Column(name = "pdf", nullable = false, columnDefinition = "boolean default false")
    private Boolean pdf;

    //private String pdf;
    @Column(name = "proveedor_id", nullable = false)
    private Long proveedorId;
    @ManyToOne
    @JoinColumn(name="comunidad_id", referencedColumnName = "id")
    private ComunidadEntity comunidad;
    @ManyToOne
    @JoinColumn(name="partida_id", referencedColumnName = "id")
    private PartidaEntity partida;

    public static FacturaEntity fromDomain(Factura factura) {
        if (factura == null) {
            return null;
        }

        return FacturaEntity.builder()
                .id(factura.getId())
                .fechaFactura(factura.getFechaFactura())
                .numeroFactura(factura.getNumeroFactura())
                .descripcion(factura.getDescripcion())
                .importe(factura.getImporte())
                .pagada(factura.getPagada())
                .autorizada(factura.getAutorizada())
                .fechaPago(factura.getFechaPago())
                .pdf(factura.getPdf())
                .proveedorId(factura.getProveedorId())
                .comunidad(ComunidadEntity.fromDomain(factura.getComunidad()))
                .partida(PartidaEntity.fromDomain(factura.getPartida()))
                .build();
    }
    @Override
    public Factura toDomain() {
        return Factura.builder()
                .id(this.getId())
                .fechaFactura(this.getFechaFactura())
                .numeroFactura(this.getNumeroFactura())
                .descripcion(this.getDescripcion())
                .importe(this.getImporte())
                .pagada(this.getPagada())
                .autorizada(this.getAutorizada())
                .fechaPago(this.getFechaPago())
                .pdf(this.getPdf())
                .proveedorId(this.getProveedorId())
                //.comunidad(this.getComunidad().toDomain())//*
                //.partida(this.getPartida().toDomain())
                .build();
    }
}