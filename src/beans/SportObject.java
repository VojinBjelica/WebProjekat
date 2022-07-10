package beans;

import java.awt.Image;

public class SportObject {
	
	private String objectName;
	private ObjectTypeEnum objectType;
	private String objectOffer; // sauna,grupni/personalni treninzi
	private boolean status; // radi/ne radi
	private Location location;
	private String logo;
	private double avarageMark;//prosecna ocena
	private String workHour;// radno vreme
	private int deleted;
	public SportObject() {
		super();
	}
	public SportObject(String objectName, ObjectTypeEnum objectType, String objectOffer, boolean status,
			Location location, String logo, double avarageMark, String workHour, int deleted) {
		super();
		this.objectName = objectName;
		this.objectType = objectType;
		this.objectOffer = objectOffer;
		this.status = status;
		this.location = location;
		this.logo = logo;
		this.avarageMark = avarageMark;
		this.workHour = workHour;
		this.deleted = deleted;
	}
	
	
	
	
	
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public ObjectTypeEnum getObjectType() {
		return objectType;
	}
	public void setObjectType(ObjectTypeEnum objectType) {
		this.objectType = objectType;
	}
	public String getObjectOffer() {
		return objectOffer;
	}
	public void setObjectOffer(String objectOffer) {
		this.objectOffer = objectOffer;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public double getAvarageMark() {
		return avarageMark;
	}
	public void setAvarageMark(double avarageMark) {
		this.avarageMark = avarageMark;
	}
	public String getWorkHour() {
		return workHour;
	}
	public void setWorkHour(String workHour) {
		this.workHour = workHour;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	
	

}
