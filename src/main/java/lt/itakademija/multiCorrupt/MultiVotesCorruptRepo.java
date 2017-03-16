package lt.itakademija.multiCorrupt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MultiVotesCorruptRepo {

	@Autowired
	private EntityManager em;

	public MultiVotesCorruptEntity save(MultiVotesCorruptEntity m) {
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		String dateOnsaveString = dateFormat.format(new Date());
//		m.setDateOnSaving(dateOnsaveString);
		em.persist(m);
		return m;
	}

	public List<MultiVotesCorruptEntity> findAll() {
		return em.createQuery("SELECT m from MultiVotesCorruptEntity m").getResultList();
	}

	public List<MultiVotesCorruptEntity> findMultiVotesCorruptByDistrict(Long districtId) {
		return em.createQuery("SELECT m from MultiVotesCorruptEntity m WHERE m.districtId = :districtId")
				.setParameter("districtId", districtId).getResultList();
	}
	
	// Grazina multiVotesCorruptEntity kaip objekta
	
	public MultiVotesCorruptEntity findMultiVotesCorruptEntityByDistrict( Long districtId) {
		return (MultiVotesCorruptEntity) em.createQuery("SELECT m from MultiVotesCorruptEntity m WHERE m.districtId = :districtId")
				.setParameter("districtId", districtId).getSingleResult();
	}

	public List<MultiVotesCorruptEntity> delete(Long districtId) {
		List<MultiVotesCorruptEntity> votesToDelete = em
				.createQuery("SELECT m from MultiVotesCorruptEntity m WHERE m.districtId = :districtId")
				.setParameter("districtId", districtId).getResultList();
		for (MultiVotesCorruptEntity multiVotes : votesToDelete) {
			em.remove(multiVotes);
			;
		}
		return votesToDelete;
	}

}
