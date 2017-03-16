package lt.itakademija.singleCorrupt;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class SingleVotesCorruptEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@NotNull
	@Column(unique=true)
	private Long districtId;
	@NotNull
	private Long countyId;
	private int votesSingleCorrupt;
	private String dateOnSaving;

	public SingleVotesCorruptEntity() {
	}

	public SingleVotesCorruptEntity(Long districtId, int votesSingleCorrupt) {
		super();
		this.districtId = districtId;
		this.votesSingleCorrupt = votesSingleCorrupt;
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

	public int getVotesSingleCorrupt() {
		return votesSingleCorrupt;
	}

	public void setVotesSingleCorrupt(int votesSingleCorrupt) {
		this.votesSingleCorrupt = votesSingleCorrupt;
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
