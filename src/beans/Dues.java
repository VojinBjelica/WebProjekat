package beans;

import java.time.LocalDate;

public class Dues {
	
	private char ID; // 10 karaktera, unique
	private DuesTypeEnum duesType;
	private LocalDate payDate;
	private LocalDate expirationDateAndTime;
	private int price;
	private Customer customer;
	private boolean status;//aktivna/neaktivna
	private int numberOfAppointments;//dnevni
	public Dues(char iD, DuesTypeEnum duesType, LocalDate payDate, LocalDate expirationDateAndTime, int price,
			Customer customer, boolean status, int numberOfAppointments) {
		super();
		ID = iD;
		this.duesType = duesType;
		this.payDate = payDate;
		this.expirationDateAndTime = expirationDateAndTime;
		this.price = price;
		this.customer = customer;
		this.status = status;
		this.numberOfAppointments = numberOfAppointments;
	}
	public char getID() {
		return ID;
	}
	public void setID(char iD) {
		ID = iD;
	}
	public DuesTypeEnum getDuesType() {
		return duesType;
	}
	public void setDuesType(DuesTypeEnum duesType) {
		this.duesType = duesType;
	}
	public LocalDate getPayDate() {
		return payDate;
	}
	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}
	public LocalDate getExpirationDateAndTime() {
		return expirationDateAndTime;
	}
	public void setExpirationDateAndTime(LocalDate expirationDateAndTime) {
		this.expirationDateAndTime = expirationDateAndTime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getNumberOfAppointments() {
		return numberOfAppointments;
	}
	public void setNumberOfAppointments(int numberOfAppointments) {
		this.numberOfAppointments = numberOfAppointments;
	}
	
	
	

}
