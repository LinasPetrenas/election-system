package lt.itakademija.districtCRUD;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import lt.itakademija.countyCRUD.CountyEntity;

@Entity
public final class DistrictEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long districtId;
	@NotEmpty
	private String districtName;
	@NotEmpty
	private String districtAddress;
	private int districtElectors;
	// foreignkey
	private Long countyId;

	public DistrictEntity() {
	}

	public DistrictEntity(String districtName, String districtAddress, int districtElectors, Long countyId) {
		this.districtName = districtName;
		this.districtAddress = districtAddress;
		this.districtElectors = districtElectors;
		this.countyId = countyId;

	}

	public Long getId() {
		return districtId;
	}

	public void setId(Long id) {
		this.districtId = id;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictAddress() {
		return districtAddress;
	}

	public void setDistrictAddress(String districtAddress) {
		this.districtAddress = districtAddress;
	}

	public int getDistrictElectors() {
		return districtElectors;
	}

	public void setDistrictElectors(int districtElectors) {
		this.districtElectors = districtElectors;
	}

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

}
