package beans;

public class Administrator extends User {
	
	private String rolee;

	public Administrator() {
		super();
		this.rolee = "Administrator";
	}

	public String getRolee() {
		return rolee;
	}

	public void setRole(String role) {
		this.rolee = role;
	}

}
