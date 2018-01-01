package jsw.report.viewBean;

public class Investment {
	int id;
	String name,investmentType,customerName,location,product,service,from,to,isActive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Investment(int id, String name, String investmentType, String customerName, String location, String product,
			String service, String from, String to, String isActive) {
		super();
		this.id = id;
		this.name = name;
		this.investmentType = investmentType;
		this.customerName = customerName;
		this.location = location;
		this.product = product;
		this.service = service;
		this.from = from;
		this.to = to;
		this.isActive = isActive;
	}
	public Investment() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Investment [id=" + id + ", name=" + name + ", investmentType=" + investmentType + ", customerName="
				+ customerName + ", location=" + location + ", product=" + product + ", service=" + service + ", from="
				+ from + ", to=" + to + ", isActive=" + isActive + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInvestmentType() {
		return investmentType;
	}
	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
