package beans;

import java.awt.Image;

public class Training {
	
	private String name;
	private TrainingTypeEnum type;
	private SportObject sportObject;
	private int duration;//u minutama
	private Coach trainer;// ako postoji
	private String description;
	private Image picture;
	
	public Training(String name, TrainingTypeEnum type, SportObject sportObject, int duration, Coach trainer,
			String description, Image picture) {
		super();
		this.name = name;
		this.type = type;
		this.sportObject = sportObject;
		this.duration = duration;
		this.trainer = trainer;
		this.description = description;
		this.picture = picture;
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

	public Image getPicture() {
		return picture;
	}

	public void setPicture(Image picture) {
		this.picture = picture;
	}
	
	
	
	
	

}