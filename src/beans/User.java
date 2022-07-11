package beans;

import java.util.Date;

public class User {
	
	private String username;
	private String password;
	private String name;
	private String surname;
	private Date dateOfBirth;
	private RoleEnum role;
	private String trainingHistory;
	private int dues;
	private String sportObject;
	private String visitedObjects;
	private float collectedPoints;
	private String customerType;
	private GenderEnum gender;
	private int deleted;
	public User() {
		super();
	}
	public User(String username, String password, String name, String surname, Date dateOfBirth,
			String trainingHistory, int dues, String sportObject, String visitedObjects, int collectedPoints,
			String customerType, GenderEnum gender) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.trainingHistory = trainingHistory;
		this.dues = dues;
		this.sportObject = sportObject;
		this.visitedObjects = visitedObjects;
		this.collectedPoints = collectedPoints;
		this.customerType = customerType;
		this.gender = gender;
	}
	public User(String username, String password, String name, String surname, Date dateOfBirth, GenderEnum gender) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;

	}
	public User(String username, String password, String name, String surname, Date dateOfBirth, GenderEnum gender,RoleEnum role) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.role = role;
	}
	public User(String username, String password, String name, String surname, Date dateOfBirth, GenderEnum gender,RoleEnum role,int deleted) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.role = role;
		this.deleted = deleted;
	}
	public User(String username, String password, String name, String surname, Date dateOfBirth, GenderEnum gender,RoleEnum role,int deleted,float points) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.role = role;
		this.deleted = deleted;
		this.collectedPoints = points;
	}
	
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getTrainingHistory() {
		return trainingHistory;
	}
	public void setTrainingHistory(String trainingHistory) {
		this.trainingHistory = trainingHistory;
	}
	public int getDues() {
		return dues;
	}
	public void setDues(int dues) {
		this.dues = dues;
	}
	public String getSportObject() {
		return sportObject;
	}
	public void setSportObject(String sportObject) {
		this.sportObject = sportObject;
	}
	public String getVisitedObjects() {
		return visitedObjects;
	}
	public void setVisitedObjects(String visitedObjects) {
		this.visitedObjects = visitedObjects;
	}
	public float getCollectedPoints() {
		return collectedPoints;
	}
	public void setCollectedPoints(float collectedPoints) {
		this.collectedPoints = collectedPoints;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public GenderEnum getGender() {
		return gender;
	}
	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}
	public RoleEnum getRole() {
		return role;
	}
	public void setRole(RoleEnum role) {
		this.role = role;
	}
	
	
	
	

}
