package beans;

public class TrainingsSearchDTO {
	
	private String name;
	private TrainingTypeEnum type;
	private String priceFrom;
	private String priceTo;
	public String getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(String priceFrom) {
		this.priceFrom = priceFrom;
	}
	public String getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(String priceTo) {
		this.priceTo = priceTo;
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
	public TrainingsSearchDTO(String name, TrainingTypeEnum type, String priceFrom, String priceTo) {
		super();
		this.name = name;
		this.type = type;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
	}
	
}
