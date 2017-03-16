package lt.itakademija.representative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RepresentativeController {

	@Autowired
	private RepresentativeService service;

	@PostMapping("/api/representative")
	public RepresentativeEntity createOrUpdateDistrict(@RequestBody RepresentativeEntity d) {
		return service.save(d);
	}

	@GetMapping("/api/representative")
	public Iterable<RepresentativeEntity> districts() {
		return service.findAll();
	}

	@DeleteMapping("/api/representative/{id}")
	public RepresentativeEntity delete(@PathVariable Long id) {
		return service.delete(id);
	}
	
	@GetMapping("/api/representative/{id}")
	public RepresentativeEntity findRepresentative(@PathVariable Long id) {
		return service.find(id);
	}
}
