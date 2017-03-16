package lt.itakademija.multi.votes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.itakademija.countyCRUD.CountyEntity;
import lt.itakademija.countyCRUD.CountyRepository;

@Service
public class MultiVotesService {

	@Autowired
	private MultiVotesRepository repository;

	@Transactional
	public MultiVotesEntity save(MultiVotesEntity c) {
		return repository.save(c);
	}

	@Transactional
	public List<MultiVotesEntity> saveAll(List<MultiVotesEntity> c) {
		return repository.saveAll(c);
	}

	@Transactional(readOnly = true)
	public List<MultiVotesEntity> findMultiVotesByPartyId(Long partyId) {
		return repository.findMultiVotesByPartyId(partyId);
	}

	@Transactional(readOnly = true)
	public List<MultiVotesEntity> findAll() {
		return repository.findAll();
	}

	@Transactional
	public List<MultiVotesEntity> delete(Long partyId) {
		return repository.delete(partyId);
	}
	
	@Transactional
	public List<MultiVotesEntity> deleteAllByDistrictId(Long districtId) {
		return repository.deleteAllByDistrictId(districtId);
	}

}