package lt.itakademija.multi.votes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lt.itakademija.countyCRUD.CountyEntity;

@Repository
public class MultiVotesRepository {

	@Autowired
	private EntityManager em;

	public Long getVotesCount(){
		return em.createQuery("SELECT SUM(x.votesMultiParty) FROM MultiVotesEntity x", Long.class).getSingleResult();
	}
	
	public MultiVotesEntity save(MultiVotesEntity m) {
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		String dateOnsaveString = dateFormat.format(new Date());
//		m.setDateOnSaving(dateOnsaveString);
		em.persist(m);
		return m;
	}

	public List <MultiVotesEntity> saveAll(List <MultiVotesEntity> m) {
		for (MultiVotesEntity mulitVotes:m){
		if (mulitVotes.getId() == null) {
			em.persist(mulitVotes);
		} else {
			MultiVotesEntity merged = em.merge(mulitVotes);
			em.persist(merged);
		}
		}
		return em.createQuery("SELECT m from MultiVotesEntity m").getResultList();
	}

	public List<MultiVotesEntity> findAll() {
		return em.createQuery("SELECT m from MultiVotesEntity m").getResultList();
	}

	public List<MultiVotesEntity> findMultiVotesByPartyId(Long partyId) {
		return em.createQuery("SELECT m from MultiVotesEntity m WHERE m.partyId = :partyId")
				.setParameter("partyId", partyId).getResultList();
	}
	
	public List<MultiVotesEntity> findMultiVotesByPartyIdInDistrict(Long partyId, Long districtId) {
		return em.createQuery("SELECT m from MultiVotesEntity m WHERE m.partyId = :partyId and m.districtId = :districtId")
				.setParameter("partyId", partyId).setParameter("districtId",  districtId).getResultList();
	}

	public List<MultiVotesEntity> delete(Long partyId) {
		List<MultiVotesEntity> votesToDelete = em
				.createQuery("SELECT m from MultiVotesEntity m WHERE m.partyId = :partyId")
				.setParameter("partyId", partyId).getResultList();
		for (MultiVotesEntity multiVotes : votesToDelete) {
			em.remove(multiVotes);
		}
		return votesToDelete;
	}

	public List<MultiVotesEntity> deleteAllByDistrictId(Long districtId) {
		List<MultiVotesEntity> votesToDelete = em
				.createQuery("SELECT m from MultiVotesEntity m WHERE m.districtId = :districtId")
				.setParameter("districtId", districtId).getResultList();
		for (MultiVotesEntity multiVotes : votesToDelete) {
			em.remove(multiVotes);
		}
		return votesToDelete;
	}
}