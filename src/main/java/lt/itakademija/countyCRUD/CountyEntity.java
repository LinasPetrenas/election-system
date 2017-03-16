package lt.itakademija.countyCRUD;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public final class CountyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long countyId;
	@NotEmpty
	@Column(unique = true)
	private String countyName;

	public CountyEntity() {

	}

	public CountyEntity(String countyName) {
		this.countyName = countyName;

	}

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}



	
}
