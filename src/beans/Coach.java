package beans;

import java.util.Date;

public class Coach extends User {
	
	private String rolee;
	private String sportObjectNamee;
	private int coachID;

	public Coach(String username,String password,String name,String surname,Date date,GenderEnum gender,RoleEnum role) {
		super(username,password,name,surname,date,gender,role);
		this.rolee = "Coach";
	}
	public Coach(String username,String password,String name,String surname,Date date,GenderEnum gender) {
		super(username,password,name,surname,date,gender);
		this.rolee = "Coach";
	}
	public Coach(String username,String password,String name,String surname,Date date,GenderEnum gender,String sportObject) {
		super(username,password,name,surname,date,gender);
		this.rolee = "Coach";
		this.sportObjectNamee = sportObject;
	}
	public Coach(String username,String password,String name,String surname,Date date,GenderEnum gender,int id) {
		super(username,password,name,surname,date,gender);
		this.rolee = "Coach";
		this.coachID = id ;
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
	public int getCoachID() {
		return coachID;
	}
	public void setCoachID(int coachID) {
		this.coachID = coachID;
	}
	

}
