package beans;

public class CustomerType {
	
	private TypeName customerType;
	private int discount;
	private int neededPoints;
	public CustomerType(TypeName customerType, int discount, int neededPoints) {
		super();
		this.customerType = customerType;
		this.discount = discount;
		this.neededPoints = neededPoints;
	}
	public TypeName getCustomerType() {
		return customerType;
	}
	public void setCustomerType(TypeName customerType) {
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
