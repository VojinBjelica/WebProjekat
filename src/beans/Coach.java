package beans;

public class Coach extends User {
	
	private String role;

	public Coach() {
		super();
		this.role = "Coach";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
