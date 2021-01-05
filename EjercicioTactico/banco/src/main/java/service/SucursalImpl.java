package service;

import dao.SucursalesDao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Consumes("application/json")
@Produces("application/json")
public class SucursalImpl implements ISucursal {
	private static final String PERSITENCE = "persistence-banco";

	@POST
	@Path("/addSucursal")
	public SucursalesDao addSucursal(SucursalesDao entrada) {
		SucursalesDao sucursal = new SucursalesDao(entrada.getIdSucursal(),entrada.getNombreSucursal(), entrada.getDireccionSucursal(),
				entrada.getFechaSucursal(), entrada.getIdBanco());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			em.persist(sucursal);

			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			ex.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return sucursal;
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/getSucursal")
	@Override
	public List<SucursalesDao> getSucursal() {
		List<SucursalesDao> sucursales = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();
		try {

			sucursales = em.createQuery("select b FROM SucursalesDao b").getResultList();
		} catch (Exception ex) {

			ex.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return sucursales;
	}

	@POST
	@Path("/updateSucursal")
	@Override
	public SucursalesDao updateSucursal(SucursalesDao entrada) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();

		SucursalesDao sucursales = new SucursalesDao();
		try {
			em.getTransaction().begin();
			sucursales = em.find(SucursalesDao.class, entrada.getIdSucursal());

			sucursales.setDireccionSucursal(entrada.getDireccionSucursal());
			sucursales.setFechaSucursal(entrada.getFechaSucursal());
			sucursales.setNombreSucursal(entrada.getNombreSucursal());
			sucursales.setIdBanco(entrada.getIdBanco());
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
	@Path("/deleteSucursal")
	@Override
	public SucursalesDao deleteSucursal(SucursalesDao entrada) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();

		try {

			SucursalesDao sucursal = em.find(SucursalesDao.class, entrada.getIdSucursal());
			em.getTransaction().begin();
			em.remove(sucursal);
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
}