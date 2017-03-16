package lt.itakademija.results.single;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.net.SyslogOutputStream;
import lt.itakademija.candidateCRUD.CandidateEntity;
import lt.itakademija.candidateCRUD.CandidateRepository;
import lt.itakademija.countyCRUD.CountyEntity;
import lt.itakademija.countyCRUD.CountyRepository;
import lt.itakademija.districtCRUD.DistrictEntity;
import lt.itakademija.districtCRUD.DistrictRepository;
import lt.itakademija.party.PartyEntity;
import lt.itakademija.party.PartyRepository;
import lt.itakademija.results.multi.ResultsMultiRepository;
import lt.itakademija.results.multi.WinnersWinners;
import lt.itakademija.single.votes.SingleVotesEntity;
import lt.itakademija.single.votes.SingleVotesRepository;
import lt.itakademija.singleCorrupt.SingleVotesCorruptEntity;
import lt.itakademija.singleCorrupt.SingleVotesCorruptRepo;

@Service
public class ResultsSingleService {

	@Autowired
	private ResultsSingleRepository repository;
	@Autowired
	private DistrictRepository repositoryDistrict;
	@Autowired
	private SingleVotesCorruptRepo repositorySingleCorrupt;
	@Autowired
	private SingleVotesRepository repositorySingleVotes;
	@Autowired
	private CountyRepository repositoryCounty;
	@Autowired
	private CandidateRepository repositoryCandidate;
	@Autowired
	private PartyRepository repositoryParty;
	@Autowired
	private ResultsMultiRepository resultsMultiRepository;

	@Transactional(readOnly = true)
	public List<SingleVotesEntity> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public int districtActivity(Long districtId) {
		int totalVotesCorrupt = repositorySingleCorrupt.findSingleVotesCorruptByDistrict(districtId).stream()
				.mapToInt(o -> o.getVotesSingleCorrupt()).sum();
		int activity = repository.districtActivity(districtId).stream().mapToInt(o -> o.getVotesSingleCandidate())
				.sum();
		return (activity + totalVotesCorrupt);
	}

	@Transactional(readOnly = true)
	public double districtActivityRate(Long districtId) {
		DistrictEntity district = repositoryDistrict.findDistrictById(districtId);
		double totalVotes = district.getDistrictElectors();
		double totalVotesCorrupt = repositorySingleCorrupt.findSingleVotesCorruptByDistrict(districtId).stream()
				.mapToInt(o -> o.getVotesSingleCorrupt()).sum();
		double activity = repository.districtActivity(districtId).stream().mapToInt(o -> o.getVotesSingleCandidate())
				.sum();
		double activityRate = ((activity + totalVotesCorrupt) / totalVotes) * 100;
		return activityRate;
	}

	// NEW
	@Transactional(readOnly = true)
	public double districtCandidateVotes(Long districtId, String personCode) {
		return repository.districtCandidateVotes(districtId, personCode).stream()
				.mapToInt(o -> o.getVotesSingleCandidate()).sum();
	}

	@Transactional(readOnly = true)
	public double districtCandidateVotesRateValid(Long districtId, String personCode) {
		double totalVotesCandidate = this.districtCandidateVotes(districtId, personCode);
		double totalDistrictVotes = repository.districtActivity(districtId).stream()
				.mapToInt(o -> o.getVotesSingleCandidate()).sum();
		return (totalVotesCandidate / totalDistrictVotes) * 100;
	}

	@Transactional(readOnly = true)
	public double districtCandidateVotesRate(Long districtId, String personCode) {
		double totalVotesCorrupt = repositorySingleCorrupt.findSingleVotesCorruptByDistrict(districtId).stream()
				.mapToInt(o -> o.getVotesSingleCorrupt()).sum();
		double totalVotesCandidate = this.districtCandidateVotes(districtId, personCode);
		double totalDistrictVotes = repository.districtActivity(districtId).stream()
				.mapToInt(o -> o.getVotesSingleCandidate()).sum();
		return (totalVotesCandidate / (totalDistrictVotes + totalVotesCorrupt)) * 100;
	}

	@Transactional(readOnly = true)
	public double countyCandidateVotes(Long countyId, String personCode) {
		return repository.countyVotesByPersonCode(countyId, personCode).stream()
				.mapToInt(o -> o.getVotesSingleCandidate()).sum();
	}

	@Transactional(readOnly = true)
	public double countyCandidateVotesRate(Long countyId, String personCode) {
		double countyVotesCandidate = repository.countyVotesByPersonCode(countyId, personCode).stream()
				.mapToInt(o -> o.getVotesSingleCandidate()).sum();
		double countyVotesTotal = repository.countyVotesByCountyId(countyId).stream()
				.mapToInt(o -> o.getVotesSingleCandidate()).sum();
		double countyVotesCorrupt = repository.countyVotesCorruptByCountyId(countyId).stream()
				.mapToInt(o -> o.getVotesSingleCorrupt()).sum();
		return (countyVotesCandidate / (countyVotesTotal + countyVotesCorrupt)) * 100;
	}

	@Transactional(readOnly = true)
	public double countyCandidateVotesRateValid(Long countyId, String personCode) {
		double countyVotesCandidate = repository.countyVotesByPersonCode(countyId, personCode).stream()
				.mapToInt(o -> o.getVotesSingleCandidate()).sum();
		double countyVotesTotal = repository.countyVotesByCountyId(countyId).stream()
				.mapToInt(o -> o.getVotesSingleCandidate()).sum();
		return (countyVotesCandidate / countyVotesTotal) * 100;
	}

	@Transactional(readOnly = true)
	public int countyElectors(Long countyId) {
		return repositoryDistrict.findAllCountyDistricts(countyId).stream().mapToInt(o -> o.getDistrictElectors())
				.sum();
	}

	@Transactional(readOnly = true)
	public int countyActivity(Long countyId) {
		int countyVotesCorrupt = repository.countyVotesCorruptByCountyId(countyId).stream()
				.mapToInt(o -> o.getVotesSingleCorrupt()).sum();
		int activity = repository.countyVotesByCountyId(countyId).stream().mapToInt(o -> o.getVotesSingleCandidate())
				.sum();
		return (activity + countyVotesCorrupt);
	}

	@Transactional(readOnly = true)
	public double countyActivityRate(Long countyId) {
		double activity = this.countyActivity(countyId);
		double electors = this.countyElectors(countyId);
		return (activity / electors) * 100;
	}

	@Transactional(readOnly = true)
	public int countyVotesCorrupt(Long countyId) {
		return repository.countyVotesCorruptByCountyId(countyId).stream().mapToInt(o -> o.getVotesSingleCorrupt())
				.sum();
	}

	@Transactional(readOnly = true)
	public int countyDistrictsNumber(Long countyId) {
		return repositoryDistrict.findAllCountyDistricts(countyId).size();
	}

	@Transactional(readOnly = true)
	public int countyDistrictsSendVotes(Long countyId) {
		return repository.countyVotesCorruptByCountyId(countyId).size();
	}

	// FR7
	/*
	 * Jeigu abu kandidatai gavo vienodą balsų skaičių, Seimo nariu tampa tas
	 * kandidatas, kuris pirmą kartą balsuojant buvo gavęs daugiau balsų. Jeigu
	 * abu kandidatai pirmą kartą balsuojant buvo gavę vienodą balsų skaičių,
	 * Seimo nariu tampama burtais.
	 */
	/*
	 * Here comparator.compare(a, b) >= 0 ? a : b you can see that when 2
	 * elements are equal, i.e. compare returns 0, then the first element is
	 * returned.
	 */
	@Transactional(readOnly = true)
	public SingleVotesSum countyWinner(long countyId) {
		List<SingleVotesEntity> votesListInDistrict = repository.countyVotesByCountyId(countyId);
		List<SingleVotesSum> sumVotesListInDistrict = new ArrayList<>();

		for (SingleVotesEntity votesEntity : votesListInDistrict) {
			if (!sumVotesListInDistrict.stream().filter(o -> o.getPersonCode().equals(votesEntity.getPersonCode()))
					.findFirst().isPresent()) {
				String personCode = votesEntity.getPersonCode();
				int sumOfVotes = (int) this.countyCandidateVotes(countyId, personCode);
				SingleVotesSum newEntity = new SingleVotesSum();
				newEntity.setPersonCode(personCode);
				newEntity.setCountyId(countyId);
				newEntity.setVotesSingleCandidate(sumOfVotes);
				sumVotesListInDistrict.add(newEntity);
			}
		}
		repository.saveSingleVotesSumInCounty(sumVotesListInDistrict);
		return sumVotesListInDistrict.stream().max(Comparator.comparing(SingleVotesSum::getVotesSingleCandidate)).get();
	}

	@Transactional(readOnly = true)
	public List<SingleVotesSum> countyWinnerTest(long countyId) {
		return repository.findAllSingleVoteSum(countyId);
	}

	// VIENMANDATININKAI
	@Transactional(readOnly = true)
	public List<SingleWinnersAll> singleWinnersInCountyAll() {
		List<SingleWinnersAll> singleWinnersAll = new ArrayList<>();
		List<CountyEntity> allCounties = repositoryCounty.findAll();
		for (CountyEntity county : allCounties) {
			SingleVotesSum countyWinner = this.countyWinner(county.getCountyId());
			SingleWinnersAll newEntity = new SingleWinnersAll();
			newEntity.setPersonCode(countyWinner.getPersonCode());
			newEntity.setPersonName(repositoryCandidate.findCandidate(countyWinner.getPersonCode()).getFirstName());
			newEntity.setPersonSurname(repositoryCandidate.findCandidate(countyWinner.getPersonCode()).getLastName());
			newEntity.setCountyId(county.getCountyId());
			newEntity.setCountyName(repositoryCounty.findCounty(county.getCountyId()).getCountyName());
			newEntity.setVotesSingleCandidate(countyWinner.getVotesSingleCandidate());
			newEntity.setPartyId(repositoryCandidate.findCandidate(countyWinner.getPersonCode()).getPartyId());
			newEntity.setPartyName(repositoryParty.findPartyById((long) newEntity.getPartyId()).getPartyName());
			singleWinnersAll.add(newEntity);
		}
		repository.saveSingleVotesWinnersAll(singleWinnersAll);
		return singleWinnersAll;
	}

	// Grazina apygardoje laimejusiu kandidatu sarasa plius konvertuoja vardus
	// is asmens kodo ir pan.
	@Transactional(readOnly = true)
	public List<SingleCountyWinners> allCountyWinners() {
		return repository.allCountyWinners();
	}

	

	public List<ParlamentMemeber> getParlamentMembers() {

		List<WinnersWinners> winnersMulti = resultsMultiRepository.returnRealWinnersMulti();
		List<SingleWinnersAll> winnersSingle = repository.SingleWinnersAll();

		List<ParlamentMemeber> parlamentMembers = new ArrayList<>();

		String fullName = "";
		String partyName = "";

		for (WinnersWinners w : winnersMulti) {

			parlamentMembers.add(new ParlamentMemeber(w.getPartyId().intValue(),
					w.getPersonSurname() + " " + w.getPersonName(), w.getPartyName()));

		}

		for (SingleWinnersAll s : winnersSingle) {
			fullName = s.getPersonSurname() + " " + s.getPersonName();
			partyName = s.getPartyName();

			parlamentMembers.add(new ParlamentMemeber(s.getPartyId(), fullName, partyName));
		}

		// Rusiuoja pagal pavarde ir varda
		Collections.sort(parlamentMembers, new Comparator<ParlamentMemeber>() {
			public int compare(ParlamentMemeber one, ParlamentMemeber other) {
				return one.getFullName().compareTo(other.getFullName());
			}
		});

		return parlamentMembers;
	}

	public List<FinalPartyMandateList> finalMandateList() {

		int mandateCount = 0;
		String partyName = "";

		List<FinalPartyMandateList> finalPartyMandateList = new ArrayList<>();
		List<PartyEntity> partyList = repositoryParty.findAll();
		List<ParlamentMemeber> mpList = getParlamentMembers();

		for (PartyEntity p : partyList) {
			if (p.getPartyId() != 0) {
				partyName = p.getPartyName();

				for (ParlamentMemeber mp : mpList) {

					if (mp.getPartyId() == p.getPartyId()) {
						mandateCount++;
					}
				}
				if (mandateCount > 0) {
					finalPartyMandateList.add(new FinalPartyMandateList(partyName, mandateCount));
					mandateCount = 0;
				}

			}

		}

		return finalPartyMandateList;
	}

}
