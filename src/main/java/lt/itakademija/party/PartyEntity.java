package lt.itakademija.party;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class PartyEntity {
	
	@Id
	private Long partyId;
	@Size(max = 100)
	@NotEmpty
	@Column(unique = true)
	private String partyName;

	public PartyEntity() {

	}

	
	
	
	public PartyEntity(String partyName) {

		this.partyName = partyName;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

}
