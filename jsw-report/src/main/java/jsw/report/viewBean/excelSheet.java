package jsw.report.viewBean;

import java.io.File;

public class excelSheet {

	
	private int eid;
	private String excelname;
	private String excelPath;
	private String isImported;
	private String date;
	private String status;
	private String isActive;
private File myFile;
private String fileString;

	public String getFileString() {
	return fileString;
}
public void setFileString(String fileString) {
	this.fileString = fileString;
}
	public File getMyFile() {
	return myFile;
}
public void setMyFile(File myFile) {
	this.myFile = myFile;
}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getExcelname() {
		return excelname;
	}
	public void setExcelname(String excelname) {
		this.excelname = excelname;
	}
	public String getExcelPath() {
		return excelPath;
	}
	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}
	public String getIsImported() {
		return isImported;
	}
	public void setIsImported(String isImported) {
		this.isImported = isImported;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "excelSheet [eid=" + eid + ", excelname=" + excelname + ", excelPath=" + excelPath + ", isImported="
				+ isImported + ", date=" + date + "]";
	}
	
	
	
}
