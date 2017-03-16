package lt.itakademija.multiCorrupt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lt.itakademija.single.votes.SingleVotesEntity;
import lt.itakademija.singleCorrupt.SingleVotesCorruptEntity;

@RestController
public class MultiVotesCorruptController {

	@Autowired
	private MultiVotesCorruptService service;
	
	@PostMapping("/repr/api/multivotescorrupt")
	public MultiVotesCorruptEntity createOrUpdateMultiVotes(@RequestBody MultiVotesCorruptEntity c) {
		return service.save(c);
	}

	@GetMapping("/repr/api/multivotescorrupt")
	public Iterable<MultiVotesCorruptEntity> multiVotes() {
		return service.findAll();
	}
	
	
	// Grazina singleVotesCorruptEntity kaip masyva
	@GetMapping("/repr/api/multivotescorrupt/{districtId}")
	public List <MultiVotesCorruptEntity> findMultiVotesCorruptByDistrict(@PathVariable Long districtId) {
		return service.findMultiVotesCorrutByDistrict(districtId);
	}
	
	// Grazina multieVotesCorruptEntity kaip objekta
		@GetMapping("/repr/api/multiVotesCorruptEntity/{districtId}")
		public MultiVotesCorruptEntity findMultiVotesCorruptEntityByDistrict(@PathVariable Long districtId) {
			return service.findMultiVotesCorruptEntityByDistrict(districtId);
		}

	@CrossOrigin
	@DeleteMapping("/repr/api/multivotescorrupt/{districtId}")
	public List<MultiVotesCorruptEntity> delete(@PathVariable Long districtId) {
		return service.delete(districtId);
	}
	
}
