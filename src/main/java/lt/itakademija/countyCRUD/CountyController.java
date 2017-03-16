package lt.itakademija.countyCRUD;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CountyController {

	@Autowired
	private CountyService service;

	@PostMapping("/api/county")

	public CountyEntity createOrUpdateCounty(@RequestBody CountyEntity c) {
		return service.save(c);
	}

	@GetMapping("/api/county/{id}")
	public CountyEntity findCounty(@PathVariable Long id) {
		return service.findCounty(id);
	}

	@GetMapping("/api/county")
	public Iterable<CountyEntity> counties() {
		return service.findAll();
	}

	@DeleteMapping("/api/county/{id}")
	public CountyEntity delete(@PathVariable Long id) {
		return service.delete(id);
	}

}
