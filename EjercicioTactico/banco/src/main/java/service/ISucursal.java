package service;


import dao.SucursalesDao;

import java.util.List;

public interface ISucursal {

	SucursalesDao addSucursal(SucursalesDao sucursal);

	List<SucursalesDao> getSucursal();

	SucursalesDao updateSucursal(SucursalesDao sucursal);

	SucursalesDao deleteSucursal(SucursalesDao sucursal);
}