package beans;

import java.awt.Image;
import java.time.LocalDate;
import java.util.Date;

public class Training {
	
	private String name;
	private TrainingTypeEnum type;
	private SportObject sportObject;
	private int duration;//u minutama
	private Coach trainer;// ako postoji
	private String description;
	private String picture;
	private Date trainingDate;
	private int id;
	private int deleted;
	private double price;
	private int check;
	private int cancel;
	
	
	public Training(String name, TrainingTypeEnum type, SportObject sportObject, int duration, Coach trainer,
			String description) {
		super();
		this.name = name;
		this.type = type;
		this.sportObject = sportObject;
		this.duration = duration;
		this.trainer = trainer;
		this.description = description;
	}

	public Training(String name, TrainingTypeEnum type, SportObject sportObject, int duration, Coach trainer,
			String description, String picture) {
		super();
		this.name = name;
		this.type = type;
		this.sportObject = sportObject;
		this.duration = duration;
		this.trainer = trainer;
		this.description = description;
		this.picture = picture;
	}
	public Training(String name, TrainingTypeEnum type, SportObject sportObject, int duration, Coach trainer,
			String description,Date date,int id,int deleted) {
		super();
		this.name = name;
		this.type = type;
		this.sportObject = sportObject;
		this.duration = duration;
		this.trainer = trainer;
		this.description = description;
		this.picture = picture;
		this.trainingDate = date;
		this.id = id;
		this.deleted = deleted;
	}
	
	public Training(String name, TrainingTypeEnum type, SportObject sportObject, int duration, Coach trainer,
			String description, String picture, Date date,int id,int deleted, double price) {
		super();
		this.name = name;
		this.type = type;
		this.sportObject = sportObject;
		this.duration = duration;
		this.trainer = trainer;
		this.description = description;
		this.picture = picture;
		this.trainingDate = date;
		this.id = id;
		this.deleted = deleted;
		this.price = price;
	}
	public Training(String name, TrainingTypeEnum type, SportObject sportObject, int duration, Coach trainer,
			String description, String picture, Date date,int id,int deleted, double price,int check) {
		super();
		this.name = name;
		this.type = type;
		this.sportObject = sportObject;
		this.duration = duration;
		this.trainer = trainer;
		this.description = description;
		this.picture = picture;
		this.trainingDate = date;
		this.id = id;
		this.deleted = deleted;
		this.price = price;
		this.check = check;
	}
	
	public Training(String name, TrainingTypeEnum type, SportObject sportObject, int duration, Coach trainer,
			String description, String picture, Date date,int id,int deleted, double price,int check,int cancel) {
		super();
		this.name = name;
		this.type = type;
		this.sportObject = sportObject;
		this.duration = duration;
		this.trainer = trainer;
		this.description = description;
		this.picture = picture;
		this.trainingDate = date;
		this.id = id;
		this.deleted = deleted;
		this.price = price;
		this.check = check;
		this.cancel = cancel;
	}
	
	public Training() {}

	
	
	
	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TrainingTypeEnum getType() {
		return type;
	}

	public void setType(TrainingTypeEnum type) {
		this.type = type;
	}

	public SportObject getSportObject() {
		return sportObject;
	}

	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Coach getTrainer() {
		return trainer;
	}

	public void setTrainer(Coach trainer) {
		this.trainer = trainer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	
	
	
	
	
	
	
	

}
