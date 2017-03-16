
package lt.itakademija.single.votes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class SingleVotesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long singleVotesId;
	@NotEmpty
	private String personCode;
	@NotNull
	private Long districtId;
	private Long countyId;
	@NotNull
	private int votesSingleCandidate;
	private String dateOnSaving;

	public SingleVotesEntity() {
	}

	public SingleVotesEntity(String personCode, Long districtId, int votesSingleCandidate) {
		super();
		this.personCode = personCode;
		this.districtId = districtId;
		this.votesSingleCandidate = votesSingleCandidate;
	}

	public Long getSingleVotesId() {
		return singleVotesId;
	}

	public void setSingleVotesId(Long singleVotesId) {
		this.singleVotesId = singleVotesId;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public int getVotesSingleCandidate() {
		return votesSingleCandidate;
	}

	public void setVotesSingleCandidate(int votesSingleCandidate) {
		this.votesSingleCandidate = votesSingleCandidate;
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
