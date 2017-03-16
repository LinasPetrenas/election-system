package lt.itakademija.multi.votes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lt.itakademija.countyCRUD.CountyEntity;
import lt.itakademija.countyCRUD.CountyService;

@RestController
public class MultiVotesController {

	@Autowired
	private MultiVotesService service;

	@PostMapping("/repr/api/multivotes")
	public MultiVotesEntity createOrUpdateMultiVotes(@RequestBody MultiVotesEntity c) {
		return service.save(c);
	}
	
	@PostMapping("/repr/api/multivotes/all")
	public List <MultiVotesEntity> createOrUpdateMultiVotesAll(@RequestBody List <MultiVotesEntity> c) {
		return service.saveAll(c);
	}

	@GetMapping("/repr/api/multivotes/{partyId}")
	public List<MultiVotesEntity> findMultiVotesByPartyId(@PathVariable Long partyId) {
		return service.findMultiVotesByPartyId(partyId);
	}

	@GetMapping("/repr/api/multivotes")
	public Iterable<MultiVotesEntity> multiVotes() {
		return service.findAll();
	}
	
	@CrossOrigin
	@DeleteMapping("/repr/api/multivotes/{partyId}")
	public List<MultiVotesEntity> deleteByPartyId(@PathVariable Long partyId) {
		return service.delete(partyId);
	}
	
	@CrossOrigin
	@DeleteMapping("/repr/api/multivotes/all/{districtId}")
	public List<MultiVotesEntity> deleteAllByDistrictId(@PathVariable Long districtId) {
		return service.deleteAllByDistrictId(districtId);
	}

}