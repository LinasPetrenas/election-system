package lt.itakademija.results.single;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.itakademija.single.votes.SingleVotesEntity;
import lt.itakademija.single.votes.SingleVotesRepository;

@RestController
public class ResultsSingleController {

	@Autowired
	private ResultsSingleService service;
	@Autowired
	private ResultsSingleRepository repository;

	@GetMapping("results/single/all")
	public Iterable<SingleVotesEntity> allVotes() {
		return service.findAll();
	}

	@GetMapping("results/single/districtAktivity/{districtId}")
	public double districtActivity(@PathVariable Long districtId) {
		return service.districtActivity(districtId);
	}

	@GetMapping("results/single/districtAktivityRate/{districtId}")
	public double districtActivityRate(@PathVariable Long districtId) {
		return service.districtActivityRate(districtId);
	}

	// NEW
	@GetMapping("results/single/districtVotesCandidate/{districtId}/{personCode}")
	public double districtActivityRate(@PathVariable Long districtId, @PathVariable String personCode) {
		return service.districtCandidateVotes(districtId, personCode);
	}

	@GetMapping("results/single/districtCandidateVotesRate/{districtId}/{personCode}")
	public double districtCandidateVotesRate(@PathVariable Long districtId, @PathVariable String personCode) {
		return service.districtCandidateVotesRate(districtId, personCode);
	}

	@GetMapping("results/single/districtCandidateVotesRateValid/{districtId}/{personCode}")
	public double districtCandidateVotesRateValid(@PathVariable Long districtId, @PathVariable String personCode) {
		return service.districtCandidateVotesRateValid(districtId, personCode);
	}

	@GetMapping("results/single/countyVotesCandidate/{countyId}/{personCode}")
	public double countyVotesCandidate(@PathVariable Long countyId, @PathVariable String personCode) {
		return service.countyCandidateVotes(countyId, personCode);
	}

	@GetMapping("results/single/countyVotesCandidateRate/{countyId}/{personCode}")
	public double countyVotesCandidateRate(@PathVariable Long countyId, @PathVariable String personCode) {
		return service.countyCandidateVotesRate(countyId, personCode);
	}

	@GetMapping("results/single/countyVotesCandidateRateValid/{countyId}/{personCode}")
	public double countyVotesCandidateRateValid(@PathVariable Long countyId, @PathVariable String personCode) {
		return service.countyCandidateVotesRateValid(countyId, personCode);
	}

	@GetMapping("results/single/countyElectors/{countyId}")
	public double countyElectors(@PathVariable Long countyId) {
		return service.countyElectors(countyId);
	}

	@GetMapping("results/single/countyActivity/{countyId}")
	public double countyActivity(@PathVariable Long countyId) {
		return service.countyActivity(countyId);
	}

	@GetMapping("results/single/countyActivityRate/{countyId}")
	public double countyActivityRate(@PathVariable Long countyId) {
		return service.countyActivityRate(countyId);
	}

	@GetMapping("results/single/countyVotesCorrupt/{countyId}")
	public double countyVotesCorrupt(@PathVariable Long countyId) {
		return service.countyVotesCorrupt(countyId);
	}

	@GetMapping("results/single/countyDistrictsNumber/{countyId}")
	public double countyDistrictsNumber(@PathVariable Long countyId) {
		return service.countyDistrictsNumber(countyId);
	}

	@GetMapping("results/single/countyDistrictsSendVoteNumber/{countyId}")
	public double countyDistrictsSendVotesNumber(@PathVariable Long countyId) {
		return service.countyDistrictsSendVotes(countyId);
	}

	// FR7
	@GetMapping("results/single/winner/{countyId}")
	public SingleVotesSum districtWinner(@PathVariable Long countyId) {
		
		
		return service.countyWinner(countyId);
	}

	@GetMapping("results/single/winner/test")
	public List<SingleVotesSum> districtWinnerTest(@PathVariable Long countyId) {
		return service.countyWinnerTest(countyId);
	}

	// VIENMANDATININKAI
	@GetMapping("results/single/winner/all")
	public List<SingleWinnersAll> allCountyWinners() {
		return service.singleWinnersInCountyAll();
	}

	// Grazina apygardoje laimejusiu kandidatu sarasa plius konvertuoja vardus
	// is asmens kodo ir pan.
	@GetMapping("results/single/countyWinners")
	public Iterable<SingleCountyWinners> countyWinners() {
		return service.allCountyWinners();
	}

	// CSV eksportavimas. Vienmandate
	@PostMapping("results/single/winner/csv")
	public void downloadCSV(HttpServletResponse response) throws IOException {

		response.setContentType("text/csv");
		response.setCharacterEncoding("Windows-1257");
		//response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=CSV_Single_Result.csv");

		List<SingleCountyWinners> allCountyWinners = repository.allCountyWinners();

		String countyName = "";
		String firstName = "";
		String lastName = "";
		double countyWinnerPercents = 0.0;
		String partyName = "";

		PrintWriter writer = response.getWriter();
		writer.append("Vardas,Pavardė,Iškėlė,Apygarda,% balsų");
		writer.append("\n");

		for (SingleCountyWinners s : allCountyWinners) {

			partyName = s.getPartyName();
			countyName = s.getCountyName();
			firstName = s.getFirstName();
			lastName = s.getLastName();
			countyWinnerPercents = s.getCountyWinnerPercents();

			writer.append(
					"" + firstName + "," + lastName + "," + partyName + "," + countyName + "," + countyWinnerPercents);
			writer.append("\n");
		}

		response.getWriter().flush();

	}
	
	// CSV eksportavimas. Galutinis partiju mandatu skaicius
		@PostMapping("results/finalPartyMandateList/csv")
		public void downloadCSVFinalPartyMandateList(HttpServletResponse response) throws IOException {

			response.setContentType("text/csv");
			response.setCharacterEncoding("Windows-1257");
			//response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=CSV_FinalPartiesMandate_List.csv");

			List<FinalPartyMandateList> list = service.finalMandateList();

			String partyName = "";
			int mandateCount = 0;
			

			PrintWriter writer = response.getWriter();
			writer.append("Partija,Mandatų skaičius");
			writer.append("\n");

			for (FinalPartyMandateList s : list) {

				partyName = s.getPartyName();
				mandateCount = s.getMandateCount();
				

				writer.append(
						"" + partyName + "," + mandateCount + "" );
				writer.append("\n");
			}

			response.getWriter().flush();

		}
	
	

	
	@GetMapping("/api/formParlamentMembers")
	public List<ParlamentMemeber>  createOrUpdateCandidate() {
		return service.getParlamentMembers();
	}
	
	@GetMapping("/api/finalMandateList")
	public List<FinalPartyMandateList>  finalMandateList() {
		return service.finalMandateList();
		
	}
	
	
	

	@GetMapping("/api/singleFinalList")
	public List<SingleCountyWinners>  singleCountyWinnersAll() {
		return repository.singleCountyWinnersAll();
		
	}

}
