package lt.itakademija.results.single;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class ParlamentMemeber {

	
	private int partyId;
	private String fullName;
	private String partyName;
	
	
	public ParlamentMemeber(int partyId,String fullName, String partyName) {
		super();
		this.partyId = partyId;
		this.fullName = fullName;
		this.partyName = partyName;
	}


	


	public int getPartyId() {
		return partyId;
	}





	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}





	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getPartyName() {
		return partyName;
	}


	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	
}
