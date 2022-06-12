package beans;

public class CustomerType {
	
	private CustomerType customerType;
	private int discount;
	private int neededPoints;
	public CustomerType(CustomerType customerType, int discount, int neededPoints) {
		super();
		this.customerType = customerType;
		this.discount = discount;
		this.neededPoints = neededPoints;
	}
	public CustomerType getCustomerType() {
		return customerType;
	}
	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getNeededPoints() {
		return neededPoints;
	}
	public void setNeededPoints(int neededPoints) {
		this.neededPoints = neededPoints;
	}
	
	
	

}
