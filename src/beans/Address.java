package beans;

public class Address {
	
	private String streetAndNumber;
	private String city;
	private String zipCode;
	public Address(String streetAndNumber, String city, String zipCode) {
		super();
		this.streetAndNumber = streetAndNumber;
		this.city = city;
		this.zipCode = zipCode;
	}
	public String getStreetAndNumber() {
		return streetAndNumber;
	}
	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	

}
