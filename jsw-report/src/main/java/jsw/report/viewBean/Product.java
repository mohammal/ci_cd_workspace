package jsw.report.viewBean;
// default package
// Generated Jan 31, 2017 3:12:43 PM by Hibernate Tools 3.4.0.CR1

/**
 * Product generated by hbm2java
 */
public class Product implements java.io.Serializable {

	private int id;
	private String productName;
	private double cost;

	private Double maxDiscount;

	private String discountDesc;
	private String fromDate;
	private String toDate;

	public Product() {
	}

	public Product(int id, double cost) {
		this.id = id;
		this.cost = cost;
	}

	public Product(int id, String productName, double cost,

	Double maxDiscount, String discountDescription, String fromDate, String toDate) {
       this.id = id;
       this.productName = productName;
       this.cost = cost;
       this.maxDiscount = maxDiscount;
       this.discountDesc = discountDescription;
       this.fromDate = fromDate;
       this.toDate = toDate;
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Double getMaxDiscount() {
        return this.maxDiscount;
    }

	public void setMaxDiscount(Double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

	public String getDiscountDesc() {
		return this.discountDesc;
	}

	public void setDiscountDesc(String discountDescription) {
		this.discountDesc = discountDescription;
	}

	public String getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return this.toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
