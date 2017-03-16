package lt.itakademija.singleCorrupt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;



@Repository
public class SingleVotesCorruptRepo {

	@Autowired
	private EntityManager em;
	private Date dateOnsave;

	public SingleVotesCorruptEntity save(SingleVotesCorruptEntity m) {
		
//			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			dateOnsave = new Date();
//			String dateOnsaveString = dateFormat.format(dateOnsave);
//			m.setDateOnSaving(dateOnsaveString);
			em.persist(m);
			return m;
		
	}

	public List<SingleVotesCorruptEntity> findAll() {
		return em.createQuery("SELECT m from SingleVotesCorruptEntity m").getResultList();
	}

	public List<SingleVotesCorruptEntity> findSingleVotesCorruptByDistrict(Long districtId) {
		return em.createQuery("SELECT m from SingleVotesCorruptEntity m WHERE m.districtId = :districtId")
				.setParameter("districtId", districtId).getResultList();
	}
	
	// Grazina singleVotesCorruptEntity kaip objekta, o ne kaip masyva
	
		public SingleVotesCorruptEntity findSingleVotesCorruptEntityByDistrict( Long districtId) {
			return (SingleVotesCorruptEntity) em.createQuery("SELECT m from SingleVotesCorruptEntity m WHERE m.districtId = :districtId")
					.setParameter("districtId", districtId).getSingleResult();
		}
	
	public List<SingleVotesCorruptEntity> deleteById(Long districtId) {
		List<SingleVotesCorruptEntity> votesToDelete = em.createQuery("SELECT m from SingleVotesCorruptEntity m WHERE m.districtId = :districtId")
				.setParameter("districtId", districtId).getResultList();
		for(SingleVotesCorruptEntity multiVotes:votesToDelete){
			em.remove(multiVotes);;
		}
		return votesToDelete;
	}
}
