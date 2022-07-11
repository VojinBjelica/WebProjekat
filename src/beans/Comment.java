package beans;

public class Comment {

	private Customer customer;
	private SportObject sportObject;
	private String text;
	private int mark;
	private int id;
	private int approved;
	public Comment(Customer customer, SportObject sportObject, String text, int mark, int approved,int id) {
		super();
		this.customer = customer;
		this.sportObject = sportObject;
		this.text = text;
		this.mark = mark;
		this.approved = approved;
		this.id = id;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public SportObject getSportObjectt() {
		return sportObject;
	}
	public void setSportObjectt(SportObject sportObject) {
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
