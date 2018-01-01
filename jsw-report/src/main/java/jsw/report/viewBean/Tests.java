package jsw.report.viewBean;
// default package
// Generated Dec 28, 2016 2:54:52 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tests generated by hbm2java
 */
@Entity
@Table(name = "tests", catalog = "volkswagen")
public class Tests implements java.io.Serializable {

	private Integer testId;
	private String testName;
	private String testCondition;
	private String startDate;
	private String endDate;
	private String duration;
	private String machineNo;

	public Tests() {
	}

	public Tests(String testName, String testCondition, String startDate, String endDate, String duration,
			String machineNo) {
		this.testName = testName;
		this.testCondition = testCondition;
		this.startDate = startDate;
		this.endDate = endDate;
		this.duration = duration;
		this.machineNo = machineNo;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "test_id", unique = true, nullable = false)
	public Integer getTestId() {
		return this.testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	@Column(name = "test_name", nullable = false, length = 50)
	public String getTestName() {
		return this.testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	@Column(name = "test_condition", nullable = false, length = 50)
	public String getTestCondition() {
		return this.testCondition;
	}

	public void setTestCondition(String testCondition) {
		this.testCondition = testCondition;
	}

	@Column(name = "startDate", nullable = false, length = 50)
	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name = "endDate", nullable = false, length = 50)
	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "duration", nullable = false, length = 50)
	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Column(name = "machine_no", nullable = false, length = 50)
	public String getMachineNo() {
		return this.machineNo;
	}

	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}

	@Override
	public String toString() {
		return "Tests [testId=" + testId + ", testName=" + testName + ", testCondition=" + testCondition
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", duration=" + duration + ", machineNo="
				+ machineNo + "]";
	}

}
