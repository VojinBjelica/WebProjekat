package beans;

import java.time.LocalDate;
import java.util.Date;

public class TrainingHistory {
	
	private Date checkInDate;
	private Training training;
	private Customer customer;
	private Coach trainer;
	public TrainingHistory(Date checkInDateAndTime, Training training, Customer customer, Coach trainer) {
		super();
		this.checkInDate = checkInDateAndTime;
		this.training = training;
		this.customer = customer;
		this.trainer = trainer;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDateAndTime) {
		this.checkInDate = checkInDateAndTime;
	}
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Coach getTrainer() {
		return trainer;
	}
	public void setTrainer(Coach trainer) {
		this.trainer = trainer;
	}
	
	

}
