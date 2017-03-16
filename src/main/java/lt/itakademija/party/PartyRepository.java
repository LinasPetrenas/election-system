package lt.itakademija.party;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import lt.itakademija.candidateCRUD.CandidateEntity;

@Repository
public class PartyRepository {

	@Autowired
	private EntityManager em;
	

	public PartyEntity save(PartyEntity p) {
		em.persist(p);
		/*if (p.getPartyId() == null) {
			em.persist(p);
		} else {
			PartyEntity merged = em.merge(p);
			em.persist(merged);
			return merged;
		}*/
		return p;
	}

	public List<PartyEntity> findAll() {
		return em.createQuery("SELECT p from PartyEntity p WHERE NOT partyId=0").getResultList();
	}

	
	
	
	
	public List<PartyEntity> findAllParties() {
		return em.createQuery("SELECT p from PartyEntity p").getResultList();
	}
	
	public PartyEntity delete(Long id) {
		PartyEntity p = em.find(PartyEntity.class, id);
		em.remove(p);
		return p;
	}
	
	
	public PartyEntity findPartyById(@PathVariable Long partyId) {
		return (PartyEntity) em.createQuery("SELECT p from PartyEntity p WHERE partyId=:partyId").setParameter("partyId", partyId).getSingleResult();
	}
	
	public PartyEntity findPartyByPersonCode(String personCode) {
		
		CandidateEntity c = (CandidateEntity) em.createQuery("SELECT c FROM CandidateEntity c WHERE personCode=:personCode")
		.setParameter("personCode", personCode).getSingleResult();

		return (PartyEntity) em.createQuery("SELECT p FROM PartyEntity p WHERE partyId=:id")
				.setParameter("id", new Long(c.getPartyId())).getSingleResult();
	}

}
