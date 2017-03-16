package lt.itakademija.results.multi;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FinalPartyResult {
	
	@Id
	private String partyName;
	private int totalVoteCount;
	private double participatedPercent;
	private int mandateCount;
	
	
	public FinalPartyResult() {
		super();
	}
	public FinalPartyResult(String partyName, int totalVoteCount, double participatedPercent, int mandateCount) {
		super();
		this.partyName = partyName;
		this.totalVoteCount = totalVoteCount;
		this.participatedPercent = participatedPercent;
		this.mandateCount = mandateCount;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public int getTotalVoteCount() {
		return totalVoteCount;
	}
	public void setTotalVoteCount(int totalVoteCount) {
		this.totalVoteCount = totalVoteCount;
	}
	public double getParticipatedPercent() {
		return participatedPercent;
	}
	public void setParticipatedPercent(double participatedPercent) {
		this.participatedPercent = participatedPercent;
	}
	public int getMandateCount() {
		return mandateCount;
	}
	public void setMandateCount(int mandateCount) {
		this.mandateCount = mandateCount;
	}
	

}
