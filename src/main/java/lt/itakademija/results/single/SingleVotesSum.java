package lt.itakademija.results.single;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@SqlResultSetMapping(name = "countyWinners", classes = {
		@ConstructorResult(targetClass = lt.itakademija.results.single.SingleVotesSum.class, columns = {
					@ColumnResult(name = "PERSON_CODE"), 
					@ColumnResult(name = "COUNTY_ID", type = Long.class),
					@ColumnResult(name = "VOTES_SINGLE_CANDIDATE", type = Integer.class) }) })
@Entity
public class SingleVotesSum {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long singleVotesSumId;
	@NotEmpty
	private String personCode;
	@NotNull
	private Long countyId;
	@NotNull
	private int votesSingleCandidate;

	public SingleVotesSum() {

	}

	public SingleVotesSum(String personCode, Long countyId, int votesSingleCandidate) {
		super();
		this.personCode = personCode;
		this.countyId = countyId;
		this.votesSingleCandidate = votesSingleCandidate;
	}

	public Long getSingleVotesSumId() {
		return singleVotesSumId;
	}

	public void setSingleVotesSumId(Long singleVotesSumId) {
		this.singleVotesSumId = singleVotesSumId;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public Long getCountyId() {
		return countyId;
	}

	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}

	public int getVotesSingleCandidate() {
		return votesSingleCandidate;
	}

	public void setVotesSingleCandidate(int votesSingleCandidate) {
		this.votesSingleCandidate = votesSingleCandidate;
	}

}
