package service;

import dao.OrdenesPago;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;


@Consumes("application/json")
@Produces("application/json")
public class OrdenesPagoImpl implements IOrdenesPago {
	private static final String PERSITENCE = "persistence-banco";
	@POST
	@Path("/getOrdenesPagoBySucursal")
	public List<OrdenesPago> getOrdenesPagoBySucursal(OrdenesPago orden) {
		List<OrdenesPago> ordenes = new ArrayList<OrdenesPago>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSITENCE);
		EntityManager em = emf.createEntityManager();
		try {

			 TypedQuery<OrdenesPago> tp = em.createQuery("select mr "
	    			  + "from  OrdenesPago mr "
	    			  + "WHERE mr.moneda=:moneda AND mr.idSucursal= :idSucursal", OrdenesPago.class);
	    	 tp.setParameter("moneda", orden.getMoneda());
	    	 tp.setParameter("idSucursal", orden.getIdSucursal());
	         ordenes=tp.getResultList();
		} catch (Exception ex) {

			ex.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return ordenes;
	}

	



	
}