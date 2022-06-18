package beans;

public class Administrator extends User {
	
	private String role;

	public Administrator() {
		super();
		this.role = "Administrator";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
