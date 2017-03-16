package lt.itakademija.results.single;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class SingleWinnersAll {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long singleWinnersAllId;
	@NotEmpty
	private String personCode;
	@NotEmpty
	private String personName;
	@NotEmpty
	private String personSurname;
	@NotNull
	private Long countyId;
	@NotEmpty
	private String countyName;
	@NotNull
	private int votesSingleCandidate;
	private int partyId;
	private String partyName;

	public SingleWinnersAll() {

	}

	public SingleWinnersAll(String personCode, String personName, String personSurname, Long countyId,
			String countyName, int votesSingleCandidate, int partyId, String partyName) {
		super();
		this.personCode = personCode;
		this.personName = personName;
		this.personSurname = personSurname;
		this.countyId = countyId;
		this.countyName = countyName;
		this.votesSingleCandidate = votesSingleCandidate;
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

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public int getVotesSingleCandidate() {
		return votesSingleCandidate;
	}

	public void setVotesSingleCandidate(int votesSingleCandidate) {
		this.votesSingleCandidate = votesSingleCandidate;
	}

	public int getPartyId() {
		return partyId;
	}

	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

}
