
package lt.itakademija.single.votes;

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
public class SingleVotesController {

	@Autowired
	private SingleVotesService service;

	@PostMapping("repr/api/singlevotes")
	public SingleVotesEntity createOrUpdateSingleVote(@RequestBody SingleVotesEntity s) {
		return service.save(s);
	}

	@PostMapping("repr/api/singlevotes/all")
	public List<SingleVotesEntity> createOrUpdateSingleVotesAll(@RequestBody List<SingleVotesEntity> s) {
		return service.saveAll(s);
	}

	@GetMapping("repr/api/singlevotes/{personCode}")
	public List<SingleVotesEntity> findSingleVotesByCandidateId(@PathVariable String personCode) {
		return service.findSingleVotesByCandidateId(personCode);
	}

	@GetMapping("repr/api/singlevotes")
	public Iterable<SingleVotesEntity> votes() {
		return service.findAll();
	}

	@CrossOrigin
	@DeleteMapping("repr/api/singlevotes/{personCode}")
	public List<SingleVotesEntity> delete(@PathVariable String personCode) {
		return service.delete(personCode);
	}
	
	@CrossOrigin
	@DeleteMapping("repr/api/singlevotes/all/{districtId}")
	public List<SingleVotesEntity> deleteAllByDistrictId(@PathVariable Long districtId) {
		return service.deleteAllByDistrictId(districtId);
	}

}
