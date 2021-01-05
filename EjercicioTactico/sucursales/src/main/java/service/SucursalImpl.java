package com.banco.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import dao.Sucursales;

@Consumes("application/xml")
@Produces("application/xml")
public class SucursalImpl implements ISucursal {
	private static final String PERSITENCE = "persistence-banco";

	@POST
	@Path("/getSucursalByBanco")
	public List<Sucursales> getSucursalByBanco(Sucursales sucursal) {
		List<Sucursales> sucursales = new ArrayList<Sucursales>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();
		try {

			TypedQuery<Sucursales> tp = em.createQuery(
					"select su " + "from  Sucursales su " + "WHERE  su.idBanco= :idBanco", Sucursales.class);

			tp.setParameter("idBanco", sucursal.getIdBanco());
			sucursales = tp.getResultList();
		} catch (Exception ex) {

			ex.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return sucursales;
	}

}