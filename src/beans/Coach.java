package beans;

import java.util.Date;

public class Coach extends User {
	
	private String rolee;
	private String sportObjectNamee;

	public Coach(String username,String password,String name,String surname,Date date,GenderEnum gender,RoleEnum role) {
		super(username,password,name,surname,date,gender,role);
		this.rolee = "Coach";
		this.sportObjectNamee = "None";
	}
	public Coach(String username,String password,String name,String surname,Date date,GenderEnum gender) {
		super(username,password,name,surname,date,gender);
		this.rolee = "Coach";
		this.sportObjectNamee = "None";
	}
	public Coach(String username,String password,String name,String surname,Date date,GenderEnum gender,String sportObject) {
		super(username,password,name,surname,date,gender);
		this.rolee = "Coach";
		this.sportObjectNamee = sportObject;
	}

	public String getRolee() {
		return rolee;
	}

	public void setRole(String role) {
		this.rolee = role;
	}
	public String getSportObjectNamee() {
		return sportObjectNamee;
	}
	public void setSportObjectNamee(String sportObjectName) {
		this.sportObjectNamee = sportObjectName;
	}
	

}
