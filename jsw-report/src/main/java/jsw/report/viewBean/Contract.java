package jsw.report.viewBean;

public class Contract {
	int id;
	String name,firstParty,party1,otherparty,from,to,isActive,binding;
	public String getBinding() {
		return binding;
	}
	public void setBinding(String binding) {
		this.binding = binding;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Contract() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contract(int id, String name, String firstParty, String party1, String otherparty, String from, String to) {
		super();
		this.id = id;
		this.name = name;
		this.firstParty = firstParty;
		this.party1 = party1;
		this.otherparty = otherparty;
		this.from = from;
		this.to = to;
	}
	public Contract(String to) {
		super();
		this.to = to;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstParty() {
		return firstParty;
	}
	public void setFirstParty(String firstParty) {
		this.firstParty = firstParty;
	}
	public String getParty1() {
		return party1;
	}
	public void setParty1(String party1) {
		this.party1 = party1;
	}
	public String getOtherparty() {
		return otherparty;
	}
	public void setOtherparty(String otherparty) {
		this.otherparty = otherparty;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

}
