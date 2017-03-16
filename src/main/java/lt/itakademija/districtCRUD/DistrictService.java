package lt.itakademija.districtCRUD;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class DistrictService {

	@Autowired
	private DistrictRepository repository;

	@Transactional
	public DistrictEntity save(DistrictEntity p) {
		return repository.save(p);
	}

	@Transactional(readOnly = true)
	public List<DistrictEntity> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public List<DistrictEntity> findAllCountyDistricts(Long countyId) {
		return repository.findAllCountyDistricts(countyId);
	}

	@Transactional
	public DistrictEntity delete(Long id) {
		return repository.delete(id);
	}

	@Transactional(readOnly = true)
	public DistrictEntity findDistrictById(Long id) {
		return repository.findDistrictById(id);
	}
}
