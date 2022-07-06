package beans;

public class Points {
	
	String userUsername;
	float points;
	public Points() {
		super();
	}
	public Points(String userUsername, float points) {
		super();
		this.userUsername = userUsername;
		this.points = points;
	}
	public String getUserUsername() {
		return userUsername;
	}
	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}
	public float getPoints() {
		return points;
	}
	public void setPoints(float points) {
		this.points = points;
	}
	
	

}
