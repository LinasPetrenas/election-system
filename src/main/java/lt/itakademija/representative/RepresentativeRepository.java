package lt.itakademija.representative;

import java.util.List;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepresentativeRepository {

	@Autowired
	private EntityManager em;

	public RepresentativeEntity save(RepresentativeEntity d) {
		em.persist(d);
		/*if (!findAll().contains(d)) {
			em.persist(d);
			return d;
		} else {
			RepresentativeEntity merged = em.merge(d);
			em.persist(merged);
			return merged;
		}*/
		
		return d;
	}

	public List<RepresentativeEntity> findAll() {
		return em.createQuery("SELECT d from RepresentativeEntity d").getResultList();
	}

	public RepresentativeEntity delete(Long id) {
		RepresentativeEntity c = em.find(RepresentativeEntity.class, id);
		em.remove(c);
		return c;
	}

	
	public RepresentativeEntity find(Long id) {
		return (RepresentativeEntity) em.createQuery
				("SELECT d from RepresentativeEntity d WHERE districtId=:id").setParameter("id", id).getSingleResult();
	}
	
}
