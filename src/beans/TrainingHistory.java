package beans;

import java.time.LocalDate;

public class TrainingHistory {
	
	private LocalDate checkInDateAndTime;
	private Training training;
	private Customer customer;
	private Coach trainer;
	public TrainingHistory(LocalDate checkInDateAndTime, Training training, Customer customer, Coach trainer) {
		super();
		this.checkInDateAndTime = checkInDateAndTime;
		this.training = training;
		this.customer = customer;
		this.trainer = trainer;
	}
	public LocalDate getCheckInDateAndTime() {
		return checkInDateAndTime;
	}
	public void setCheckInDateAndTime(LocalDate checkInDateAndTime) {
		this.checkInDateAndTime = checkInDateAndTime;
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
