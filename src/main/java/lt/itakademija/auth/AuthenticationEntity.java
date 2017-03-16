package lt.itakademija.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthenticationEntity {

	@Id
	private Long id;
	@Column(unique = true)
	private String userName;
	private String password;
	private String role;
	
	public AuthenticationEntity() {
	
	}
	
	
	public AuthenticationEntity(Long id, String userName, String password, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "AuthenticationEntity [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role
				+ "]";
	}
	
	
	
}
