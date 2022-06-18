package beans;

public class Comment {

	private Customer customer;
	private SportObject sportObject;
	private String text;
	private int mark;
	public Comment(Customer customer, SportObject sportObject, String text, int mark) {
		super();
		this.customer = customer;
		this.sportObject = sportObject;
		this.text = text;
		this.mark = mark;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public SportObject getSportObject() {
		return sportObject;
	}
	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	
}
