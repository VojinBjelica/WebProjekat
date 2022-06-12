package beans;

public class Customer extends User {
	
	private String role;

	public Customer() {
		super();
		this.role = "Customer";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
