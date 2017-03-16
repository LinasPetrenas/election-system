package lt.itakademija.districtCRUD;

import java.util.List;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lt.itakademija.representative.RepresentativeEntity;

@Repository
public class DistrictRepository {

	@Autowired
	private EntityManager em;

	public DistrictEntity save(DistrictEntity d) {
		Long countyId = d.getCountyId();
		boolean nameExists = false;
		List <DistrictEntity> allInCounty = this.findAllCountyDistricts(countyId);
		for (DistrictEntity districtEntity:allInCounty){
			if (districtEntity.getDistrictName().equals(d.getDistrictName())){
				nameExists = true;
			}
		}
		if (!nameExists) {
			em.persist(d);
		} 
		return d;
	}

	public List<DistrictEntity> findAll() {
		return em.createQuery("SELECT d from DistrictEntity d").getResultList();
	}

	public List<DistrictEntity> findAllCountyDistricts(Long countyId) {
		return em.createQuery("SELECT d from DistrictEntity d WHERE d.countyId = :countyId order by DISTRICT_NAME   ")
				.setParameter("countyId", countyId).getResultList();
	}

	public DistrictEntity delete(Long id) {
		DistrictEntity c = em.find(DistrictEntity.class, id);
		Long districtsId = c.getId();
		List<RepresentativeEntity> suitedRepresentative = em
				.createQuery("SELECT r from RepresentativeEntity r WHERE r.districtId = :districtId")
				.setParameter("districtId", districtsId).getResultList();
		for (RepresentativeEntity representative : suitedRepresentative) {
			em.remove(representative);
		}
		
		em.remove(c);
		return c;
	}

	
	public DistrictEntity findDistrictById(Long id) {
		return (DistrictEntity) em.createQuery("SELECT d from DistrictEntity d WHERE districtId = :id")
		.setParameter("id", id).getSingleResult();
	}
	
}
