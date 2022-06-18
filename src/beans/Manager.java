package beans;

public class Manager extends User {
	
	private String role;

	public Manager() {
		super();
		this.role = "Manager";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
