package beans;

import java.util.Date;

public class Customer extends User {
	
	private String roles;
	
	public Customer()
	{
		super();
	}
	public Customer(String username, String password, String name, String surname, Date dateOfBirth, GenderEnum gender) {
		super(username,password,name,surname,dateOfBirth,gender);
		this.roles = "Customer";
	}

	public String getRole() {
		return roles;
	}

	public void setRole(String role) {
		this.roles = role;
	}

}
