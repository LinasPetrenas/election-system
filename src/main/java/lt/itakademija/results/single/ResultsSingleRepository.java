package lt.itakademija.results.single;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lt.itakademija.candidateCRUD.CandidateEntity;
import lt.itakademija.candidateCRUD.CandidateRepository;
import lt.itakademija.countyCRUD.CountyRepository;
import lt.itakademija.party.PartyRepository;
import lt.itakademija.single.votes.SingleVotesEntity;
import lt.itakademija.singleCorrupt.SingleVotesCorruptEntity;

@Repository
public class ResultsSingleRepository {

	@Autowired
	private EntityManager em;
	@Autowired
	private CountyRepository countyRepo;
	@Autowired
	private CandidateRepository candidateRepo;
	@Autowired
	private PartyRepository partyRepo;
	@Autowired
	private ResultsSingleService singleResultService;

	public List<SingleVotesEntity> findAll() {
		return em.createQuery("SELECT s from SingleVotesEntity s").getResultList();
	}

	// NEW
	public List<SingleVotesEntity> districtCandidateVotes(Long districtId, String personCode) {
		return em
				.createQuery(
						"SELECT s from SingleVotesEntity s WHERE s.districtId = :districtId and s.personCode = :personCode")
				.setParameter("districtId", districtId).setParameter("personCode", personCode).getResultList();
	}

	public List<SingleVotesEntity> districtActivity(Long districtId) {
		return em.createQuery("SELECT s from SingleVotesEntity s WHERE s.districtId = :districtId")
				.setParameter("districtId", districtId).getResultList();
	}

	public List<SingleVotesEntity> countyVotesByPersonCode(Long countyId, String personCode) {
		return em
				.createQuery(
						"SELECT s from SingleVotesEntity s WHERE s.countyId = :countyId and s.personCode = :personCode")
				.setParameter("countyId", countyId).setParameter("personCode", personCode).getResultList();
	}

	public List<SingleVotesEntity> countyVotesByCountyId(Long countyId) {
		return em.createQuery("SELECT s from SingleVotesEntity s WHERE s.countyId = :countyId")
				.setParameter("countyId", countyId).getResultList();
	}

	public List<SingleVotesCorruptEntity> countyVotesCorruptByCountyId(Long countyId) {
		return em.createQuery("SELECT s from SingleVotesCorruptEntity s WHERE s.countyId = :countyId")
				.setParameter("countyId", countyId).getResultList();
	}

	// SINGLE VOTES SUM RADIMAS
	public List<SingleVotesSum> findAllSingleVoteSum(Long countyId) {
		return em.createQuery("SELECT s from SingleVotesSum s WHERE s.countyId = :countyId")
				.setParameter("countyId", countyId).getResultList();
	}

	// SINGLE VOTES SUM SAUGOJIMAS
	@SuppressWarnings("unchecked")
	public List<SingleVotesSum> saveSingleVotesSumInCounty(List<SingleVotesSum> s) {
		for (SingleVotesSum singleEntitySum : s) {
			if (!this.findAllSingleVoteSum(singleEntitySum.getCountyId()).stream()
					.filter(o -> o.getPersonCode().equals(singleEntitySum.getPersonCode())).findFirst().isPresent()) {
				em.persist(singleEntitySum);
			} else {
				em.createQuery(
						"update SingleVotesSum s set votesSingleCandidate = :newVotesSum WHERE s.countyId = :countyId and s.personCode = :personCode")
						.setParameter("countyId", singleEntitySum.getCountyId())
						.setParameter("newVotesSum", singleEntitySum.getVotesSingleCandidate())
						.setParameter("personCode", singleEntitySum.getPersonCode()).executeUpdate();

				// .setParameter("countyId", singleEntitySum.getCountyId())

				/*
				 * SingleVotesSum old = (SingleVotesSum) em.
				 * createQuery("SELECT s from SingleVotesSum s WHERE s.countyId = :countyId and s.personCode = :personCode"
				 * ) .setParameter("countyId", singleEntitySum.getCountyId())
				 * .setParameter("personCode",
				 * singleEntitySum.getPersonCode()).getSingleResult();
				 */
				/*
				 * SingleVotesSum old =
				 * this.findAllSingleVoteSum(singleEntitySum.getCountyId())
				 * .stream().filter(o->o.getPersonCode()
				 * .equals(singleEntitySum.getPersonCode())).findFirst().get();
				 */
				// em.getTransaction().begin();
				// old.setVotesSingleCandidate(singleEntitySum.getVotesSingleCandidate());
				// em.getTransaction().commit();

				/*
				 * em.remove(old); System.out.println(old);
				 * //em.persist(singleEntitySum);
				 */ }
		}
		return em.createQuery("SELECT s from SingleVotesSum s").getResultList();
	}

	// VIENMANDATININKU RADIMAS
	public List<SingleWinnersAll> findAllSingleWinners() {
		return em.createQuery("SELECT s from SingleWinnersAll s").getResultList();
	}

	// VIENMANDATININKAI
	@SuppressWarnings("unchecked")
	public List<SingleWinnersAll> saveSingleVotesWinnersAll(List<SingleWinnersAll> s) {
		if (this.findAllSingleWinners().isEmpty()) {
			for (SingleWinnersAll singleWinner : s) {
				em.persist(singleWinner);
			}
		} else {
			em.createQuery("DELETE FROM SingleWinnersAll e").executeUpdate();
			for (SingleWinnersAll singleWinner : s) {
				em.persist(singleWinner);
			}
		}
		return em.createQuery("SELECT s from SingleWinnersAll s").getResultList();
	}

	public List<SingleCountyWinners> findAllSingleCountyWinnersLong() {
		return em.createQuery("SELECT s from SingleCountyWinners s ").getResultList();
	}

	public List<SingleWinnersAll> SingleWinnersAll() {
		return em.createQuery("SELECT s from SingleWinnersAll s ").getResultList();
	}
	
	
	

	// Grazina apygardoje laimejusiu kandidatu sarasa plius konvertuoja vardus
	// is asmens kodo ir pan.
	public List<SingleCountyWinners> allCountyWinners() {

		Query q = em.createNativeQuery("SELECT tt.* FROM SINGLE_VOTES_SUM tt INNER JOIN "
				+ "(SELECT COUNTY_ID , MAX(VOTES_SINGLE_CANDIDATE ) AS MaxVotes FROM SINGLE_VOTES_SUM "
				+ "GROUP BY COUNTY_ID ) groupedtt ON tt.COUNTY_ID = groupedtt.COUNTY_ID "
				+ "AND tt.VOTES_SINGLE_CANDIDATE = groupedtt.MaxVotes", "countyWinners");

		List<SingleVotesSum> list = new ArrayList<>(q.getResultList());
		List<SingleCountyWinners> winners = new ArrayList<>();

	
		String countyName = "";
		String firstName = "";
		String lastName = "";
		String partyName = "";
		double countyWinnerPercents = 0.0;

		for (SingleVotesSum s : list) {

			if (candidateRepo.findCandidate(s.getPersonCode()).getPartyId() == 0) {
				partyName = "Išsikėlė pats";
			} else {
				partyName = partyRepo.findPartyByPersonCode(s.getPersonCode()).getPartyName();
			}

			countyName = countyRepo.findCounty(s.getCountyId()).getCountyName();
			firstName = candidateRepo.findCandidate(s.getPersonCode()).getFirstName();
			lastName = candidateRepo.findCandidate(s.getPersonCode()).getLastName();
			countyWinnerPercents = singleResultService.countyCandidateVotesRate(s.getCountyId(), s.getPersonCode());

			winners.add(new SingleCountyWinners(countyName, firstName, lastName, partyName, countyWinnerPercents));

		}

		// Rusiuoja pagal Apygardos pavadinima
		Collections.sort(winners, new Comparator<SingleCountyWinners>() {
			public int compare(SingleCountyWinners one, SingleCountyWinners other) {
				return one.getCountyName().compareTo(other.getCountyName());
			}
		});

	
		//Test
				
			
			// persistina i duomeny baze	
		saveSingleCountyWinners(winners);
		
		return winners;
	}
	
	public List<SingleCountyWinners> singleCountyWinnersAll() {
		return em.createQuery("SELECT s from SingleCountyWinners s ").getResultList();
	}
	
	public void saveSingleCountyWinners(List<SingleCountyWinners> list) {
		List<SingleCountyWinners> countyWinnersList = list;
		//HashSet<SingleCountyWinners> noDublicate = new HashSet();
		
for(SingleCountyWinners s: countyWinnersList){
	
		
	em.persist(s);
		}
		
		
//		for(SingleCountyWinners s: noDublicate){
//			
//			em.persist(s);
//		}
		
		
		
	
	}
	
}
