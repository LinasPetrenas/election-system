package lt.itakademija.results.multi;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import lt.itakademija.candidateCRUD.CandidateEntity;
import lt.itakademija.districtCRUD.DistrictEntity;
import lt.itakademija.districtCRUD.DistrictRepository;
import lt.itakademija.multi.votes.MultiVotesEntity;
import lt.itakademija.multi.votes.MultiVotesRepository;
import lt.itakademija.multiCorrupt.MultiVotesCorruptRepo;
import lt.itakademija.party.PartyEntity;
import lt.itakademija.party.PartyRepository;
import lt.itakademija.single.votes.SingleVotesEntity;
import lt.itakademija.results.single.*;

@Service
public class ResultsMultiService {

	@Autowired
	private ResultsMultiRepository repository;
	@Autowired
	private MultiVotesCorruptRepo repositoryMultiCorrupt;
	@Autowired
	private DistrictRepository repositoryDistrict;
	@Autowired
	private MultiVotesRepository repositoryMultiVotes;
	@Autowired
	private ResultsSingleService resultsSingleService;
	@Autowired
	private PartyRepository repositoryParty;

	
	
	public List<FinalPartyResult> findAllFinalPartyResult() {
		
		return repository.findAllFinalPartyResult();
	}
	
	
	@Transactional(readOnly = true)
	public List<MultiVotesEntity> findAll() {
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public int districtActivity(Long districtId){
		int totalVotesCorrupt = repositoryMultiCorrupt.findMultiVotesCorruptByDistrict(districtId)
				.stream().mapToInt(o->o.getVotesMultiCorrupt()).sum();
		int activity = repository.findMultiVotesByDistrict(districtId)
				.stream().mapToInt(o->o.getVotesMultiParty()).sum();
		return (activity+totalVotesCorrupt);
	}
	
	@Transactional(readOnly = true)
	public double districtActivityRate(Long districtId){
		double activity = this.districtActivity(districtId);
		double totalVotes = repositoryDistrict.findDistrictById(districtId).getDistrictElectors();
		return (activity/totalVotes)*100;
	}
	
	@Transactional(readOnly = true)
	public double districtVotesparty (Long districtId, Long partyId){
		return repositoryMultiVotes.findMultiVotesByPartyIdInDistrict(partyId, districtId)
				.stream().mapToInt(o->o.getVotesMultiParty()).sum();
	}
	@Transactional(readOnly = true)
	public double districtPartyVotesRate (Long districtId, Long partyId){
		double toalVotesDistrict = this.districtActivity(districtId);
		double votesParty = repositoryMultiVotes.findMultiVotesByPartyIdInDistrict(partyId, districtId)
				.stream().mapToInt(o->o.getVotesMultiParty()).sum();
		return (votesParty/toalVotesDistrict)*100;
	}
	
	@Transactional(readOnly = true)
	public double districtPartyVotesRateValid (Long districtId, Long partyId){
		double toalVotesDistrict = repository.findMultiVotesByDistrict(districtId)
				.stream().mapToInt(o->o.getVotesMultiParty()).sum();
		double votesParty = repositoryMultiVotes.findMultiVotesByPartyIdInDistrict(partyId, districtId)
				.stream().mapToInt(o->o.getVotesMultiParty()).sum();
		return (votesParty/toalVotesDistrict)*100;
	}
	
	@Transactional(readOnly = true)
	public int countyPartyVotes (Long countyId, Long partyId){
		return repository.multiVotesByCountyAndParty(countyId, partyId)
				.stream().mapToInt(o->o.getVotesMultiParty()).sum();
	}
	//NEW VISU APYGARDU REZULTATAS PER PARTIJA
	@Transactional(readOnly = true)
	public int allCountyVotesByParty (Long partyId){
		return repository.multiVotesPartyTotal(partyId).intValue();
	}
	
	//NEW VISU APYGARDU PROC NUO DALYVAVUSIU
	@Transactional(readOnly = true)
	public double allCountyRateFromWholeByParty(Long partyId){
		double votesValidTotal = repositoryMultiVotes.findAll()
				.stream().mapToInt(o->o.getVotesMultiParty()).sum();
		double votesCorruptTotal = repositoryMultiCorrupt.findAll()
				.stream().mapToInt(o->o.getVotesMultiCorrupt()).sum();
		double votesTotal = votesValidTotal+votesCorruptTotal;
		double votesPartyTotal = repository.multiVotesPartyTotal(partyId);
		
		return (votesPartyTotal*100)/votesTotal;
	}
	
	@Transactional(readOnly = true)
	public double countyPartyVotesRate(Long countyId, Long partyId){
		double countyVotesParty = this.countyPartyVotes(countyId, partyId);
		double countyVotesCorrupt = repository.multiVotesCorruptByCounty(countyId)
				.stream().mapToInt(o->o.getVotesMultiCorrupt()).sum();
		double countyVotesTotal = repository.multiVotesByCounty(countyId)
				.stream().mapToInt(o->o.getVotesMultiParty()).sum();
		return (countyVotesParty/(countyVotesCorrupt+countyVotesTotal))*100;
	}
	
	@Transactional(readOnly = true)
	public double countyPartyVotesRateValid(Long countyId, Long partyId){
		double countyVotesParty = this.countyPartyVotes(countyId, partyId);
		double countyVotesTotal = repository.multiVotesByCounty(countyId)
				.stream().mapToInt(o->o.getVotesMultiParty()).sum();
		return (countyVotesParty/countyVotesTotal)*100;
	}
	
	//MANDATAI
	/*Partijos kandidatų sąrašas gali gauti Seimo narių mandatų
	 *  (dalyvauja skirstant mandatus) tik tada, jeigu už jį balsavo ne mažiau kaip 5
	 *   procentai rinkimuose dalyvavusių rinkėjų.
	*/
	
	/*Mandatai skirstomi 5% barjerą peržengusioms partijoms proporcingai
	pagal laimėtų balsų skaičių*/
	
	
	@Transactional(readOnly = true)
	public List <MultiVotesEntity> partiesOverFiveProcents(List <MultiVotesEntity> allMultiVotes){
		List <MultiVotesEntity> partiesOverFiveProcVotes = new ArrayList <MultiVotesEntity>(); 
		
		//ideti queri
		double votesValidTotal =repositoryMultiVotes.getVotesCount().doubleValue() ;
		double votesCorruptTotal = repositoryMultiCorrupt.findAll()
				.stream().mapToInt(o->o.getVotesMultiCorrupt()).sum();
		double votesTotal = votesValidTotal+votesCorruptTotal;
		
		List<PartyEntity> allParties = repositoryParty.findAllParties();
		HashMap<Long, Long> partiesAndVotesTotal = new HashMap<Long,Long>();
		
		for (PartyEntity partyEntity : allParties) {
			partiesAndVotesTotal.put(partyEntity.getPartyId(),repository.multiVotesPartyTotal(partyEntity.getPartyId()));
		}
		
		for (MultiVotesEntity entity:allMultiVotes){
			double votesPartyTotal = partiesAndVotesTotal.get(entity.getPartyId());
//			double votesPartyTotal = repository.multiVotesPartyTotal(entity.getPartyId());
					
			double partyVotesRateFromTotal = (votesPartyTotal*100)/votesTotal;
			if (partyVotesRateFromTotal>=5){
				partiesOverFiveProcVotes.add(entity);
			}
		}
		return partiesOverFiveProcVotes;
	}
	
	/*4. Apskaičiuojama kvota - tai yra, kiek balsų reikia 1 mandatui gauti. 
	Ji lygi rinkėjų balsų, kuriuos gavo sąrašai, dalyvaujantys skirstant mandatus, sumai,
	padalytai iš 70. Jei dalijant gaunama liekana, dalmuo padidinamas vienetu.*/
	
	@Transactional(readOnly = true)
	public int multiQuota (){
		List <MultiVotesEntity> partiesOverFiveProcVotes = this.partiesOverFiveProcents(repositoryMultiVotes.findAll());
		int quota;
		int totalVotesForAllParties = partiesOverFiveProcVotes
				.stream().mapToInt(o->o.getVotesMultiParty()).sum();
		if (totalVotesForAllParties%70 == 0){
			quota = totalVotesForAllParties/70;
		} else{
			quota = totalVotesForAllParties/70+1;
		}
		System.out.println(totalVotesForAllParties);
		System.out.println(quota);
		return quota;
	}
	
/*	5. Už kiekvieną sąrašą paduotų balsų skaičius dalijamas iš kvotos.
	Gautas sveikasis dalmuo yra mandatų skaičius, tenkantis kiekvienam sąrašui pagal kvotą,
	o šio dalijimo liekanos naudojamos likusiems mandatams paskirstyti pagal liekanas.*/
	
	//@SuppressWarnings("null")
	@Transactional(readOnly = true)
	public List <MultiVotesMandates> mandatesList (){
		List<MultiVotesEntity> allMultiVotes = repositoryMultiVotes.findAll();
		List <MultiVotesEntity> partiesOverFiveProcVotes = this.partiesOverFiveProcents(allMultiVotes);
		List <MultiVotesMandates> mandatesList = new ArrayList<>();
		int quota = this.multiQuota();

		for (MultiVotesEntity entity:partiesOverFiveProcVotes){
			if (!mandatesList.stream().filter(o->o.getPartyId().equals(entity.getPartyId())).findFirst().isPresent()){

			int votesPartyTotal = repository.multiVotesPartyTotal(entity.getPartyId()).intValue();
			int remainder = votesPartyTotal%quota;
			int mandates = votesPartyTotal/quota;
			
			MultiVotesMandates mandatesEntities = new MultiVotesMandates();
			mandatesEntities.setPartyId(entity.getPartyId());
			mandatesEntities.setVotesMultiParty(votesPartyTotal);
			mandatesEntities.setRemaining(remainder);
			mandatesEntities.setMandates(mandates);
			mandatesList.add(mandatesEntities);
			}
		}
		
		//This is similar SYNTAX to the Streams above, but it sorts the original list!!
		mandatesList.sort(Comparator.comparing(MultiVotesMandates::getRemaining).thenComparing(MultiVotesMandates::getVotesMultiParty).reversed());
		//mandatesList.sort((o1, o2) -> o2.getRemaining() - o1.getRemaining());
				
		int remainingMandates = 70-mandatesList.stream().mapToInt(o->o.getMandates()).sum();
		while (remainingMandates>0){
			for (MultiVotesMandates entity:mandatesList){
				if (remainingMandates>0){
					int newMandates = entity.getMandates()+1;
					entity.setMandates(newMandates);
					remainingMandates--;
				}
			}
		}
		
		repository.saveAll(mandatesList);
		return mandatesList;
	}
	
	//MANDATAI PARTIJAI
	@Transactional(readOnly = true)
	public int mandatesListbyPartyALL(Long partyId){
	//List <MultiVotesMandates> mandatesList = this.mandatesList();
		
		return repository.mandatesByParty(partyId);
		
//		return repository.mandatesByParty(partyId). 
//				stream().mapToInt(o->o.getMandates()).sum();
	}
	
	@Transactional(readOnly = true)
	public double countyElectors(Long countyId){
		return repositoryDistrict.findAllCountyDistricts(countyId)
				.stream().mapToInt(o->o.getDistrictElectors()).sum();
	}
	
	@Transactional(readOnly = true)
	public int countyActivity(Long countyId){
		int countyVotesCorrupt = repository.multiVotesCorruptByCounty(countyId)
				.stream().mapToInt(o->o.getVotesMultiCorrupt()).sum();
		int countyVotesTotal = repository.multiVotesByCounty(countyId)
				.stream().mapToInt(o->o.getVotesMultiParty()).sum();
		return countyVotesCorrupt+countyVotesTotal;
	}
	
	@Transactional(readOnly = true)
	public double countyActivityRate(Long countyId){
		double activity = this.countyActivity(countyId);
		double electors = this.countyElectors(countyId);
		return (activity/electors)*100;
	}
	
	@Transactional(readOnly = true)
	public int countyVotesCorrupt (Long countyId){
		return repository.multiVotesCorruptByCounty(countyId)
				.stream().mapToInt(o->o.getVotesMultiCorrupt()).sum();
	}
	
	@Transactional(readOnly = true)
	public int countyDistrictNumber(Long countyId){
		return repositoryDistrict.findAllCountyDistricts(countyId).size();
	}
	
	@Transactional(readOnly = true)
	public int countyDistrictsSendData(Long countyId){
		return repository.multiVotesCorruptByCounty(countyId).size();
	}
	//KONSOLIDUOTAS
	@Transactional(readOnly = true)
	public List <WinnersWinners> winnersTakesItAll(){
		List <MultiVotesMandates> mandatesCountMethod = this.mandatesList();
		List <SingleWinnersAll> singleWinnersMethod = resultsSingleService.singleWinnersInCountyAll();
		
		List <WinnersWinners> winnersWinners = new ArrayList<>();
		
		for (MultiVotesMandates mandates:mandatesCountMethod){
			int partyId = mandates.getPartyId().intValue();
			int mandatesPerParty = mandates.getMandates();
			List<CandidateEntity> candidatesinParty = repository.candidatesByPartyId(partyId);
			//System.out.println(partyId);
			//System.out.println(mandatesPerParty);
			
			int i=0;
			while (mandatesPerParty>0){
				boolean isSingleWinner = false;
				for (SingleWinnersAll singleWinner:singleWinnersMethod){
					if(candidatesinParty.size()>i){
						if(candidatesinParty.get(i).getPersonCode().equals(singleWinner.getPersonCode())){
							isSingleWinner = true;
						}
					}
				}
				
				if ((candidatesinParty.size()>i)&&(!isSingleWinner)){
					//&&(!singleWinnersMethod.stream().filter(o->o.getPersonCode() == Integer.parseInt(candidatesinParty.get(i).getPersonCode())).findFirst().isPresent())
					WinnersWinners newEntity = new WinnersWinners();
					newEntity.setPartyId(mandates.getPartyId());
					newEntity.setPartyName(repositoryParty.findPartyById(mandates.getPartyId()).getPartyName());
					newEntity.setPersonCode(candidatesinParty.get(i).getPersonCode());
					newEntity.setPersonName(candidatesinParty.get(i).getFirstName());
					newEntity.setPersonSurname(candidatesinParty.get(i).getLastName());
					winnersWinners.add(newEntity);	
					mandatesPerParty--;
				}
				
				i++;
			}
			
			
		}
		repository.saveRealWinnersMulti(winnersWinners);
		return winnersWinners;
	}
	

	@Transactional
	public List<FinalPartyResult> allPartyWinners() {
		List<PartyEntity> list = repositoryParty.findAll();
		List<FinalPartyResult> winners = new ArrayList<>();
		List<MultiVotesMandates> mandateList = repository.findAllMandates();

		

		 String partyName ="";
		 int totalVoteCount=0;
		 double participatedPercent=0.0;
		 int mandateCount=0;
		 

		for (PartyEntity p : list) {

			

			partyName = p.getPartyName();
			participatedPercent = allCountyRateFromWholeByParty(p.getPartyId());
			totalVoteCount = this.allCountyVotesByParty(p.getPartyId());
			
			for(MultiVotesMandates m: mandateList){
				
				if(m.getPartyId() == p.getPartyId()){
					mandateCount = m.getMandates();
				}
			}
			
			winners.add(new FinalPartyResult(partyName, totalVoteCount, participatedPercent, mandateCount));

		}

//Veikia?
repository.createFinalMultiResultList(winners);

		return winners;
	}

	
}




