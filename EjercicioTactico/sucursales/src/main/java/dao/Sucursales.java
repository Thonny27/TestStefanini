package com.banco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "SUCURSALES")
@XmlRootElement(name = "Sucursales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursales {
	@Id 
	@Column(name = "id_sucursal", insertable = false)
	private int idSucursal;

	@Column(name= "nombre_sucursal")
	private String nombreSucursal;

	@Column(name= "direccion_sucursal")
	private String direccionSucursal;
	
	@Column(name= "fecha_sucursal")
	private String fechaSucursal;

	@Column(name= "id_banco")
	private int idBanco;

}
