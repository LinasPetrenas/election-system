package lt.itakademija.results.single;

public class FinalPartyMandateList {

	private String partyName;
	private int mandateCount;

	public FinalPartyMandateList(String partyName, int mandateCount) {
		super();
		this.partyName = partyName;
		this.mandateCount = mandateCount;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public int getMandateCount() {
		return mandateCount;
	}

	public void setMandateCount(int mandateCount) {
		this.mandateCount = mandateCount;
	}

}
