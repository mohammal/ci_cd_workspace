package jsw.report.viewBean;

import javax.persistence.Column;
import javax.persistence.Id;

public class Customer {
	private int id;
	private String name;
	private String location;
	private String address;
	private String contact;

	public Customer() {
	}

	public Customer(int id) {
		this.id = id;
	}

	public Customer(int id, String name, String location, String address, String contact) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.address = address;
		this.contact = contact;
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

	@Column(name = "address", length = 50)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "Customer [id=" + id + ", name=" + name + ", location=" + location + ", address=" + address + ", contact="
				+ contact + "]";
	}

}
