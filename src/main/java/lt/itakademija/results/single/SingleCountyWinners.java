package lt.itakademija.results.single;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@Entity
public class SingleCountyWinners {
@Id
@GeneratedValue(strategy= GenerationType.AUTO)
private Long id;

	@Column(unique=true)
	private String countyName;
	private String firstName;
	private String lastName;
	private String partyName;
	private double countyWinnerPercents;
	
	public SingleCountyWinners() {
		super();
	}
	
	
	public SingleCountyWinners(String countyName, String firstName, String lastName,String partyName, double countyWinnerPercents) {
		super();
		this.countyName = countyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.partyName = partyName;
		this.countyWinnerPercents = countyWinnerPercents;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPartyName() {
		return partyName;
	}


	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
	public double getCountyWinnerPercents() {
		return countyWinnerPercents;
	}
	public void setCountyWinnerPercents(double countyWinnerPercents) {
		this.countyWinnerPercents = countyWinnerPercents;
	}
	
	
}
