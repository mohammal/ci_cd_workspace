package jsw.report.viewBean;
// Generated Jan 4, 2017 6:13:39 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserBackup generated by hbm2java
 */
@Entity
@Table(name = "user_backup", catalog = "lapp")
public class UserBackup implements java.io.Serializable {

	private int id=0;
	private String userName;
	private String from;
	private String to;

	public UserBackup() {
	}

	public UserBackup(int id, String userName, String from, String to) {
		this.id = id;
		this.userName = userName;
		this.from = from;
		this.to = to;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "user_name", nullable = false, length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "from", nullable = false, length = 50)
	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@Column(name = "to", nullable = false, length = 50)
	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
