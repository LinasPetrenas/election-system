package lt.itakademija.countyCRUD;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.itakademija.candidateCRUD.CandidateEntity;

@Service
public class CountyService {

	@Autowired
	private CountyRepository repository;

	@Transactional
	public CountyEntity save(CountyEntity c) {
		return repository.save(c);
	}
	
	@Transactional(readOnly = true)
	public CountyEntity findCounty(Long id) {
		return repository.findCounty(id);
	}

	@Transactional(readOnly = true)
	public List<CountyEntity> findAll() {
		return repository.findAll();
	}

	@Transactional
	public CountyEntity delete(Long id) {
		return repository.delete(id);
	}
}
