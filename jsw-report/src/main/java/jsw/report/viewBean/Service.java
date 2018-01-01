package jsw.report.viewBean;

public class Service {
	int id;
	String serviceName,serviceCoast,customerName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Service [id=" + id + ", serviceName=" + serviceName + ", serviceCoast=" + serviceCoast + "]";
	}
	public Service(int id, String serviceName, String serviceCoast) {
		super();
		this.id = id;
		this.serviceName = serviceName;
		this.serviceCoast = serviceCoast;
	}
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceCoast() {
		return serviceCoast;
	}
	public void setServiceCoast(String serviceCoast) {
		this.serviceCoast = serviceCoast;
	}

}
