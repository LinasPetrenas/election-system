package lt.itakademija.singleCorrupt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class SingleVotesCorruptService {

	@Autowired
	private SingleVotesCorruptRepo repository;

	@Transactional
	public SingleVotesCorruptEntity save(SingleVotesCorruptEntity c) {
		return repository.save(c);
	}

	@Transactional(readOnly = true)
	public List<SingleVotesCorruptEntity> findSingleVotesCorruptByDistrict(Long districtId) {
		return repository.findSingleVotesCorruptByDistrict(districtId);
	}
	
	// Grazina singleVotesCorruptEntity kaip objekta, o ne kaip masyva
	@Transactional(readOnly = true)
	public SingleVotesCorruptEntity findSingleVotesCorruptEntityByDistrict( Long districtId) {
		return repository.findSingleVotesCorruptEntityByDistrict(districtId);
	}

	@Transactional(readOnly = true)
	public List<SingleVotesCorruptEntity> findAll() {
		return repository.findAll();
	}

	@Transactional
	public List<SingleVotesCorruptEntity> deleteById(Long districtId) {
		return repository.deleteById(districtId);

	}
}
