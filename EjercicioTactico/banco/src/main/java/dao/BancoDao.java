package dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "BANCO")
@XmlRootElement(name = "Banco")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BancoDao {

    @Id
    @Column(name = "id_banco", insertable = false)
    private int idBanco;

    @Column(name= "banco_nombre")
    private String nombreBanco;

    @Column(name= "banco_direccion")
    private String direccionBanco;

    @Column(name= "banco_fecha_registro")
    private String fechaBanco;

}
