package beans;

import java.util.Date;

public class PromoCode {
	
	private String promoCodeName;
	private Date fromDate;
	private Date toDate;
	private int numberOfUsing;
	private int discount;
	private int used;
	public PromoCode() {
		super();
	}
	public PromoCode(Date fromDate, Date toDate, int numberOfUsing, int discount) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.numberOfUsing = numberOfUsing;
		this.discount = discount;
	}
	public PromoCode(Date fromDate, Date toDate, int numberOfUsing, int discount,int used) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.numberOfUsing = numberOfUsing;
		this.discount = discount;
		this.used = used;
	}
	public PromoCode(String name,Date fromDate, Date toDate, int numberOfUsing, int discount,int used) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.numberOfUsing = numberOfUsing;
		this.discount = discount;
		this.used = used;
		this.promoCodeName = name;
	}
	
	
	public String getPromoCodeName() {
		return promoCodeName;
	}
	public void setPromoCodeName(String promoCodeName) {
		this.promoCodeName = promoCodeName;
	}
	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public int getNumberOfUsing() {
		return numberOfUsing;
	}
	public void setNumberOfUsing(int numberOfUsing) {
		this.numberOfUsing = numberOfUsing;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	

}
