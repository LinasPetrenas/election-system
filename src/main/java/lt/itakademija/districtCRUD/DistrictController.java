package lt.itakademija.districtCRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController {

	@Autowired
	private DistrictService service;

	@PostMapping("/api/district")
	public DistrictEntity createOrUpdateDistrict(@RequestBody DistrictEntity d) {
		return service.save(d);
	}

	@GetMapping("/api/district")
	public Iterable<DistrictEntity> districts() {
		return service.findAll();
	}
	
	@GetMapping("/api/county/districts/{countyId}")
	public Iterable<DistrictEntity> districts(@PathVariable Long countyId) {
		return service.findAllCountyDistricts(countyId);
	}

	@CrossOrigin
	@DeleteMapping("/api/district/{id}")
	public DistrictEntity delete(@PathVariable Long id) {
		return service.delete(id);
	}
	
	@GetMapping("/api/district/{id}")
	public DistrictEntity findDistrictById(@PathVariable Long id) {
		return service.findDistrictById(id);
	}
}
