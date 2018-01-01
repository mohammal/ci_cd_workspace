package jsw.report.viewBean;
// Generated Jan 4, 2017 6:13:39 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Orders generated by hbm2java
 */
@Entity
@Table(name = "orders", catalog = "lapp")
public class Orders implements java.io.Serializable {

	private int id;
	private String customerName;
	private String orderName;
	public int quantity;
	public double cost;

	@Column(name = "quantity", nullable = false, length = 50)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Column(name = "cost", nullable = false, length = 50)
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Orders() {
	}

	

	public Orders(int id, String customerName, String orderName, int quantity, double cost) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.orderName = orderName;
		this.quantity = quantity;
		this.cost = cost;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "customer_name", nullable = false, length = 50)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "order_name", nullable = false, length = 50)
	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

}
