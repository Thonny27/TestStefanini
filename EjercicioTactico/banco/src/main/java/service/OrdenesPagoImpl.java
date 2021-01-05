package service;

import dao.OrdenesPagoDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;


@Consumes("application/json")
@Produces("application/json")
public class OrdenesPagoImpl implements IOrdenesPago {
	private static final String PERSITENCE = "persistence-banco";

	@POST
	@Path("/addIOrdenesPago")
	public OrdenesPagoDao addOrdenesPago(OrdenesPagoDao entrada) {
		OrdenesPagoDao orden = new OrdenesPagoDao(entrada.getIdOrden(),entrada.getMonto(), entrada.getMoneda(), entrada.getEstado(),
				entrada.getFechaPago(), entrada.getIdSucursal());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			em.persist(orden);


			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return orden;
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/getOrdenesPago")
	@Override
	public List<OrdenesPagoDao> getOrdenesPago() {

		List<OrdenesPagoDao> ordenes = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();
		try {

			ordenes = em.createQuery("select b FROM OrdenesPagoDao b").getResultList();

		} catch (Exception ex) {

			ex.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return ordenes;
	}

	@POST
	@Path("/updateOrdenesPago")
	@Override
	public OrdenesPagoDao updateOrdenesPago(OrdenesPagoDao entrada) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();

		OrdenesPagoDao orden = new OrdenesPagoDao();
		try {
			em.getTransaction().begin();
			orden = em.find(OrdenesPagoDao.class, entrada.getIdOrden());

			orden.setMonto(entrada.getMonto());
			orden.setMoneda(entrada.getMoneda());
			orden.setEstado(entrada.getEstado());
			orden.setFechaPago(entrada.getFechaPago());
			orden.setIdSucursal(entrada.getIdSucursal());
			em.getTransaction().commit();

		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return entrada;
	}
	@POST
	@Path("/deleteOrdenesPago")
	@Override
	public OrdenesPagoDao deleteOrdenesPago(OrdenesPagoDao banco) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();

		try {

			OrdenesPagoDao orden = em.find(OrdenesPagoDao.class, banco.getIdOrden());
			em.getTransaction().begin();
			em.remove(orden);
			em.getTransaction().commit();

		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return banco;
	}
}