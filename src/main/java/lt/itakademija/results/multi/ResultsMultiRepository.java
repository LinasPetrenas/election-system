package lt.itakademija.results.multi;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lt.itakademija.candidateCRUD.CandidateEntity;
import lt.itakademija.countyCRUD.CountyEntity;
import lt.itakademija.multi.votes.MultiVotesEntity;
import lt.itakademija.multiCorrupt.MultiVotesCorruptEntity;
import lt.itakademija.single.votes.SingleVotesEntity;

@Repository
public class ResultsMultiRepository {

	@Autowired
	private EntityManager em;
	
	public List<FinalPartyResult> findAllFinalPartyResult() {
		return em.createQuery("SELECT m from FinalPartyResult m").getResultList();
	}
	
	public int findFinalPartyResultByPartyName(String partyName) {
		return em.createQuery("SELECT m from FinalPartyResult m WHERE partyName=:partyName").setParameter("partyName", partyName).getFirstResult();
	}
	
	
	
	public List<FinalPartyResult> createFinalMultiResultList(List<FinalPartyResult> list) {
		List<FinalPartyResult> multiList = list;
		
		for(FinalPartyResult f: multiList){
			
//			if(findFinalPartyResultByPartyName(f.getPartyName()) == 0){
				em.persist(f);
				
//			}else{
//				FinalPartyResult merged = em.merge(f);
//				em.persist(merged);
//			}
					
		}
		
		return em.createQuery("SELECT m from FinalPartyResult m").getResultList();
	}
	

	public List<MultiVotesEntity> findAll() {
		return em.createQuery("SELECT m from MultiVotesEntity m").getResultList();
	}

	public List<MultiVotesEntity> findMultiVotesByDistrict(Long districtId) {
		return em.createQuery("SELECT m from MultiVotesEntity m WHERE m.districtId = :districtId")
				.setParameter("districtId", districtId).getResultList();
	}

	public List <MultiVotesEntity> multiVotesByCountyAndParty(Long countyId, Long partyId){
		return em.createQuery("SELECT m from MultiVotesEntity m WHERE m.countyId = :countyId and m.partyId=:partyId")
				.setParameter("countyId", countyId).setParameter("partyId", partyId).getResultList();
	}
	
	public List<MultiVotesCorruptEntity> multiVotesCorruptByCounty(Long countyId) {
		return em.createQuery("SELECT m from MultiVotesCorruptEntity m WHERE m.countyId = :countyId")
				.setParameter("countyId", countyId).getResultList();
	}

	public List<MultiVotesEntity> multiVotesByCounty(Long countyId) {
		return em.createQuery("SELECT m from MultiVotesEntity m WHERE m.countyId = :countyId")
				.setParameter("countyId", countyId).getResultList();
	}

	// MANDATE
	public Long multiVotesPartyTotal(Long partyId) {
		return em.createQuery("SELECT SUM(m.votesMultiParty) from MultiVotesEntity m WHERE m.partyId = :partyId",Long.class)
				.setParameter("partyId", partyId).getSingleResult();
	}

	// MANDATES PARTY
	public int mandatesByParty(Long partyId) {
		
		return em.createQuery("SELECT m.madates from MultiVotesMandates m WHERE m.partyId = :partyId")
				.setParameter("partyId", partyId).getFirstResult();
		
//		return em.createQuery("SELECT m from MultiVotesMandates m WHERE m.partyId = :partyId")
//				.setParameter("partyId", partyId).getResultList();
	}

	// MANDATES
	public List<MultiVotesMandates> findAllMandates() {
		return em.createQuery("SELECT s from MultiVotesMandates s").getResultList();
	}

	// MANDATES SAVE
	public List<MultiVotesMandates> saveAll(List<MultiVotesMandates> s) {
		boolean oldTableExists = false;
		for (MultiVotesMandates mandateEntity : s) {
			if (findAllMandates().stream().filter(o -> o.getPartyId().equals(mandateEntity.getPartyId())).findFirst()
					.isPresent()) {
				oldTableExists = true;
			}
		}

		if (!oldTableExists) {
			for (MultiVotesMandates mandateEntity1 : s) {
				em.persist(mandateEntity1);
			}
		} else {
			em.createQuery("delete from MultiVotesMandates m").executeUpdate();
			for (MultiVotesMandates mandateEntity1 : s) {
				em.persist(mandateEntity1);
			}
		}

		/*
		 * for (MultiVotesMandates mandateEntity:s){
		 * 
		 * if (!findAllMandates().stream().filter(o->o.getPartyId().equals(
		 * mandateEntity.getPartyId())).findFirst().isPresent()) {
		 * em.persist(mandateEntity); } else{
		 * em.createQuery("delete from MultiVotesMandates m").executeUpdate();
		 * em.persist(mandateEntity);
		 */

		/*
		 * em.
		 * createQuery("update MultiVotesMandates m set votesMultiParty = :votesMultiParty, remaining = :remaining, mandates = :mandates where m.partyId = :partyId"
		 * ) .setParameter("votesMultiParty",
		 * mandateEntity.getVotesMultiParty()) .setParameter("remaining",
		 * mandateEntity.getRemaining()) .setParameter("mandates",
		 * mandateEntity.getMandates()) .setParameter("partyId",
		 * mandateEntity.getPartyId()) .executeUpdate(); } }
		 */

		return em.createQuery("SELECT s from MultiVotesMandates s").getResultList();
	}
	
	
	public List<CandidateEntity> candidatesByPartyId(int partyId) {
		return em.createQuery("SELECT s from CandidateEntity s where s.partyId = :partyId")
				.setParameter("partyId", partyId).getResultList();
	}
	
	//KONSOLIDUOTAS BE SINGLE SAVE
	public List<WinnersWinners> saveRealWinnersMulti(List<WinnersWinners> s) {
		if (!(this.returnRealWinnersMulti().size()>0)) {
			for (WinnersWinners entity : s) {
				em.persist(entity);
			}
		} else {
			em.createQuery("delete from WinnersWinners m").executeUpdate();
			for (WinnersWinners entity : s) {
				em.persist(entity);
			}
		}
		return em.createQuery("SELECT s from WinnersWinners s").getResultList();
	}
	//KONSOLIDUOTAS BE SINGLE RETURN
	public List<WinnersWinners> returnRealWinnersMulti(){
		return em.createQuery("SELECT s from WinnersWinners s").getResultList();
	}

}