package jsw.report.viewBean;

public class AssociateList {
	int id,entityRef_no,orderRef_no;
	String amount,taxAmount,filename,isvalid,isActive,entityType;
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "AssociateList [id=" + id + ", entityRef_no=" + entityRef_no + ", orderRef_no=" + orderRef_no
				+ ", amount=" + amount + ", taxAmount=" + taxAmount + ", filename=" + filename + ", isvalid=" + isvalid
				+ ", isActive=" + isActive + "]";
	}
	public AssociateList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AssociateList(int id, int entityRef_no, int orderRef_no, String amount, String taxAmount, String filename,
			String isvalid, String isActive) {
		super();
		this.id = id;
		this.entityRef_no = entityRef_no;
		this.orderRef_no = orderRef_no;
		this.amount = amount;
		this.taxAmount = taxAmount;
		this.filename = filename;
		this.isvalid = isvalid;
		this.isActive = isActive;
	}
	public int getEntityRef_no() {
		return entityRef_no;
	}
	public void setEntityRef_no(int entityRef_no) {
		this.entityRef_no = entityRef_no;
	}
	public int getOrderRef_no() {
		return orderRef_no;
	}
	public void setOrderRef_no(int orderRef_no) {
		this.orderRef_no = orderRef_no;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}
