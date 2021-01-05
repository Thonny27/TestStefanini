package service;

import dao.BancoDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Consumes("application/json")
@Produces("application/json")
public class BancoImpl implements IBanco {

	private static final String PERSITENCE = "persistence-banco";
	@POST
	@Path("/addBanco")
	public BancoDao addBanco(BancoDao entrada) {

		BancoDao banco = new BancoDao(entrada.getIdBanco(),entrada.getNombreBanco(), entrada.getDireccionBanco(), entrada.getFechaBanco());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(banco);
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

	@SuppressWarnings("unchecked")
	@GET
	@Path("/getBanco")
	@Override
	public List<BancoDao> getBanco() {
		
		List<BancoDao> bancos = new ArrayList<>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();
		try {
			bancos = em.createQuery("select b FROM BancoDao b").getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return bancos;
	}

	@POST
	@Path("/updateBanco")
	@Override
	public BancoDao updateBanco(BancoDao entrada) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();

		BancoDao banco = new BancoDao();
		try {
			em.getTransaction().begin();
			banco = em.find(BancoDao.class, entrada.getIdBanco());

			banco.setDireccionBanco(entrada.getDireccionBanco());
			banco.setFechaBanco(entrada.getFechaBanco());
			banco.setNombreBanco(entrada.getNombreBanco());
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
	@Path("/deleteBanco")
	@Override
	public BancoDao deleteBanco(BancoDao entrada) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();

		try {
			BancoDao banco = em.find(BancoDao.class, entrada.getIdBanco());
			em.getTransaction().begin();
			em.remove(banco);
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