package beans;

import java.util.Date;

public class Manager extends User {
	
	private String role;

	public Manager(String username,String password,String name,String surname,Date date,GenderEnum gender,RoleEnum role) {
		super(username,password,name,surname,date,gender,role);
		this.role = "Manager";
	}
	public Manager(String username,String password,String name,String surname,Date date,GenderEnum gender) {
		super(username,password,name,surname,date,gender);
		this.role = "Manager";
	}

	public String getRolee() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
