package beans;

import java.util.Date;

public class Customer extends User {
	
	private String roles;
	private String sportObjectNick;
	private int trainingID;
	private TypeName userType;
	
	public Customer()
	{
		super();
	}
	public Customer(String username, String password, String name, String surname, Date dateOfBirth, GenderEnum gender) {
		super(username,password,name,surname,dateOfBirth,gender);
		this.roles = "Customer";
		this.trainingID = -1;
		this.userType = TypeName.No;
	}
	public Customer(String username, String password, String name, String surname, Date dateOfBirth, GenderEnum gender,TypeName userType) {
		super(username,password,name,surname,dateOfBirth,gender);
		this.roles = "Customer";
		this.trainingID = -1;
		this.userType = userType;
	}
	public Customer(String username, String password, String name, String surname, Date dateOfBirth, GenderEnum gender,RoleEnum role) {
		super(username,password,name,surname,dateOfBirth,gender,role);
		this.roles = "Customer";
		this.trainingID = -1;
		this.userType = TypeName.No;
	}
	public Customer(String username, String password, String name, String surname, Date dateOfBirth, GenderEnum gender,String nick) {
		super(username,password,name,surname,dateOfBirth,gender);
		this.roles = "Customer";
		this.sportObjectNick = nick;
		this.trainingID = -1;
		this.userType = TypeName.No;
	}
	public Customer(String username, String password, String name, String surname, Date dateOfBirth, GenderEnum gender,int trainingID) {
		super(username,password,name,surname,dateOfBirth,gender);
		this.roles = "Customer";
		this.trainingID = trainingID;
		this.userType = TypeName.No;
	}
	

	
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public TypeName getUserType() {
		return userType;
	}
	public void setUserType(TypeName userType) {
		this.userType = userType;
	}
	public String getRolee() {
		return roles;
	}

	public void setRole(String role) {
		this.roles = role;
	}
	public String getSportObjectNick() {
		return sportObjectNick;
	}
	public void setSportObjectNick(String sportObjectNick) {
		this.sportObjectNick = sportObjectNick;
	}
	public int getTrainingID() {
		return trainingID;
	}
	public void setTrainingID(int trainingID) {
		this.trainingID = trainingID;
	}
	
	

}
