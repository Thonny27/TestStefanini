package com.banco.service;

import java.util.List;

import dao.Sucursales;

public interface ISucursal {

	
	List<Sucursales> getSucursalByBanco(Sucursales sucursal);

}