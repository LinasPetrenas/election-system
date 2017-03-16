package lt.itakademija.countyCRUD;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lt.itakademija.candidateCRUD.CandidateEntity;
import lt.itakademija.districtCRUD.DistrictEntity;
import lt.itakademija.representative.RepresentativeEntity;

@Repository
public class CountyRepository {

	@Autowired
	private EntityManager em;

	public CountyEntity save(CountyEntity c) {
		if (c.getCountyId() == null) {
			em.persist(c);
			return c;
		} else {
			CountyEntity merged = em.merge(c);
			em.persist(merged);
			return merged;
		}
	}

	public List<CountyEntity> findAll() {
		return em.createQuery("SELECT c from CountyEntity c ORDER BY county_name").getResultList();
	}
	
	public CountyEntity findCounty(Long id) {
		return em.find(CountyEntity.class, id);
	}
//
//	public CountyEntity delete(Long id) {
//		CountyEntity c = em.find(CountyEntity.class, id);
//		em.remove(c);
//		return c;
//	}

	
	public CountyEntity delete(Long id) {
		CountyEntity c = em.find(CountyEntity.class, id);
		em.remove(c);

		List<DistrictEntity> suitedDistricts = em
				.createQuery("SELECT d from DistrictEntity d WHERE d.countyId = :countyId").setParameter("countyId", id)
				.getResultList();
		for (DistrictEntity district : suitedDistricts) {

			Long districtsId = district.getId();
			List<RepresentativeEntity> suitedRepresentative = em
					.createQuery("SELECT r from RepresentativeEntity r WHERE r.districtId = :districtId")
					.setParameter("districtId", districtsId).getResultList();
			for (RepresentativeEntity representative : suitedRepresentative) {
				em.remove(representative);
			}

			em.remove(district);
		}
		return c;
	}

	
}
