package lt.itakademija.results.multi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class MultiVotesMandates {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@NotNull
	private Long partyId;
	private int votesMultiParty;
	private int mandates;
	private int remaining;

	public MultiVotesMandates(){
	}
	
	public MultiVotesMandates(Long partyId, int votesMultiParty, int mandates, int remaining) {
		super();
		this.partyId = partyId;
		this.votesMultiParty = votesMultiParty;
		this.mandates = mandates;
		this.remaining = remaining;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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

	public int getMandates() {
		return mandates;
	}

	public void setMandates(int mandates) {
		this.mandates = mandates;
	}

	public int getRemaining() {
		return remaining;
	}

	public void setRemaining(int remaining) {
		this.remaining = remaining;
	}

}