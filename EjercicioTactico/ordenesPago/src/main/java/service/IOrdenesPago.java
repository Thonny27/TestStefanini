package service;


import dao.OrdenesPago;

import java.util.List;

public interface IOrdenesPago {

	
	List<OrdenesPago> getOrdenesPagoBySucursal(OrdenesPago orden);

}