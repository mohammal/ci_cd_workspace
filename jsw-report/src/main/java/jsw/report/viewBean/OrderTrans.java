package jsw.report.viewBean;
// default package
// Generated Jan 31, 2017 3:12:43 PM by Hibernate Tools 3.4.0.CR1

/**
 * Peyment generated by hbm2java
 */
public class OrderTrans implements java.io.Serializable {

	private Integer id=0;
	private Integer refId=0;
	private String amount;
	private String field1;
	private String field2;
	private String field3;
	private String field4;
	private String field5;
	private String field6;
	private String field7;
	private String field8;
	private String field9;
	private String isActive="n";
	private String isValidate="n";

	public OrderTrans() {
	}

	public OrderTrans(String isActive) {
		this.isActive = isActive;
	}

	public OrderTrans(Integer refId, String amount, String field1, String field2, String field3, String isActive,
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

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getField6() {
		return field6;
	}

	public void setField6(String field6) {
		this.field6 = field6;
	}

	public String getField7() {
		return field7;
	}

	public void setField7(String field7) {
		this.field7 = field7;
	}

	public String getField8() {
		return field8;
	}

	public void setField8(String field8) {
		this.field8 = field8;
	}

	public String getField9() {
		return field9;
	}

	public void setField9(String field9) {
		this.field9 = field9;
	}

}
