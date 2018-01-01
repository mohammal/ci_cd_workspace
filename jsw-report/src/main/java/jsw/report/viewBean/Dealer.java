package jsw.report.viewBean;
// Generated Jan 4, 2017 6:13:39 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dealer generated by hbm2java
 */
@Entity
@Table(name = "dealer", catalog = "lapp")
public class Dealer implements java.io.Serializable {

	private int id;
	private String name;
	private String location;
	
	private String contact;
	public String getPartnertype() {
		return partnertype;
	}

	public void setPartnertype(String partnertype) {
		this.partnertype = partnertype;
	}

	private String partnertype;
	

	public Dealer() {
	}

	public Dealer(int id) {
		this.id = id;
	}

	

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public Dealer(int id, String name, String location,String contact, String partnertype) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		
		this.contact = contact;
		this.partnertype = partnertype;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "location", length = 50)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	

	

	@Column(name = "contact", length = 50)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Dealer [id=" + id + ", name=" + name + ", location=" + location + ", contact=" + contact
				+ ", partnertype=" + partnertype + "]";
	}

	

}