package lt.itakademija.results.multi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class WinnersWinners {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long winnersWinnersId;
	@NotEmpty
	private String personCode;
	@NotEmpty
	private String personName;
	@NotEmpty
	private String personSurname;
	private Long partyId;
	private String partyName;

	public WinnersWinners() {
	}

	public WinnersWinners(String personCode, String personName, String personSurname, Long partyId, String partyName) {
		super();
		this.personCode = personCode;
		this.personName = personName;
		this.personSurname = personSurname;
		this.partyId = partyId;
		this.partyName = partyName;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonSurname() {
		return personSurname;
	}

	public void setPersonSurname(String personSurname) {
		this.personSurname = personSurname;
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
