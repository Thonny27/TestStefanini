package dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ORDENESPAGO")
@XmlRootElement(name = "OrdenesPago")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenesPagoDao {

    @Id
    @Column(name = "id_orden", insertable = false)
    private int idOrden;

    @Column(name = "monto")
    private float monto;

    @Column(name = "moneda")
    private String moneda;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_pago")
    private String fechaPago;

    @Column(name = "id_sucursal")
    private int idSucursal;
}
