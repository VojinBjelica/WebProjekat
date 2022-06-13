package beans;

import java.time.LocalDate;

public class Customer extends User {
	
	private String role;

	public Customer(String username, String password, String name, String surname, LocalDate dateOfBirth, GenderEnum gender) {
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
