package beans;

import java.util.Date;

public class Customer extends User {
	
	private String role;
	
	public Customer()
	{
		super();
	}
	public Customer(String username, String password, String name, String surname, Date dateOfBirth, GenderEnum gender) {
		super(username,password,name,surname,dateOfBirth,gender);
		this.role = "Customer";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
