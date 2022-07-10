package beans;

public class searchTrainersTrainingsDTO {
	private String soName;
	private ObjectTypeEnum soType;
	private String priceFrom;
	private String priceTo;
	private TrainingTypeEnum type;
	public String getSoName() {
		return soName;
	}
	public void setSoName(String soName) {
		this.soName = soName;
	}
	public ObjectTypeEnum getSoType() {
		return soType;
	}
	public void setSoType(ObjectTypeEnum soType) {
		this.soType = soType;
	}
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
	public searchTrainersTrainingsDTO(String soName, ObjectTypeEnum soType, String priceFrom, String priceTo,
			TrainingTypeEnum type) {
		super();
		this.soName = soName;
		this.soType = soType;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.type = type;
	}
	
	
}
