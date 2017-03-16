package lt.itakademija.single.votes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.itakademija.countyCRUD.CountyEntity;
import lt.itakademija.countyCRUD.CountyRepository;

@Service
public class SingleVotesService {

	@Autowired
	private SingleVotesRepository repository;

	@Transactional
	public SingleVotesEntity save(SingleVotesEntity s) {
		return repository.save(s);
	}

	@Transactional
	public List<SingleVotesEntity> saveAll(List<SingleVotesEntity> s) {
		return repository.saveAll(s);
	}

	@Transactional(readOnly = true)
	public List<SingleVotesEntity> findSingleVotesByCandidateId(String personCode) {
		return repository.findSingleVotesByPersonCode(personCode);
	}

	@Transactional(readOnly = true)
	public List<SingleVotesEntity> findAll() {
		return repository.findAll();
	}

	@Transactional
	public List<SingleVotesEntity> delete(String personCode) {
		return repository.delete(personCode);
	}
	
	@Transactional
	public List<SingleVotesEntity> deleteAllByDistrictId(Long districtId) {
		return repository.deleteAllByDistrictId(districtId);
	}
}
