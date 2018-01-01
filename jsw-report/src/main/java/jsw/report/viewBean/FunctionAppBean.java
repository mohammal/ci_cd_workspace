package jsw.report.viewBean;

import java.sql.Date;

public class FunctionAppBean {

	
	private int id;
	private String Business,Location,DisplayBy,Roles,Users,CaseType,DocType;
	private String fromDate,toDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusiness() {
		return Business;
	}
	public void setBusiness(String business) {
		Business = business;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getDisplayBy() {
		return DisplayBy;
	}
	public void setDisplayBy(String displayBy) {
		DisplayBy = displayBy;
	}
	public String getRoles() {
		return Roles;
	}
	public void setRoles(String roles) {
		Roles = roles;
	}
	public String getUsers() {
		return Users;
	}
	public void setUsers(String users) {
		Users = users;
	}
	public String getCaseType() {
		return CaseType;
	}
	public void setCaseType(String caseType) {
		CaseType = caseType;
	}
	public String getDocType() {
		return DocType;
	}
	public void setDocType(String docType) {
		DocType = docType;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	@Override
	public String toString() {
		return "FunctionAppBean [id=" + id + ", Business=" + Business
				+ ", Location=" + Location + ", DisplayBy=" + DisplayBy
				+ ", Roles=" + Roles + ", Users=" + Users + ", CaseType="
				+ CaseType + ", DocType=" + DocType + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + "]";
	}
	
	
}
