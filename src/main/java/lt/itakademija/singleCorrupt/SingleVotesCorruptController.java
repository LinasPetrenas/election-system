package lt.itakademija.singleCorrupt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lt.itakademija.multi.votes.MultiVotesEntity;
import lt.itakademija.multi.votes.MultiVotesService;

@RestController
public class SingleVotesCorruptController {

	@Autowired
	private SingleVotesCorruptService service;
	
	@PostMapping("/repr/api/singlevotescorrupt")
	public SingleVotesCorruptEntity createOrUpdateMultiVotes(@RequestBody SingleVotesCorruptEntity c) {
		return service.save(c);
	}
	// Grazina singleVotesCorruptEntity ,kaip masyva
	@GetMapping("/repr/api/singlevotescorrupt/{districtId}")
	public List<SingleVotesCorruptEntity> findSingleVotesCorruptByDistrict(@PathVariable Long districtId) {
		return service.findSingleVotesCorruptByDistrict(districtId);
	}
	
	// Grazina singleVotesCorruptEntity kaip objekta
	@GetMapping("/repr/api/singleVotesCorruptEntity/{districtId}")
	public SingleVotesCorruptEntity findSingleVotesCorruptEntityByDistrict(@PathVariable Long districtId) {
		return service.findSingleVotesCorruptEntityByDistrict(districtId);
	}

	@GetMapping("/repr/api/singlevotescorrupt")
	public Iterable<SingleVotesCorruptEntity> multiVotes() {
		return service.findAll();
	}
	
	@CrossOrigin
	@DeleteMapping("/repr/api/singlevotescorrupt/{districtId}")
	public List<SingleVotesCorruptEntity> delete(@PathVariable Long districtId) {
		return service.deleteById(districtId);
	}
	
}
