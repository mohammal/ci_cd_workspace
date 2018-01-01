package jsw.report.viewBean;

public class functionTable {

	private String location,company,name,count,period1,period2,period3,period4,payrollarea;

	public String getPeriod1() {
		return period1;
	}

	public void setPeriod1(String period1) {
		this.period1 = period1;
	}

	public String getPeriod2() {
		return period2;
	}

	public void setPeriod2(String period2) {
		this.period2 = period2;
	}

	public String getPeriod3() {
		return period3;
	}

	public void setPeriod3(String period3) {
		this.period3 = period3;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeriod4() {
		return period4;
	}

	public void setPeriod4(String period4) {
		this.period4 = period4;
	}

	public String getPayrollarea() {
		return payrollarea;
	}

	public void setPayrollarea(String payrollarea) {
		this.payrollarea = payrollarea;
	}

	@Override
	public String toString() {
		return "functionTable [location=" + location + ", company=" + company
				+ ", name=" + name + ", count=" + count + ", period1="
				+ period1 + ", period2=" + period2 + ", period3=" + period3
				+ ", period4=" + period4 + ", payrollarea=" + payrollarea + "]";
	}
	
	
	
	
	
}
