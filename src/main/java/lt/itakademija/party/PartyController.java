package lt.itakademija.party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lt.itakademija.countyCRUD.CountyEntity;

@RestController
public class PartyController {
	@Autowired
	private PartyService service;

	@PostMapping("/api/party")
	public PartyEntity createOrUpdateCandidate(@RequestBody PartyEntity p) {

		return service.save(p);
	}

	@GetMapping("/api/party")
	public Iterable<PartyEntity> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/api/party/{partyId}")
	public PartyEntity findPartyById(@PathVariable Long partyId) {
		return service.findPartyById(partyId);
	}
	
	@GetMapping("/api/partyByPersoncode/{personCode}")
	public PartyEntity findPartyByPersonCode(@PathVariable String personCode) {
		return service.findPartyByPersonCode(personCode);
	}

	@CrossOrigin
	@PostMapping("/api/party/{id}")
	public PartyEntity delete(@PathVariable Long id) {
		return service.delete(id);
	}

}
