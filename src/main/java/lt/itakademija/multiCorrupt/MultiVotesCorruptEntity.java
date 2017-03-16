package lt.itakademija.multiCorrupt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class MultiVotesCorruptEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@NotNull
	@Column(unique=true)
	private Long districtId;
	private Long countyId;
	@NotNull
	private int votesMultiCorrupt;
	private String dateOnSaving;
	
	public MultiVotesCorruptEntity() {
	}

	public MultiVotesCorruptEntity(Long districtId, int votesMultiCorrupt) {
		super();
		this.districtId = districtId;
		this.votesMultiCorrupt = votesMultiCorrupt;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public int getVotesMultiCorrupt() {
		return votesMultiCorrupt;
	}

	public void setVotesMultiCorrupt(int votesMultiCorrupt) {
		this.votesMultiCorrupt = votesMultiCorrupt;
	}

	public String getDateOnSaving() {
		return dateOnSaving;
	}

	public void setDateOnSaving(String dateOnSaving) {
		this.dateOnSaving = dateOnSaving;
	}

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

	
}
