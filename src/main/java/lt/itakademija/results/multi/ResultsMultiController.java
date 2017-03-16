package lt.itakademija.results.multi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.itakademija.multi.votes.MultiVotesEntity;
import lt.itakademija.party.PartyRepository;
import lt.itakademija.results.single.ParlamentMemeber;
import lt.itakademija.results.single.ResultsSingleService;

@RestController
public class ResultsMultiController {

	@Autowired
	private ResultsMultiService service;
	@Autowired
	private ResultsSingleService serviceResultSingle;
	@Autowired
	private ResultsMultiRepository repository;
	@Autowired
	private PartyRepository repositoryParty;

	
	
	
	@GetMapping("results/multi/all")
	public Iterable<MultiVotesEntity> allVotes() {
		return service.findAll();
	}

	@GetMapping("results/multi/activity/{districtId}")
	public int districtActivity(@PathVariable Long districtId) {
		return service.districtActivity(districtId);
	}

	@GetMapping("results/multi/activityrate/{districtId}")
	public double districtActivityRate(@PathVariable Long districtId) {
		return service.districtActivityRate(districtId);
	}

	// new
	@GetMapping("results/multi/partyvotesindistrict/{districtId}/{partyId}")
	public double districtPartyVotes(@PathVariable Long districtId, @PathVariable Long partyId) {
		return service.districtVotesparty(districtId, partyId);
	}

	@GetMapping("results/multi/partyvotesrate/{districtId}/{partyId}")
	public double districtpartyVotesRate(@PathVariable Long districtId, @PathVariable Long partyId) {
		return service.districtPartyVotesRate(districtId, partyId);
	}

	@GetMapping("results/multi/partyvotesratevalid/{districtId}/{partyId}")
	public double districtPartyVotesRateValid(@PathVariable Long districtId, @PathVariable Long partyId) {
		return service.districtPartyVotesRateValid(districtId, partyId);
	}

	@GetMapping("results/multi/countypartyvotes/{countyId}/{partyId}")
	public double countyPartyVotes(@PathVariable Long countyId, @PathVariable Long partyId) {
		return service.countyPartyVotes(countyId, partyId);
	}

	@GetMapping("results/multi/countypartyvotesrate/{countyId}/{partyId}")
	public double countyPartyVotesRate(@PathVariable Long countyId, @PathVariable Long partyId) {
		return service.countyPartyVotesRate(countyId, partyId);
	}

	@GetMapping("results/multi/countypartyvotesratevalid/{countyId}/{partyId}")
	public double countyPartyVotesRateValid(@PathVariable Long countyId, @PathVariable Long partyId) {
		return service.countyPartyVotesRateValid(countyId, partyId);
	}

	@GetMapping("results/multi/countyelectors/{countyId}")
	public double countyElectors(@PathVariable Long countyId) {
		return service.countyElectors(countyId);
	}

	@GetMapping("results/multi/countyactivity/{countyId}")
	public double countyActivity(@PathVariable Long countyId) {
		return service.countyActivity(countyId);
	}

	@GetMapping("results/multi/countyactivityrate/{countyId}")
	public double countyActivityRate(@PathVariable Long countyId) {
		return service.countyActivityRate(countyId);
	}

	@GetMapping("results/multi/countyvotescorrupt/{countyId}")
	public double countyVotesCorrupt(@PathVariable Long countyId) {
		return service.countyVotesCorrupt(countyId);
	}

	@GetMapping("results/multi/countydistrictnumber/{countyId}")
	public double countyDistrictnumber(@PathVariable Long countyId) {
		return service.countyDistrictNumber(countyId);
	}

	@GetMapping("results/multi/countydistrictsenddata/{countyId}")
	public double countyDistrictSendData(@PathVariable Long countyId) {
		return service.countyDistrictsSendData(countyId);
	}

	// MANDATES VISOS PARTIJOS
	@GetMapping("results/multi/mandates")
	public List<MultiVotesMandates> mandatesAll() {
		return service.mandatesList();
	}

	// MANDATES SKAICIUS PAGAL PARTIJA
	@GetMapping("results/multi/mandatesbyparty/{partyId}")
	public int mandatesByParty(@PathVariable Long partyId) {
		
		return service.mandatesListbyPartyALL(partyId);
	}

	// KONSOLIDUOTAS
	@GetMapping("results/multi/winnerswinners")
	public List<WinnersWinners> winnerswinners() {
		return service.winnersTakesItAll();
	}

	// NEW VISU APYGARDU REZULTATAS PER PARTIJA
	@GetMapping("results/multi/allcountyvotes/{partyId}")
	public int allCountyVotesByParty(@PathVariable Long partyId) {
		return service.allCountyVotesByParty(partyId);
	}

	// NEW VISU APYGARDU PROC NUO DALYVAVUSIU
	@GetMapping("results/multi/allcountyvotesrate/{partyId}")
	public double allCountyRateFromWholeByParty(@PathVariable Long partyId) {
		return service.allCountyRateFromWholeByParty(partyId);
	}
	
	
	@GetMapping("results/multi/allPartyWinners")
	public List<FinalPartyResult> allPartyWinners() {
		
		return service.allPartyWinners();
	}
	
	
	
	@GetMapping("results/multi/allFinalPartyResult")
	public List<FinalPartyResult> findAllFinalPartyResult() {
		
		return service.findAllFinalPartyResult();
	}
	
	
	
	// CSV eksportavimas. Daugiamandate
		@PostMapping("results/multi/winner/csv")
		public void downloadCSVmulti(HttpServletResponse response) throws IOException {
			response.setContentType("text/csv");
			response.setCharacterEncoding("Windows-1257");
			//response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=CSV_Multi_Result.csv");


			List<MultiVotesMandates> allPartiesWithMandates = repository.findAllMandates();

			String partyName = "";
			int mandates = 0;
			int totalVotes = 0;
			double totalPercent = 0.0;
			

			PrintWriter writer = response.getWriter();
			writer.append("Partija,Iš viso,% Balsų,Mandatai");
			writer.append("\n");

			for (MultiVotesMandates s : allPartiesWithMandates) {

				partyName = repositoryParty.findPartyById(s.getPartyId()).getPartyName();
				totalVotes = allCountyVotesByParty(s.getPartyId());
				totalPercent = allCountyRateFromWholeByParty(s.getPartyId());
				mandates = service.mandatesListbyPartyALL(s.getPartyId());
				

				writer.append("" + partyName + "," + totalVotes + "," + totalPercent + "," + mandates + "");
				writer.append("\n");
			}

			response.getWriter().flush();
		}
		
		// CSV eksportavimas. seimo nariai
				@PostMapping("results/mp/csv")
				public void downloadCSVmp(HttpServletResponse response) throws IOException {
					response.setContentType("text/csv");
					response.setCharacterEncoding("Windows-1257");
					//response.setCharacterEncoding("UTF-8");
					response.setHeader("Content-disposition", "attachment;filename=CSV_ParlamentMember_List.csv");


					List<ParlamentMemeber> parlamentMemberList = serviceResultSingle.getParlamentMembers();

					String partyName = "";
					String lastName = "";
					String firstName = "";
					
					

					PrintWriter writer = response.getWriter();
					writer.append("Vardas,Pavardė,Partija");
					writer.append("\n");

					for (ParlamentMemeber s : parlamentMemberList) {

						String arr[] = s.getFullName().split("\\s+");
						lastName =arr[0];
						firstName=arr[1];
								partyName = s.getPartyName();

						writer.append(firstName+"," + lastName  + "," + partyName);
						writer.append("\n");
					}

					response.getWriter().flush();
				}
}
