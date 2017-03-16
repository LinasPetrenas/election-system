package lt.itakademija.candidateCRUD;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import lt.itakademija.representative.RepresentativeEntity;


@Repository
public class CandidateRepository {

	@Autowired
	private EntityManager em;

	public CandidateEntity save(CandidateEntity c) {
		
		boolean candidateExists =false;
		CandidateEntity newC = new CandidateEntity();
		List <CandidateEntity> allCandidates = this.findAll();
		for (CandidateEntity candidate:allCandidates){
			if(candidate.getPersonCode().equals(c.getPersonCode())){
				candidateExists=true;
				newC = candidate;
			}
		}

		if (!candidateExists) {
			em.persist(c);
			return c;
		} else {
			if(c.isMulti()){
				newC.setMulti(true);
			}
			if (c.isSingle()){
				newC.setSingle(true);
			}
			CandidateEntity merged = em.merge(newC);
			em.persist(merged);
			return merged;
		}
	}
	

	
	// Skirta Atstovo puslapiui. Paduoda kandidatu sarasa pagal apylinkes ID (iesko apygardos kurioje yra nurodoma apylinke ir)
	// Labai klaidinantys parametru kintamuju pavadinimai, bet pagal logika viskas veikia viskas veikiai.
	public List<CandidateEntity> candidateListByCountyId(Long countyId) {
		
		
		RepresentativeEntity representative =	
				(RepresentativeEntity) em.createQuery("SELECT r from RepresentativeEntity r WHERE districtId=:countyId")
				.setParameter("countyId", countyId).getSingleResult();
		
		return em.createQuery("SELECT c from CandidateEntity c WHERE countyId=:countyId").setParameter("countyId", representative.getCountyId()).getResultList();
	
	}
	
	
	// Kandidatu sarasas vienmandateje apygadoje pagal apygardos ID
	public List<CandidateEntity> candidateListForSingleMemberResultByCountyId( Long countyId) {
		return em.createQuery("SELECT c from CandidateEntity c WHERE countyId=:countyId").setParameter("countyId", countyId).getResultList();
	}

	public List<CandidateEntity> findAll() {
		return em.createQuery("SELECT c from CandidateEntity c").getResultList();
	}

	public CandidateEntity findCandidate(String personCode) {
		return em.find(CandidateEntity.class, personCode);
	}

	// Trina visus kandidatus kurie priklauso tam tikrai partijai kai trinama
	// partija trinamas ir jos sarasas(Partijos
	// kandidatu saraso trinimas)

	public int deleteCandidatesByPartyId(int partyId) {
		return em.createQuery("DELETE from CandidateEntity c WHERE c.partyId = :partyId")
				.setParameter("partyId", partyId).executeUpdate();

	}
	

	public  int  deleteCandidatesByCountyId( Long countyId) {
		return em.createQuery("DELETE from CandidateEntity c WHERE c.countyId = :countyId")
				.setParameter("countyId", countyId).executeUpdate();
	}

	public CandidateEntity delete(Long id) {
		CandidateEntity c = em.find(CandidateEntity.class, id);
		em.remove(c);
		return c;
	}
	
	


}
