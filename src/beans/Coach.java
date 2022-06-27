package beans;

import java.util.Date;

public class Coach extends User {
	
	private String role;

	public Coach(String username,String password,String name,String surname,Date date,GenderEnum gender,RoleEnum role) {
		super(username,password,name,surname,date,gender,role);
		this.role = "Coach";
	}
	public Coach(String username,String password,String name,String surname,Date date,GenderEnum gender) {
		super(username,password,name,surname,date,gender);
		this.role = "Coach";
	}

	public String getRolee() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
