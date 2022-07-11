package beans;

import java.util.Date;

public class searchTrainingsForCustomerDTO {
	private String soName;
	private String priceFrom;
	private String priceTo;
	private Date dateFrom;
	private Date dateTo;
	private ObjectTypeEnum soType;
	private TrainingTypeEnum type;
	public String getSoName() {
		return soName;
	}
	public void setSoName(String soName) {
		this.soName = soName;
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
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public ObjectTypeEnum getSoType() {
		return soType;
	}
	public void setSoType(ObjectTypeEnum soType) {
		this.soType = soType;
	}
	public TrainingTypeEnum getType() {
		return type;
	}
	public void setType(TrainingTypeEnum type) {
		this.type = type;
	}
	public searchTrainingsForCustomerDTO(String soName, String priceFrom, String priceTo, Date dateFrom, Date dateTo,
			ObjectTypeEnum soType, TrainingTypeEnum type) {
		super();
		this.soName = soName;
		this.priceFrom = priceFrom;
		this.priceTo = priceTo;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.soType = soType;
		this.type = type;
	}
	
	
	
}
