

package lt.itakademija.single.votes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class SingleVotesRepository {

	@Autowired
	private EntityManager em;
	
	public SingleVotesEntity save(SingleVotesEntity s) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateOnsaveString = dateFormat.format(new Date());
		s.setDateOnSaving(dateOnsaveString);
		em.persist(s);
		return s;
		
	}
	
	public List <SingleVotesEntity> saveAll(List<SingleVotesEntity> s) {
		for (SingleVotesEntity singleVote:s){ 
			
			if (!findAll().contains(singleVote)) {
				em.persist(singleVote);
			} else {
				SingleVotesEntity merged = em.merge(singleVote);
				em.persist(merged);
			}
		}
		return em.createQuery("SELECT s from SingleVotesEntity s").getResultList();
	}

	public List<SingleVotesEntity> findAll() {
		return em.createQuery("SELECT s from SingleVotesEntity s").getResultList();
	}

	public List<SingleVotesEntity> findSingleVotesByPersonCode(String personCode) {
		return em.createQuery("SELECT s from SingleVotesEntity s WHERE s.personCode = :personCode")
				.setParameter("personCode", personCode).getResultList();
	}
	
	public List<SingleVotesEntity> delete (String personCode) {
		List <SingleVotesEntity> votesToDelete = 
				em.createQuery("SELECT s from SingleVotesEntity s WHERE s.personCode = :personCode")
				.setParameter("personCode", personCode).getResultList();
		for(SingleVotesEntity singleVotes:votesToDelete){
			em.remove(singleVotes);
		}
		return votesToDelete;
	}
	
	public List<SingleVotesEntity> deleteAllByDistrictId (Long districtId) {
		List <SingleVotesEntity> votesToDelete = 
				em.createQuery("SELECT s from SingleVotesEntity s WHERE s.districtId = :districtId")
				.setParameter("districtId", districtId).getResultList();
		for(SingleVotesEntity singleVotes:votesToDelete){
			em.remove(singleVotes);
		}
		return votesToDelete;
	}
}
