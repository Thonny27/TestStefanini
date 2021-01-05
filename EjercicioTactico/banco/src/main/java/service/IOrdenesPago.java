package service;

import dao.OrdenesPagoDao;

import java.util.List;

public interface IOrdenesPago {

	OrdenesPagoDao addOrdenesPago(OrdenesPagoDao ordenesPagoDao);

	List<OrdenesPagoDao> getOrdenesPago();

	OrdenesPagoDao updateOrdenesPago(OrdenesPagoDao ordenesPagoDao);

	OrdenesPagoDao deleteOrdenesPago(OrdenesPagoDao ordenesPagoDao);
}