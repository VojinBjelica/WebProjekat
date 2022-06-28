package beans;

import java.util.Date;

public class Manager extends User {
	
	private  String rolee;
	private  String sportObjectName;

	public Manager() {}
	
	public Manager(String username,String password,String name,String surname,Date date,GenderEnum gender,RoleEnum role) {
		super(username,password,name,surname,date,gender,role);
		this.rolee = "Manager";
		this.sportObjectName = "None";
	}
	public Manager(String username,String password,String name,String surname,Date date,GenderEnum gender) {
		super(username,password,name,surname,date,gender);
		this.rolee = "Manager";
		this.sportObjectName = "None";
	}
	public Manager(String username,String password,String name,String surname,Date date,GenderEnum gender, String sportObject) {
		super(username,password,name,surname,date,gender);
		this.rolee = "Manager";
		this.sportObjectName = sportObject;
	}
	

	public String getRolee() {
		return rolee;
	}

	public void setRole(String role) {
		this.rolee = role;
	}
	
	public String getSportObject() {
		return sportObjectName;
	}
	
	public void setSportObject(String sportObject) {
		this.sportObjectName = sportObject;
	}
	
	
	

}
