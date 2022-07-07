package beans;

import java.time.LocalDate;
import java.util.Date;

public class Dues {
	
	private String ID; // 10 karaktera, unique
	private DuesTypeEnum duesType;
	private Date payDate;
	private Date expirationDateAndTime;
	private int price;
	private Customer customer;
	private boolean status;//aktivna/neaktivna
	private int numberOfAppointments;//dnevni
	private String promoCode;
	private int done;
	public Dues(String iD, DuesTypeEnum duesType, Date payDate, Date expirationDateAndTime, int price,
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
	public Dues(String iD, DuesTypeEnum duesType, Date payDate, Date expirationDateAndTime, int price,
			Customer customer, boolean status, int numberOfAppointments,int done) {
		super();
		ID = iD;
		this.duesType = duesType;
		this.payDate = payDate;
		this.expirationDateAndTime = expirationDateAndTime;
		this.price = price;
		this.customer = customer;
		this.status = status;
		this.numberOfAppointments = numberOfAppointments;
		this.done = done;
	}
	public Dues(String iD, DuesTypeEnum duesType, Date payDate, Date expirationDateAndTime, int price,
			Customer customer, boolean status, int numberOfAppointments,String promoCode) {
		super();
		ID = iD;
		this.duesType = duesType;
		this.payDate = payDate;
		this.expirationDateAndTime = expirationDateAndTime;
		this.price = price;
		this.customer = customer;
		this.status = status;
		this.numberOfAppointments = numberOfAppointments;
		this.promoCode = promoCode;
	}
	
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	
	public int getDone() {
		return done;
	}
	public void setDone(int done) {
		this.done = done;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public DuesTypeEnum getDuesType() {
		return duesType;
	}
	public void setDuesType(DuesTypeEnum duesType) {
		this.duesType = duesType;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Date getExpirationDateAndTime() {
		return expirationDateAndTime;
	}
	public void setExpirationDateAndTime(Date expirationDateAndTime) {
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
