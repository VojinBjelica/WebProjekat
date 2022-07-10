package beans;

public class searchTrainingsOfObjectDTO {
	private String priceFrom;
	private String priceTo;
	private TrainingTypeEnum type;
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
	public TrainingTypeEnum getType() {
		return type;
	}
	public void setType(TrainingTypeEnum type) {
		this.type = type;
	}
	public searchTrainingsOfObjectDTO(String priceFrom, String priceTo, TrainingTypeEnum type) {
		super();
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.type = type;
	}
	
	
}
