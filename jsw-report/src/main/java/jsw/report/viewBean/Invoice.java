package jsw.report.viewBean;
// default package
// Generated Jan 31, 2017 3:12:43 PM by Hibernate Tools 3.4.0.CR1

/**
 * Peyment generated by hbm2java
 */
public class Invoice implements java.io.Serializable {

	private Integer id=0;
	private Integer refId=0;
	private String amount;
	private String field1;
	private String field2;
	private String field3;
	private String isActive="n";
	private String isValidate="n";

	public Invoice() {
	}

	public Invoice(String isActive) {
		this.isActive = isActive;
	}

	public Invoice(Integer refId, String amount, String field1, String field2, String field3, String isActive,
			String isValidate) {
		this.refId = refId;
		this.amount = amount;
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
		this.isActive = isActive;
		this.isValidate = isValidate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRefId() {
		return this.refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getField1() {
		return this.field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return this.field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return this.field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsValidate() {
		return this.isValidate;
	}

	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", refId=" + refId + ", amount=" + amount + ", field1=" + field1 + ", field2="
				+ field2 + ", field3=" + field3 + ", isActive=" + isActive + ", isValidate=" + isValidate + "]";
	}

}
