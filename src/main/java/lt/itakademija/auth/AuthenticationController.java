package lt.itakademija.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lt.itakademija.candidateCRUD.CandidateEntity;
import lt.itakademija.representative.RepresentativeEntity;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationService service;

	

	@PostMapping("/api/auth")
	public AuthenticationEntity createOrUpdateUser(@RequestBody AuthenticationEntity a) {
		return service.save(a);
	}
	@GetMapping("/api/auth/{id}")
	public AuthenticationEntity findUser(@PathVariable Long id) {
		return service.findUser(id);
	}
	
	@PostMapping("/api/auth/login")
	public AuthenticationEntity findUserByNameAndPass(@RequestBody AuthenticationEntity a) {
		return service.findUserByNameAndPass(a);
	}
	
	@GetMapping("/api/auth")
	public Iterable<AuthenticationEntity> allRegisteredUsers() {
		return service.findAll();
	}
	
	@PostMapping("/api/auth/{id}")
	public int delete(@PathVariable Long id) {
		return service.delete(id);
	}
}
