package lt.itakademija.representative;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lt.itakademija.auth.AuthenticationEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class RepresentativeService {

	@Autowired
	private RepresentativeRepository repository;

	@Transactional
	public RepresentativeEntity save(RepresentativeEntity p) {
		String password = p.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
		String bcryptPassword = passwordEncoder.encode(password);
				
		p.setPassword(bcryptPassword);
		return repository.save(p);
	}

	@Transactional(readOnly = true)
	public List<RepresentativeEntity> findAll() {
		return repository.findAll();
	}

	@Transactional
	public RepresentativeEntity delete(Long id) {
		return repository.delete(id);
	}

	@Transactional
	public RepresentativeEntity find(Long id) {
		return repository.find(id);
	}
	
}
