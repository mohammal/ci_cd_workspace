package jsw.report.viewBean;

public class InvestmentType {
	int id;
	String name,isActive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public InvestmentType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InvestmentType(int id, String name, String isActive) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "InvestmentType [id=" + id + ", name=" + name + ", isActive=" + isActive + "]";
	}
	

}
