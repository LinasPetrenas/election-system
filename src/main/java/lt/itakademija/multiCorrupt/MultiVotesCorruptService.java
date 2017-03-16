package lt.itakademija.multiCorrupt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class MultiVotesCorruptService {
	@Autowired
	private MultiVotesCorruptRepo repository;
	
	@Transactional
	public MultiVotesCorruptEntity save(MultiVotesCorruptEntity c) {
		return repository.save(c);
	}
	
	@Transactional(readOnly = true)
	public List <MultiVotesCorruptEntity> findMultiVotesCorrutByDistrict(Long districtId) {
		return repository.findMultiVotesCorruptByDistrict(districtId);
	}
	
	// Grazina multiVotesCorruptEntity kaip objekta
	@Transactional(readOnly = true)
	public MultiVotesCorruptEntity findMultiVotesCorruptEntityByDistrict( Long districtId) {
		return repository.findMultiVotesCorruptEntityByDistrict(districtId);
	}

	@Transactional(readOnly = true)
	public List<MultiVotesCorruptEntity> findAll() {
		return repository.findAll();
	}

	@Transactional
	public List <MultiVotesCorruptEntity> delete(Long districtId) {
		return repository.delete(districtId);
				
	}
}
