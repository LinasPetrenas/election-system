package lt.itakademija.auth;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import lt.itakademija.candidateCRUD.CandidateEntity;
import lt.itakademija.districtCRUD.DistrictEntity;
import lt.itakademija.representative.RepresentativeEntity;

@Repository
public class AuthenticationRepository {

	@Autowired
	private EntityManager em;

	public AuthenticationEntity save(AuthenticationEntity a) {

		if (!findAll().contains(findUser(a.getId()))) {
			em.persist(a);
			return a;
		} else {
			AuthenticationEntity merged = em.merge(a);
			em.persist(merged);
			return merged;
		}

	}

	public AuthenticationEntity findUser(Long id) {
		return em.find(AuthenticationEntity.class, id);
	}

	public AuthenticationEntity findUserByNameAndPass(AuthenticationEntity a) {
		return (AuthenticationEntity) em
				.createQuery("SELECT a FROM AuthenticationEntity a WHERE userName=:name AND " + "password=:pass")
				.setParameter("name", a.getUserName()).setParameter("pass", a.getPassword()).getSingleResult();
	}

	public List<AuthenticationEntity> findAll() {
		return em.createQuery("SELECT a from AuthenticationEntity a").getResultList();
	}

	
	// kazkodel neveikia em.remove su em.find
	public int delete( Long id) {
		
		
		return em.createQuery("DELETE FROM AuthenticationEntity a WHERE id=:id ").setParameter("id", id).executeUpdate();
	}

}
