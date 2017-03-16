package lt.itakademija.multi.votes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class MultiVotesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@NotNull
	private Long districtId;
	private Long countyId;
	@NotNull
	private Long partyId;
	@NotNull
	private int votesMultiParty;
	private String dateOnSaving;
	
	public MultiVotesEntity() {
	}

	public MultiVotesEntity(Long districtId, Long partyId, int votesMultiParty) {
		super();
		this.districtId = districtId;
		this.partyId = partyId;
		this.votesMultiParty = votesMultiParty;
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

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public int getVotesMultiParty() {
		return votesMultiParty;
	}

	public void setVotesMultiParty(int votesMultiParty) {
		this.votesMultiParty = votesMultiParty;
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
