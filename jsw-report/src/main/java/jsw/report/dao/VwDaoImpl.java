package jsw.report.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jsw.report.viewBean.*;

public class VwDaoImpl implements VwDao {
	DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// *************Add User*************
	public boolean addUser(RegistrationBean regis) throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		String sql = "insert into users(username,password,email) values(?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, regis.getUsename());
		p.setString(2, regis.getPassword());
		p.setString(3, regis.getEmail());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}
	// **************End ***********

	public boolean updateTest(Tests test) throws SQLException {
		System.out.println(test.getTestId());
		int row = 0;
		String sql = "update tests set test_name=?,test_condition=?,startDate=?,endDate=?,duration=?,machine_test=? where test_id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, test.getTestName());
		p.setString(2, test.getTestCondition());
		p.setString(3, test.getStartDate());
		p.setString(4, test.getEndDate());
		p.setString(5, test.getDuration());
		p.setString(6, test.getMachineNo());
		p.setInt(7, test.getTestId());
		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean deleteTest(int id) throws SQLException {

		int row = 0;
		String sql = "delete from tests where test_id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, id);

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean addTest(Tests Test) throws SQLException {

		int row = 0;
		String sql = "insert into tests(test_name,test_condition,startDate,endDate,duration,machine_no) values(?,?,?,?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, Test.getTestName());
		p.setString(2, Test.getTestCondition());
		p.setString(3, Test.getStartDate());
		p.setString(4, Test.getEndDate());
		p.setString(5, Test.getDuration());
		p.setString(6, Test.getMachineNo());
		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public List getTest() throws SQLException {
		String query = "select * from tests";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Tests> ls = new ArrayList<Tests>();
		while (resultSet.next()) {

			Tests lg = new Tests();
			lg.setTestId(resultSet.getInt("test_id"));
			lg.setTestName(resultSet.getString("test_name"));
			lg.setTestCondition(resultSet.getString("test_condition"));
			lg.setStartDate(resultSet.getString("startDate"));
			lg.setEndDate(resultSet.getString("endDate"));
			lg.setDuration(resultSet.getString("duration"));
			lg.setMachineNo(resultSet.getString("machine_no"));
			ls.add(lg);
		}
		System.out.println(ls);

		return ls;

	}

	public List getRole() throws SQLException {

		String query1 = "select * from menutable";

		PreparedStatement pstmt1 = dataSource.getConnection().prepareStatement(query1);
		ResultSet resultSet1 = pstmt1.executeQuery();
		int count = 0;
		while (resultSet1.next()) {
			count++;
		}
		System.out.println("%%%%%" + count);
		String names[] = new String[count + 1];
		int len = count;
		count = 0;
		resultSet1 = pstmt1.executeQuery();

		while (resultSet1.next()) {

			names[count] = resultSet1.getString("menu_name");
			System.out.println(names[count++]);

		}

		String query = "select * from role";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Role> ls = new ArrayList<Role>();
		while (resultSet.next()) {

			Role lg = new Role();
			lg.setId(resultSet.getInt("id"));
			lg.setRoleName(resultSet.getString("role_name"));
			lg.setRoleDescription(resultSet.getString("role_description"));
			lg.setIsActive(resultSet.getString("isActive"));
			String screenID = resultSet.getString("screenId");
			String screen = "";
			String[] ary = screenID.split(",");
			System.out.println(ary.length);
			int first = 0;

			for (int k = 0; k < ary.length; k++) {
				
				System.out.println(names[Integer.parseInt(ary[k])]);

				if (k == 0)
					screen += names[Integer.parseInt(ary[k])];
				else
					screen += "," + names[Integer.parseInt(ary[k])];

			}

			/*
			 * for(int outerloop=0;outerloop<len;outerloop++) { int temp=0;
			 * for(int k=0;k<ary.length;k++) {
			 * 
			 * System.out.println(k+"$$$$$"+ary[k]);
			 * if(Integer.parseInt(ary[k])==outerloop){
			 * System.out.println("****"+names[k]); temp++; break; }
			 * 
			 * }
			 * 
			 * 
			 * 
			 * System.out.println("@@@@"+screen);
			 */
			lg.setScreenId(screen);
			ls.add(lg);
		}

		System.out.println(ls);

		return ls;

	}
	public Tests editTest(int id) throws SQLException {
		String query = "select * from tests where test_id=?";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		Tests lg = new Tests();
		while (resultSet.next()) {

			lg.setTestId(resultSet.getInt("test_id"));
			lg.setTestName(resultSet.getString("test_name"));
			lg.setTestCondition(resultSet.getString("test_condition"));
			lg.setStartDate(resultSet.getString("startDate"));
			lg.setEndDate(resultSet.getString("endDate"));
			lg.setDuration(resultSet.getString("duration"));
			lg.setMachineNo(resultSet.getString("machine_no"));

		}
		System.out.println(lg);

		return lg;

	}

	public List<RegistrationBean> getAllUsers() throws SQLException {
		String query = "select * from users where Active='1'";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<RegistrationBean> ls = new ArrayList<RegistrationBean>();
		while (resultSet.next()) {
			RegistrationBean lg = new RegistrationBean();
			lg.setUsename(resultSet.getString("username"));
			lg.setPassword(resultSet.getString("password"));
			lg.setName(resultSet.getString("name"));
			lg.setEmail(resultSet.getString("email"));
lg.setDashboard(resultSet.getString("dashboard"));
lg.setActor(resultSet.getString("actor"));
lg.setExpiryDate(resultSet.getString("Expiry_date"));
lg.setRole(resultSet.getString("role"));
			lg.setId(resultSet.getInt("id"));
			ls.add(lg);
		}
		System.out.println("lllll" + ls);

		return ls;

	}

	public JSONArray getMenuTree() throws Exception {
		String query = "select menu_name,menu_id from menutable where sub_menu=?";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, "null");
		ResultSet resultSet = pstmt.executeQuery();
		int no = 0;

		while (resultSet.next()) {

		}
		resultSet = pstmt.executeQuery();

		JSONArray ls = new JSONArray();
		JSONObject ljs = new JSONObject();
		JSONObject temp = null;
		while (resultSet.next()) {
			temp = new JSONObject();
			temp.put("zid",  resultSet.getString("menu_id"));
			temp.put("pid", "null");
			temp.put("value", resultSet.getString("menu_name"));
			ls.put(temp);

		}
		ljs.put("L1", ls);
		JSONArray ls1 = new JSONArray();

		for (int i = 0; i < ls.length(); i++) {
			// System.out.println(str[size++]+" abcd");
			String query1 = "select menu_name,menu_id from menutable where sub_menu=?";
			PreparedStatement pstmt1 = dataSource.getConnection().prepareStatement(query1);
			pstmt1.setString(1, new JSONObject(ls.getString(i)).getString("value"));
			ResultSet resultSet1 = pstmt1.executeQuery();

			JSONObject temp1 = null;
			while (resultSet1.next()) {
				temp1 = new JSONObject();

				temp1.put("zid", resultSet1.getString("menu_id"));
				temp1.put("pid", new JSONObject(ls.getString(i)).getString("zid"));
				temp1.put("value", resultSet1.getString("menu_name"));
				ls1.put(temp1);
			}
			ljs.put("L2", ls1);
			pstmt1.close();
			resultSet1.close();
		}

		JSONArray ls2 = new JSONArray();
		for (int i = 0; i < ls1.length(); i++) {
			// System.out.println(new
			// JSONObject(ls1.getString(i)).getString("value")+" abcd");
			String query1 = "select menu_name,menu_id from menutable where sub_menu=?";
			PreparedStatement pstmt1 = dataSource.getConnection().prepareStatement(query1);
			pstmt1.setString(1, new JSONObject(ls1.getString(i)).getString("value"));
			ResultSet resultSet1 = pstmt1.executeQuery();

			JSONObject temp1 = null;
			while (resultSet1.next()) {
				temp1 = new JSONObject();

				temp1.put("zid", resultSet1.getString("menu_id"));
				temp1.put("pid", new JSONObject(ls1.getString(i)).getString("zid"));
				temp1.put("value", resultSet1.getString("menu_name"));
				ls2.put(temp1);
			}
			ljs.put("L3", ls2);
			pstmt1.close();
			resultSet1.close();
		}

		JSONArray finalJson = new JSONArray();

		finalJson.put(ljs);

		System.out.println(ls);

		return finalJson;
	}

	public List<String> getUserName() throws SQLException {
		String query = "select * from users";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<String> ls = new ArrayList<String>();
		while (resultSet.next()) {
			ls.add((String) resultSet.getString("username"));

		}
		System.out.println(ls);

		return ls;

	}

	public boolean updateScreenID(RegistrationBean screenID) throws SQLException {

		int row = 0;
		String sql = "update users set role=? where username=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, screenID.getRole());
		p.setString(2, screenID.getUsename());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}
	public boolean updateDashboard(RegistrationBean dashboard) throws SQLException {

		int row = 0;
		String sql = "update users set dashboard=? where username=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, dashboard.getDashboard());
		p.setString(2, dashboard.getUsename());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}
	public boolean addRole(Role role) throws SQLException {

		int row = 0;
		String sql = "insert into role(role_name,role_description,isActive,screenId) values(?,?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, role.getRoleName());
		p.setString(2, role.getRoleDescription());
		p.setString(3, role.getIsActive());
		p.setString(4, role.getScreenId());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	/// *****************Dealer***********

	public boolean addDealer(Dealer test) throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		String sql = "insert into dealer(name,location,address,contact) values(?,?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, test.getName());
		p.setString(2, test.getLocation());
		p.setString(3, test.getLocation());
		p.setString(4, test.getContact());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;
	}

	public List getDealer() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from dealer";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Dealer> ls = new ArrayList<Dealer>();
		while (resultSet.next()) {

			Dealer lg = new Dealer();
			lg.setId(resultSet.getInt("id"));
			lg.setName(resultSet.getString("name"));
			lg.setLocation(resultSet.getString("location"));
			lg.setPartnertype(resultSet.getString("partnerType"));

			lg.setContact(resultSet.getString("contact"));
			ls.add(lg);
		}
		System.out.println(ls);

		return ls;

	}

	public boolean deleteDealerList(int id) throws SQLException {
		System.out.println("id" + id);
		int row = 0;
		String sql = "delete from dealer where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, id);

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public List getDealerById(int id) throws SQLException {
		String query = "select * from dealer where id=?";

		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		List<Dealer> ls = new ArrayList<Dealer>();
		Dealer kp = new Dealer();
		while (resultSet.next()) {

			kp.setId(resultSet.getInt("id"));
			kp.setName(resultSet.getString("name"));
			kp.setPartnertype(resultSet.getString("partnerType"));
			kp.setLocation(resultSet.getString("location"));
			kp.setContact(resultSet.getString("contact"));

		}

		ls.add(kp);

		return ls;

	}

	public boolean updateDealer(Dealer stage) throws SQLException {

		int row = 0;

		String sql = "update dealer set name=?,location=?,address=?,contact=? where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, stage.getName());
		p.setString(2, stage.getPartnertype());
		p.setString(3, stage.getLocation());

		p.setString(4, stage.getContact());
		p.setInt(5, stage.getId());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	// **************End***********
	// ***************Product********
	public List getProduct() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from product";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Product> ls = new ArrayList<Product>();
		while (resultSet.next()) {

			Product lg = new Product();
			lg.setId(resultSet.getInt("id"));
			lg.setProductName(resultSet.getString("product_name"));
			lg.setCost(resultSet.getDouble("cost"));
			lg.setMaxDiscount(resultSet.getDouble("max_discount(%)"));
			lg.setFromDate(resultSet.getString("FromDate"));
			lg.setToDate(resultSet.getString("ToDate"));
			lg.setDiscountDesc(resultSet.getString("Discount_Description"));

			ls.add(lg);
		}
		System.out.println(ls);

		return ls;
	}

	public boolean addOrder(Orders order) throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		String sql = "insert into orders (customer_name,order_name) values(?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, order.getCustomerName());
		p.setString(2, order.getOrderName());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;
	}

	public List getOrder() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from orders";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Orders> ls = new ArrayList<Orders>();
		while (resultSet.next()) {

			Orders lg = new Orders();
			lg.setId(resultSet.getInt("id"));
			lg.setCustomerName(resultSet.getString(2));
			lg.setOrderName(resultSet.getString(3));

			ls.add(lg);
		}
		System.out.println(ls);

		return ls;
	}

	public boolean deleteOrderList(int id) throws SQLException {
		System.out.println("id" + id);
		int row = 0;
		String sql = "delete from orders where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, id);

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public List getOrderById(int id) throws SQLException {
		String query = "select * from orders where id=?";

		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		List<Orders> ls = new ArrayList<Orders>();
		Orders kp = new Orders();
		while (resultSet.next()) {

			kp.setId(resultSet.getInt("id"));
			kp.setCustomerName(resultSet.getString("customer_name"));
			kp.setOrderName(resultSet.getString("order_name"));

		}

		ls.add(kp);

		return ls;

	}

	public boolean updateOrder(Orders stage) throws SQLException {

		int row = 0;

		String sql = "update orders set customer_name=?,order_name=? where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, stage.getCustomerName());
		p.setString(2, stage.getOrderName());

		p.setInt(3, stage.getId());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	// ***************End*********

	// ***************Service********
	public List getService() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from service";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Service> ls = new ArrayList<Service>();
		while (resultSet.next()) {

			Service lg = new Service();
			lg.setId(resultSet.getInt("id"));
			lg.setServiceName(resultSet.getString("service_name"));
			lg.setServiceCoast(resultSet.getString("service_cost"));

			ls.add(lg);
		}

		return ls;
	}

	public boolean addService(Service order) throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		String sql = "insert into service (service_name,service_cost) values(?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, order.getServiceName());
		p.setString(2, order.getServiceCoast());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;
	}

	public boolean deleteServiceList(int id) throws SQLException {
		System.out.println("id" + id);
		int row = 0;
		String sql = "delete from service where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, id);

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public List getServiceById(int id) throws SQLException {
		String query = "select * from service where id=?";

		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		List<Service> ls = new ArrayList<Service>();
		Service kp = new Service();
		while (resultSet.next()) {

			kp.setId(resultSet.getInt("id"));
			kp.setServiceName(resultSet.getString("service_name"));
			kp.setServiceCoast(resultSet.getString("service_cost"));

		}

		ls.add(kp);

		return ls;

	}

	public boolean updateService(Service stage) throws SQLException {

		int row = 0;

		String sql = "update service set service_name=?,service_cost=? where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, stage.getServiceName());
		p.setString(2, stage.getServiceCoast());

		p.setInt(3, stage.getId());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	// ***************End*********
	// **************Customer***********

	public List getCustomer() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from customer";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Customer> ls = new ArrayList<Customer>();
		while (resultSet.next()) {

			Customer lg = new Customer();
			lg.setId(resultSet.getInt("id"));
			lg.setName(resultSet.getString("name"));
			lg.setLocation(resultSet.getString("location"));
			lg.setLocation(resultSet.getString("address"));
			lg.setContact(resultSet.getString("contact"));
			ls.add(lg);
		}
		System.out.println(ls);

		return ls;

	}

	public boolean addCustomer(Customer test) throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		String sql = "insert into customer(name,location,address,contact) values(?,?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, test.getName());
		p.setString(2, test.getLocation());
		p.setString(3, test.getAddress());
		p.setString(4, test.getContact());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;
	}

	// *************End*************
	// ******************Schem************
	public boolean addDiscountOnMaterials(Scheme discount) throws SQLException {
		int row = 0;
		String sql = "insert into discount(id,type,product,discount_desc,discount,from_date,to_date,isActive) values(?,?,?,?,?,?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);

		p.setInt(1, discount.getId());
		p.setString(2, discount.getType());
		p.setString(3, discount.getProductName());
		p.setString(4, discount.getDescription());
		p.setString(5, discount.getDiscount());

		p.setString(6, discount.getFromDate());
		p.setString(7, discount.getToDate());
		p.setString(8, discount.getIsActive());
		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;
	}

	public List getDiscountOnMaterials() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from discount";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Scheme> ls = new ArrayList<Scheme>();

		while (resultSet.next()) {
			Scheme lg = new Scheme();

			lg.setId(resultSet.getInt("id"));
			lg.setType(resultSet.getString("type"));
			lg.setProductName(resultSet.getString("product"));
			lg.setDescription(resultSet.getString("discount_desc"));
			lg.setDiscount(resultSet.getString("discount"));

			lg.setFromDate(resultSet.getString("from_date"));
			lg.setToDate(resultSet.getString("to_date"));
			lg.setIsActive(resultSet.getString("isActive"));
			ls.add(lg);
		}
		System.out.println(ls);

		return ls;
	}

	public List getDiscountById(int id) throws SQLException {

		String query = "select * from discount where id=?";

		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		List<Scheme> ls = new ArrayList<Scheme>();
		Scheme lg = new Scheme();
		while (resultSet.next()) {

			lg.setId(resultSet.getInt("id"));
			lg.setType(resultSet.getString("type"));
			lg.setProductName(resultSet.getString("product"));

			lg.setDescription(resultSet.getString("discount_desc"));
			lg.setDiscount(resultSet.getString("discount"));

			lg.setFromDate(resultSet.getString("from_date"));
			lg.setToDate(resultSet.getString("to_date"));
			lg.setIsActive(resultSet.getString("isActive"));

		}

		ls.add(lg);

		return ls;
	}

	// **********End*********

	// ************Contract**********

	public List getContract() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from contract";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Contract> ls = new ArrayList<Contract>();

		while (resultSet.next()) {
			Contract lg = new Contract();

			lg.setId(resultSet.getInt("id"));
			lg.setBinding(resultSet.getString("binding"));
			lg.setName(resultSet.getString("name"));
			lg.setFirstParty(resultSet.getString("firstParty"));
			lg.setParty1(resultSet.getString("Party1"));
			lg.setOtherparty(resultSet.getString("OtherParty"));

			lg.setFrom(resultSet.getString("from_date"));
			lg.setTo(resultSet.getString("to_date"));
			lg.setIsActive(resultSet.getString("isActive"));
			ls.add(lg);
		}
		System.out.println(ls);

		return ls;
	}

	// ***********ENd************

	// *************Investment*******

	public List getInvestment() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from investment";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Investment> ls = new ArrayList<Investment>();

		while (resultSet.next()) {
			Investment lg = new Investment();

			lg.setId(resultSet.getInt("id"));
			lg.setInvestmentType(resultSet.getString("Investment_Type"));
			lg.setCustomerName(resultSet.getString("Customer"));
			lg.setLocation(resultSet.getString("location"));
			lg.setProduct(resultSet.getString("Product"));
			lg.setService(resultSet.getString("Service"));

			lg.setFrom(resultSet.getString("from_date"));
			lg.setTo(resultSet.getString("to_date"));
			lg.setIsActive(resultSet.getString("isActive"));
			ls.add(lg);
		}
		System.out.println(ls);

		return ls;
	}

	// ************End************

	// *********FileName storage to database******
		

		
		
		// ********* end FileName storage to database******

		// ************List Of File********

		public List getFileList() throws SQLException {
			// TODO Auto-generated method stub
			String query = "select * from fileAttachement";

			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			ResultSet resultSet = pstmt.executeQuery();

			List<FileList> ls = new ArrayList();

			while (resultSet.next()) {
				FileList stg = new FileList();

				stg.setId(resultSet.getInt("id"));
				stg.setFileName(resultSet.getString("FileName"));
				stg.setCreatedTimeStamp(resultSet.getDate("CreatedTimeStamp"));
				stg.setStatus(resultSet.getInt("Status"));
				System.out.println("comming" + stg.getFileName());

				ls.add(stg);

			}

			return ls;

		}

		public String getFileById(int id) throws SQLException {

			String query = "select FileName from fileAttachement where id=?";

			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet resultSet = pstmt.executeQuery();

			String s = "";

			while (resultSet.next()) {

				s = resultSet.getString(1);
				System.out.println("ooooo" + s);

			}

			return s;
		}

		public boolean deleteFileList(int id) throws SQLException {
			System.out.println("id" + id);
			int row = 0;
			String sql = "delete from fileAttachement where id=?";
			PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
			p.setInt(1, id);

			row = p.executeUpdate();

			if (row > 0)
				return true;
			else
				return false;

		}

		// ************End List Of File********


	// *************Document Type***********
	public List getDocumentList() throws SQLException {
		String query = "select * from documentType";

		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<DocumentType> ls = new ArrayList();

		while (resultSet.next()) {
			DocumentType stg = new DocumentType();

			stg.setId(resultSet.getInt("id"));
			stg.setName(resultSet.getString("Name"));
			stg.setIsActive(resultSet.getString("IsActive"));

			ls.add(stg);

		}

		return ls;

	}

	public boolean addDocumentType(DocumentType regis) throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		String sql = "insert into documentType(Name,IsActive) values(?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, regis.getName());
		p.setString(2, regis.getIsActive());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	// ***********End******************

	// *************Investment Type***********
	public List getInvestmentList() throws SQLException {
		String query = "select * from investmentType";

		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<InvestmentType> ls = new ArrayList();

		while (resultSet.next()) {
			InvestmentType stg = new InvestmentType();

			stg.setId(resultSet.getInt("id"));
			stg.setName(resultSet.getString("Name"));
			stg.setIsActive(resultSet.getString("IsActive"));

			ls.add(stg);

		}

		return ls;

	}

	public boolean addInvestmentType(InvestmentType regis) throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		String sql = "insert into investmentType(Name,IsActive) values(?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, regis.getName());
		p.setString(2, regis.getIsActive());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	// ***********End******************

	// *************Binding Type***********
	public List getBindingList() throws SQLException {
		String query = "select * from bindingType";

		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Binding> ls = new ArrayList();

		while (resultSet.next()) {
			Binding stg = new Binding();

			stg.setId(resultSet.getInt("id"));
			stg.setName(resultSet.getString("Name"));
			stg.setIsActive(resultSet.getString("IsActive"));

			ls.add(stg);

		}

		return ls;

	}

	public boolean addBindingType(Binding regis) throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		String sql = "insert into bindingType(Name,IsActive) values(?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, regis.getName());
		p.setString(2, regis.getIsActive());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	// ***********End******************

	// **************Associate***********
	public List getAssociateList() throws SQLException {
		String query = "select * from associate";

		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<AssociateList> ls = new ArrayList();

		while (resultSet.next()) {
			AssociateList stg = new AssociateList();

			stg.setId(resultSet.getInt("id"));
			stg.setEntityRef_no(resultSet.getInt("EntityRef_No"));
			stg.setAmount(resultSet.getString("Amount"));
			stg.setFilename(resultSet.getString("FileName"));
			stg.setIsvalid(resultSet.getString("Isvalid"));
			stg.setIsActive(resultSet.getString("IsActive"));
			stg.setEntityType(resultSet.getString("EntityType"));

			ls.add(stg);

		}

		return ls;

	}

	public boolean addAssociate(AssociateList regis) throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		String sql = "insert into associate(EntityType,EntityRef_No,OrderRef_No,Amount,TaxAmount,FileName,Isvalid,IsActive) values(?,?,?,?,?,?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, regis.getEntityType());
		p.setInt(2, regis.getEntityRef_no());
		p.setInt(3, regis.getOrderRef_no());
		p.setString(4, regis.getAmount());
		p.setString(5, regis.getTaxAmount());
		p.setString(6, regis.getFilename());
		p.setString(7, regis.getIsvalid());
		p.setString(8, regis.getIsActive());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public List getOrderRef() throws SQLException {
		String query = "select refId from orderDetail";
		String query2 = "select Name from entitylist";

		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		PreparedStatement pstmt2 = dataSource.getConnection().prepareStatement(query2);
		ResultSet resultSet2 = pstmt2.executeQuery();

		List<String> lst = new ArrayList();
		List<String> lst2 = new ArrayList();
		List<List> l3 = new ArrayList();
		;

		while (resultSet.next()) {

			lst.add(Integer.toString(resultSet.getInt("refId")));

		}

		while (resultSet2.next()) {

			lst2.add(resultSet2.getString("Name"));

		}

		l3.add(lst);
		l3.add(lst2);

		/*
		 * if(resultSet!=null){ resultSet.close(); }
		 */

		return l3;

	}

	public List getOrderAmount(int id) throws SQLException {
		String query = "select Amount from orderDetail where refId=? ";

		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		List lst = new ArrayList();

		while (resultSet.next()) {

			lst.add(resultSet.getInt("Amount"));

		}

		return lst;

	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// *************edit,update,delete User*************


	public boolean updateUser(RegistrationBean regis) throws SQLException {

		int row = 0;
		String sql = "update users set username=?,password=?,email=?,Expiry_date=?,Actor=?,role=? where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, regis.getUsename());
		p.setString(2, regis.getPassword());
		p.setString(3, regis.getEmail());
		p.setString(4, regis.getExpiryDate());
		p.setString(5, regis.getActor());
		p.setString(6, regis.getRole());
		
		p.setInt(7, regis.getId());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean deleteUser(int id) throws SQLException {

		int row = 0;
		String sql = "delete from users where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, id);

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public RegistrationBean editUser(int id) throws SQLException {
		String query = "select * from users where id=?";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		RegistrationBean lg = new RegistrationBean();
		while (resultSet.next()) {

			lg.setId(resultSet.getInt("id"));
			lg.setUsename(resultSet.getString("username"));
			lg.setPassword(resultSet.getString("password"));
			lg.setEmail(resultSet.getString("email"));
			lg.setExpiryDate(resultSet.getString("Expiry_date"));

			lg.setActor(resultSet.getString("actor"));
			lg.setRole(resultSet.getString("role"));
			
			
		}
		System.out.println(lg);

		return lg;

	}
	// Approver

	public List getApprover() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from approver";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Approver> ls = new ArrayList<Approver>();
		while (resultSet.next()) {

			Approver lg = new Approver();
			lg.setId(resultSet.getInt("id"));
			lg.setUserName(resultSet.getString("user_name"));
			lg.setApproverName(resultSet.getString("approver_name"));
			lg.setBackupName(resultSet.getString("backup_name"));

			ls.add(lg);
		}
		System.out.println(ls);

		return ls;
	}

	public boolean addApprover(Approver approver) throws SQLException {

		int row = 0;
		String sql = "insert into approver(user_name,approver_name,backup_name) values(?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, approver.getUserName());
		p.setString(2, approver.getApproverName());
		p.setString(3, approver.getBackupName());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean updateApprover(Approver approver) throws SQLException {

		int row = 0;
		String sql = "update approver set user_name=?,approver_name=?,backup_name=? where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, approver.getUserName());
		p.setString(2, approver.getApproverName());
		p.setString(3, approver.getBackupName());
		p.setInt(4, approver.getId());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean deleteApprover(int id) throws SQLException {

		int row = 0;
		String sql = "delete from approver where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, id);

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public Approver editApprover(int id) throws SQLException {
		String query = "select * from smr where id=?";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		Approver lg = new Approver();
		while (resultSet.next()) {

			lg.setId(resultSet.getInt(1));
			lg.setUserName(resultSet.getString(2));
			lg.setApproverName(resultSet.getString(3));
			lg.setBackupName(resultSet.getString(4));

		}
		System.out.println(lg);

		return lg;

	}

	// smr
	public List getSmr() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select * from smr";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<UserBackup> ls = new ArrayList<UserBackup>();
		while (resultSet.next()) {

			UserBackup lg = new UserBackup();
			lg.setId(resultSet.getInt("id"));
			lg.setUserName(resultSet.getString(2));
			lg.setFrom(resultSet.getString(3));
			lg.setTo(resultSet.getString(4));
			ls.add(lg);
		}
		System.out.println(ls);

		return ls;
	}

	public boolean addSmr(UserBackup smr) throws SQLException {

		int row = 0;
		String sql = "insert into smr(customer,froms,tos) values(?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, smr.getUserName());
		p.setString(2, smr.getFrom());
		p.setString(3, smr.getTo());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean updateSmr(UserBackup uback) throws SQLException {

		int row = 0;
		String sql = "update smr set customer=?,froms=?,tos=? where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, uback.getUserName());
		p.setString(2, uback.getFrom());
		p.setString(3, uback.getTo());
		p.setInt(4, uback.getId());

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean deleteSmr(int id) throws SQLException {

		int row = 0;
		String sql = "delete from smr where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, id);

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public UserBackup editSmr(int id) throws SQLException {
		String query = "select * from smr where id=?";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		UserBackup lg = new UserBackup();
		while (resultSet.next()) {

			lg.setId(resultSet.getInt(1));
			lg.setUserName(resultSet.getString(2));
			lg.setFrom(resultSet.getString(3));
			lg.setTo(resultSet.getString(4));

		}
		System.out.println(lg);

		return lg;

	}

	public List getPayment() throws SQLException {
		String query = "select * from payment";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Payment> ls = new ArrayList<Payment>();
		while (resultSet.next()) {

			Payment lg = new Payment();
			lg.setId(resultSet.getInt(1));
			lg.setRefId(resultSet.getInt(2));
			lg.setAmount(resultSet.getString(3));
			lg.setField1(resultSet.getString(4));
			lg.setField2(resultSet.getString(5));
			lg.setField3(resultSet.getString(6));
			lg.setIsActive(resultSet.getString(7));
			lg.setIsValidate(resultSet.getString(8));
			ls.add(lg);
		}
		System.out.println(ls);

		return ls;

	}

	public List getDelievery() throws SQLException {
		String query = "select * from delivery";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Delievery> ls = new ArrayList<Delievery>();
		while (resultSet.next()) {

			Delievery lg = new Delievery();
			lg.setId(resultSet.getInt(1));
			lg.setRefId(resultSet.getString(2));
			lg.setAmount(resultSet.getString(3));
			lg.setField1(resultSet.getString(4));
			lg.setField2(resultSet.getString(5));
			lg.setField3(resultSet.getString(6));
			lg.setIsActive(resultSet.getString(7));
			lg.setIsValidate(resultSet.getString(8));
			ls.add(lg);
		}
		System.out.println(ls);

		return ls;

	}
	// ***************End*********

	public boolean addPayment(Payment pay) throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		String sql = "insert into payment(refId,amount,field1,field2,field3,isActive,isValidate) values(?,?,?,?,?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, pay.getRefId());
		p.setString(2, pay.getAmount());
		p.setString(3, pay.getField1());
		p.setString(4, pay.getField2());
		p.setString(5, pay.getField3());
		p.setString(6, pay.getIsActive());
		p.setString(7, pay.getIsValidate());
		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean addDelievery(Delievery pay) throws SQLException {
		int row = 0;
		String sql = "insert into delivery(refId,amount,field1,field2,field3,isActive,isValidate) values(?,?,?,?,?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, pay.getRefId());
		p.setString(2, pay.getAmount());
		p.setString(3, pay.getField1());
		p.setString(4, pay.getField2());
		p.setString(5, pay.getField3());
		p.setString(6, pay.getIsActive());
		p.setString(7, pay.getIsValidate());
		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean updatePayment(Payment pay) throws SQLException {

		int row = 0;
		String sql = "update payment set refId=?,amount=?,field1=?,field2=?,field3=?,isActive=?,isValidate=? where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, pay.getRefId());
		p.setString(2, pay.getAmount());
		p.setString(3, pay.getField1());
		p.setString(4, pay.getField2());
		p.setString(5, pay.getField3());
		p.setString(6, pay.getIsActive());
		p.setString(7, pay.getIsValidate());
		p.setInt(8, pay.getId());
		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean deleteDelivery(int id) throws SQLException {

		int row = 0;
		String sql = "delete from delivery where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, id);

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public Delievery editDelivery(int id) throws SQLException {
		String query = "select * from delivery where id=?";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		Delievery lg = new Delievery();
		while (resultSet.next()) {

			lg.setId(resultSet.getInt(1));
			lg.setRefId(resultSet.getString(2));
			lg.setAmount(resultSet.getString(3));
			lg.setField1(resultSet.getString(4));
			lg.setField2(resultSet.getString(5));
			lg.setField3(resultSet.getString(6));
			lg.setIsActive(resultSet.getString(7));
			lg.setIsValidate(resultSet.getString(8));
		}
		System.out.println(lg);

		return lg;

	}

	public boolean updateDelivery(Delievery pay) throws SQLException {

		int row = 0;
		String sql = "update delivery set refId=?,amount=?,field1=?,field2=?,field3=?,isActive=?,isValidate=? where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, pay.getRefId());
		p.setString(2, pay.getAmount());
		p.setString(3, pay.getField1());
		p.setString(4, pay.getField2());
		p.setString(5, pay.getField3());
		p.setString(6, pay.getIsActive());
		p.setString(7, pay.getIsValidate());
		p.setInt(8, pay.getId());
		row = p.executeUpdate();
		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean deletePayment(int id) throws SQLException {

		int row = 0;
		String sql = "delete from payment where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, id);

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public Payment editPayment(int id) throws SQLException {
		String query = "select * from payment where id=?";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		Payment lg = new Payment();
		while (resultSet.next()) {

			lg.setId(resultSet.getInt(1));
			lg.setRefId(resultSet.getInt(2));
			lg.setAmount(resultSet.getString(3));
			lg.setField1(resultSet.getString(4));
			lg.setField2(resultSet.getString(5));
			lg.setField3(resultSet.getString(6));
			lg.setIsActive(resultSet.getString(7));
			lg.setIsValidate(resultSet.getString(8));
		}
		System.out.println(lg);

		return lg;

	}

	public List getOrderTrans() throws SQLException {
		String query = "select * from order_transaction";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<OrderTrans> ls = new ArrayList<OrderTrans>();
		while (resultSet.next()) {

			OrderTrans lg = new OrderTrans();
			lg.setId(resultSet.getInt(1));
			lg.setRefId(resultSet.getInt(2));
			lg.setAmount(resultSet.getString(3));
			lg.setField1(resultSet.getString(4));
			lg.setField2(resultSet.getString(5));
			lg.setField3(resultSet.getString(6));
			lg.setIsActive(resultSet.getString(7));
			lg.setIsValidate(resultSet.getString(8));
			ls.add(lg);
		}
		System.out.println(ls);

		return ls;

	}

	public List getInvoice() throws SQLException {
		String query = "select * from invoice";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		List<Invoice> ls = new ArrayList<Invoice>();
		while (resultSet.next()) {

			Invoice lg = new Invoice();
			lg.setId(resultSet.getInt(1));
			lg.setRefId(resultSet.getInt(2));
			lg.setAmount(resultSet.getString(3));
			lg.setField1(resultSet.getString(4));
			lg.setField2(resultSet.getString(5));
			lg.setField3(resultSet.getString(6));
			lg.setIsActive(resultSet.getString(7));
			lg.setIsValidate(resultSet.getString(8));
			ls.add(lg);
		}
		System.out.println(ls);

		return ls;

	}

	public boolean addOrderTrans(OrderTrans pay) throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		String sql = "insert into order_transaction(refId,amount,field1,field2,field3,isActive,isValidate) values(?,?,?,?,?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, pay.getRefId());
		p.setString(2, pay.getAmount());
		p.setString(3, pay.getField1());
		p.setString(4, pay.getField2());
		p.setString(5, pay.getField3());
		p.setString(6, pay.getIsActive());
		p.setString(7, pay.getIsValidate());
		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean addInvoice(Invoice pay) throws SQLException {
		int row = 0;
		String sql = "insert into invoice(refId,amount,field1,field2,field3,isActive,isValidate) values(?,?,?,?,?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, pay.getRefId());
		p.setString(2, pay.getAmount());
		p.setString(3, pay.getField1());
		p.setString(4, pay.getField2());
		p.setString(5, pay.getField3());
		p.setString(6, pay.getIsActive());
		p.setString(7, pay.getIsValidate());
		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean updateOrderTrans(OrderTrans pay) throws SQLException {

		int row = 0;
		String sql = "update order_transaction set refId=?,amount=?,field1=?,field2=?,field3=?,isActive=?,isValidate=? where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, pay.getRefId());
		p.setString(2, pay.getAmount());
		p.setString(3, pay.getField1());
		p.setString(4, pay.getField2());
		p.setString(5, pay.getField3());
		p.setString(6, pay.getIsActive());
		p.setString(7, pay.getIsValidate());
		p.setInt(8, pay.getId());
		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean deleteOrderTrans(int id) throws SQLException {

		int row = 0;
		String sql = "delete from order_transaction where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, id);

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public OrderTrans editOrderTrans(int id) throws SQLException {
		String query = "select * from order_transaction where id=?";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		OrderTrans lg = new OrderTrans();
		while (resultSet.next()) {

			lg.setId(resultSet.getInt(1));
			lg.setRefId(resultSet.getInt(2));
			lg.setAmount(resultSet.getString(3));
			lg.setField1(resultSet.getString(4));
			lg.setField2(resultSet.getString(5));
			lg.setField3(resultSet.getString(6));
			lg.setIsActive(resultSet.getString(7));
			lg.setIsValidate(resultSet.getString(8));
		}
		System.out.println(lg);

		return lg;

	}

	public boolean updateInvoice(Invoice pay) throws SQLException {

		int row = 0;
		String sql = "update invoice set refId=?,amount=?,field1=?,field2=?,field3=?,isActive=?,isValidate=? where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, pay.getRefId());
		p.setString(2, pay.getAmount());
		p.setString(3, pay.getField1());
		p.setString(4, pay.getField2());
		p.setString(5, pay.getField3());
		p.setString(6, pay.getIsActive());
		p.setString(7, pay.getIsValidate());
		p.setInt(8, pay.getId());
		row = p.executeUpdate();
		if (row > 0)
			return true;
		else
			return false;

	}

	public boolean deleteInvoice(int id) throws SQLException {

		int row = 0;
		String sql = "delete from invoice where id=?";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setInt(1, id);

		row = p.executeUpdate();

		if (row > 0)
			return true;
		else
			return false;

	}

	public Invoice editInvoice(int id) throws SQLException {
		String query = "select * from invoice where id=?";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet resultSet = pstmt.executeQuery();

		Invoice lg = new Invoice();
		while (resultSet.next()) {

			lg.setId(resultSet.getInt(1));
			lg.setRefId(resultSet.getInt(2));
			lg.setAmount(resultSet.getString(3));
			lg.setField1(resultSet.getString(4));
			lg.setField2(resultSet.getString(5));
			lg.setField3(resultSet.getString(6));
			lg.setIsActive(resultSet.getString(7));
			lg.setIsValidate(resultSet.getString(8));
		}
		System.out.println(lg);

		return lg;

	}

	
	//Chat
	public String getMessage(String msgID) throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM chat_summary where is_displayed_to_user='0' && direction='response' && user_id="+msgID;
	      System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		  String tempId="";
		ResultSet rs = pstmt.executeQuery();
		
		 JSONArray jsonFinal=new JSONArray();
			while (rs.next()) {
				 JSONArray json=new JSONArray();
				 tempId+=rs.getInt("id")+",";
				json.put(rs.getString("message"));
				json.put(rs.getString("created_time").substring(11));
			
				jsonFinal.put(json);
			}
			if(tempId.length()!=0){
			tempId=tempId.substring(0, tempId.length()-1);
			
			System.out.println(tempId);
			String sql1 = "update chat_summary set is_displayed_to_user='1' where id in("+tempId+")";
		  
			PreparedStatement pstmt2 = dataSource.getConnection().prepareStatement(sql1);
			pstmt2.executeUpdate();
		      
		
			}
			
		
			
		
			
		return jsonFinal.toString();
	}

	public String getCustomerMessage(String msgID) throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM chat_summary where is_displayed_to_user='0' && direction='request' && user_id=(select user_id from chat_window_status where id="+msgID+")";
	      System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		  String tempId="";
		ResultSet rs = pstmt.executeQuery();
		  JSONArray jsonFinal=new JSONArray();
			while (rs.next()) {
				 JSONArray json=new JSONArray();
				 
				 tempId+=rs.getInt("id")+",";
				json.put(rs.getString("message"));
				json.put(rs.getString("created_time").substring(11));
			
				
				json.put(rs.getInt("id"));
				jsonFinal.put(json);
			}
			if(tempId.length()!=0){
			tempId=tempId.substring(0, tempId.length()-1);
			
			System.out.println(tempId);
			String sql1 = "update chat_summary set is_displayed_to_user='1' where id in("+tempId+")";
		  
			PreparedStatement pstmt2 = dataSource.getConnection().prepareStatement(sql1);
			pstmt2.executeUpdate();
		      
		
			}
			
		
			
		
			
		return jsonFinal.toString();
	}

	public String setUserMessage(String msg, String userID) throws SQLException {
		// TODO Auto-generated method stub
		
		String query = "insert into chat_summary(message,direction,user_id) values('"+msg+"','request','"+Integer.parseInt(userID)+"')";
	      System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
	
	 pstmt.executeQuery();
		
		return null;
	}

	public String setCustomerMessage(String msg, String userID) throws SQLException {
		// TODO Auto-generated method stub
		
		String query =  "insert into chat_summary(message,direction,user_id) values('"+msg+"','response',(select user_id from chat_window_status where id="+userID+"))";
	       
	      System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		
	 pstmt.executeQuery();
		
		
		return null;
	}

	public String getChatWindow() throws SQLException {
		// TODO Auto-generated method stub
		String query =  "SELECT * FROM chat_window_status c,users u where  u.id=c.user_id && c.window_displayed=0 && c.isActive=1";
	      System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		  String tempId="";
		ResultSet rs = pstmt.executeQuery();
		JSONArray jsonFinal=new JSONArray();
		while (rs.next()) {
			JSONArray inner=new JSONArray();
			 tempId+=rs.getInt("id")+",";
		
			 inner.put(rs.getInt("id"));
			 inner.put(rs.getString("username"));
			 jsonFinal.put(inner);
			 
		}
		
		if(tempId.length()!=0){
			tempId=tempId.substring(0, tempId.length()-1);
			
			System.out.println(tempId);
			String sql1 = "update chat_window_status set window_displayed='1' where id in("+tempId+")";
		  
			PreparedStatement pstmt1 = dataSource.getConnection().prepareStatement(sql1);
			pstmt1.executeUpdate();
		      
		
			}

return jsonFinal.toString();
	}

	public String setChatStatus(int id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "update chat_window_status set isActive=1,window_displayed=0,window_opened_time=current_timestamp where user_id="+id;
	       
	      System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		  String tempId="";
		pstmt.executeUpdate();
		
		return null;
	}

	public String deleteChatStatus(int id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "update chat_window_status set windows_closed_time=current_timestamp,isActive=0 where user_id="+id;
	       
		    System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		  String tempId="";
		pstmt.executeUpdate();
		
		
		return null;
	}

	public String getDeActiveted() throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM chat_window_status where isActive=0";
	      System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		  String tempId="";
		ResultSet rs = pstmt.executeQuery();
		
		
		 JSONArray jsonFinal=new JSONArray();
			while (rs.next()) {
		
				
			
				jsonFinal.put(rs.getInt("id"));
				
		
				 
			}

			
		
			
		
			
		return jsonFinal.toString();
	}

	public int getDeActivetedId(int id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM chat_window_status where isActive=0";
	      System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		  String tempId="";
		ResultSet rs = pstmt.executeQuery();
		  int isAct=1;
			while (rs.next()) {
		
				
			isAct=rs.getInt("isActive");
			
		
				 
			}

			
		
			
		
			
		return isAct;
	}

	
	
	public String getAlerts() throws SQLException {
		// TODO Auto-generated method stub
		String query = "select (select count(*) from payment),(select count(*) from order_transaction),(select count(*) from delivery),(select count(*) from invoice),"+
		"(select count(*) from investment),(select count(*) from smr) from dual";
	      System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
	
		ResultSet rs = pstmt.executeQuery();
		
		
		 JSONArray jsonFinal=new JSONArray();
			while (rs.next()) {
		
				jsonFinal.put(rs.getInt(1));
				jsonFinal.put(rs.getInt(2));
				jsonFinal.put(rs.getInt(3));
				jsonFinal.put(rs.getInt(4));
		
				jsonFinal.put(rs.getInt(5));
				jsonFinal.put(rs.getInt(6));
			}

			
		
			
		
			
		return jsonFinal.toString();
	}
	public String saveFileToDatabase(String fileName) throws IOException, SQLException {
		String excelFilePath = "D:\\" + fileName;

		fileName = fileName.replace(".", "");
		Statement pstmt = dataSource.getConnection().createStatement();

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		// XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFFormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator(workbook);
		int numOfSheet = workbook.getNumberOfSheets();
		if (numOfSheet == 0)
			return "no sheets";

		for (int countSheets = 0; countSheets < numOfSheet; countSheets++) {

			String sheetName = workbook.getSheetAt(countSheets).getSheetName();

			/*DatabaseMetaData metadata = dataSource.getConnection().getMetaData();
			ResultSet resultSet;
			resultSet = metadata.getTables(null, null, sheetName, null);
			if (resultSet.next())
				return "sheet already exist in database";*/

			int totalRows = workbook.getSheetAt(countSheets).getPhysicalNumberOfRows();
			if (totalRows == 0)
				return "please remove empty sheet from file";

			String createTableQuery = "create table if not exists " + sheetName + "(id int primary key AUTO_INCREMENT";
			int numOfColumn = workbook.getSheetAt(countSheets).getRow(0).getPhysicalNumberOfCells();

			String insertDataQuery = "insert into " + sheetName + "(";
System.out.println("numOfColumn  "+numOfColumn);
			for (int countCell = 0; countCell < numOfColumn; countCell++) {

				XSSFCell cellValue = workbook.getSheetAt(countSheets).getRow(0).getCell(countCell);
				String strCell2 = "";
				if (cellValue.getCellType() == cellValue.CELL_TYPE_FORMULA) {
					strCell2 = "" + objFormulaEvaluator.evaluate(cellValue).getNumberValue();
				} else {
					strCell2 = cellValue.toString();
				}

				createTableQuery += "," + strCell2.replaceAll("[^a-zA-Z0-9]", "").trim() + " varchar(500) ";

				if (countCell == 0)
					insertDataQuery += strCell2.replaceAll("[^a-zA-Z0-9]", "").trim();
				else
					insertDataQuery += "," + strCell2.replaceAll("[^a-zA-Z0-9]", "").trim();

			}
			createTableQuery += ")";
			insertDataQuery += ") values(";
			pstmt = dataSource.getConnection().createStatement();

			pstmt.executeUpdate(createTableQuery);

			int numOfRows = workbook.getSheetAt(countSheets).getPhysicalNumberOfRows();

			for (int countRows = 1; countRows < numOfRows; countRows++) {

				String insertQuery = insertDataQuery;
				int numOfColumninCurrentRow = workbook.getSheetAt(countSheets).getRow(countRows).getPhysicalNumberOfCells();
System.out.println("numOfColumninCurrentRow"+numOfColumninCurrentRow+"      countRows "+countRows);
				for (int countCell = 0; countCell < numOfColumn; countCell++) {

					XSSFCell cellValue = workbook.getSheetAt(countSheets).getRow(countRows).getCell(countCell);
					String strCell2 = "";
					
					if(cellValue!=null ){
						if( cellValue.getCellType()==cellValue.CELL_TYPE_BLANK){
							cellValue.setCellType(cellValue.CELL_TYPE_STRING);
						}
						
						if (cellValue.getCellType() == cellValue.CELL_TYPE_FORMULA) {
							strCell2 = "" + objFormulaEvaluator.evaluate(cellValue).getNumberValue();
						} else {
							strCell2 = cellValue.toString();
						}
	
						if (countCell == 0)
							{
							insertQuery += "'" + strCell2 + "'";
							}
						else{
							
							insertQuery += ",'" + strCell2 + "'";
						}
					}
					else
						insertQuery += ",'" + strCell2 + "'";
				}

				insertQuery += ")";
				System.out.println(countRows);
				System.out.println(insertQuery);

				pstmt = dataSource.getConnection().createStatement();

				pstmt.executeUpdate(insertQuery);

			}

		}

		return "success";
	}
	
	// *********FileName storage to database******
		

		public boolean updatefileStatus(int id) throws SQLException {
			// TODO Auto-generated method stub
			int row=0;
			
			String sql = "update  fileAttachement set status=1 where id="+id;
	        PreparedStatement p=dataSource.getConnection().prepareStatement(sql);
	      
	            row=p.executeUpdate();
			
			
			
		
			if (row>0)
					return true;
			else
					return false;

		}
		
		public boolean addFileName(String file,FileList f) throws SQLException {
			// TODO Auto-generated method stub
			int row=0;
			
			String sql = "insert into fileAttachement(FileName,Status,FileType) values(?,?,?)";
	        PreparedStatement p=dataSource.getConnection().prepareStatement(sql);
	      p.setString(1,file);
	      p.setInt(2, 0);
	      p.setString(3,f.getDocumentType());
	           
	           
	           
	        
	            row=p.executeUpdate();
			
			
			
		
			if (row>0)
					return true;
			else
					return false;

		}
		// ********* end FileName storage to database******


	
	
	
	public List getReportGraphs() throws SQLException{
		
		
		
		return null;
	}

	
	public List getNumberOfCases() throws SQLException{
		// TODO Auto-generated method stub
		String tableName[]={"Business", "Location","DisplayBy","Roles","Users","CaseType","DocType"};
		String tableColumn[]={"CompanyDisplayName", "LocationDisplayName" ,"Period", "ARRoles","Username","CaseName","DocName"};
	
			List list=new ArrayList();	
		for(int countTable=0;countTable<tableName.length;countTable++){
	     
		String query = "select * from "+tableName[countTable];
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
	
		ResultSet resultSet = pstmt.executeQuery();
	

		FunctionApplication funName=new FunctionApplication();	
		funName.setName(tableName[countTable]);
		while (resultSet.next()) {

			if(tableName[countTable].equals("DisplayBy"))
				funName.value.add(resultSet.getString(tableColumn[countTable])+" - <p style='color:blue'>"+resultSet.getString("description")+"</p>");
			else
			funName.value.add(resultSet.getString(tableColumn[countTable]));
			
		
			
		
		}
		list.add(funName);
		}
		System.out.println(list);

		return list;
	
	}
	
	
	
	
	
	
	
	
	
	public List getCasesCreated(FunctionAppBean functionAppBean) throws SQLException, JSONException {
		String query = "select GblCompanyName, gbllocation, count(*) as count from ap_custombrminvoice  group by GblCompanyname,GblLocation";
	

		System.out.println(query);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		ArrayList list=new ArrayList();
		while (resultSet.next()) {
			functionTable innerJson=new functionTable();
		
			innerJson.setCompany(resultSet.getString("GblCompanyName"));
			innerJson.setLocation(resultSet.getString("gbllocation"));
			innerJson.setCount(resultSet.getString("count"));
			innerJson.setPeriod1("0");
			innerJson.setPeriod2("0");
			innerJson.setPeriod3("0");
			list.add(innerJson);
			
		}
		System.out.println(list);

		return list;

	}


	public JSONArray getCaseCreatedCompany(String columnValue) throws SQLException, JSONException {
		String query = "select * from ap_custombrminvoice where GblCompanyName=? and gbllocation=?";
	
		System.out.println(query);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, columnValue.split("@")[0]);
		pstmt.setString(2, columnValue.split("@")[1]);
		ResultSet resultSet = pstmt.executeQuery();

		JSONArray list=new JSONArray();
		

		
		
		while (resultSet.next()) {
			JSONArray innerJson=new JSONArray();
		
			innerJson.put(resultSet.getString("FCaseFolderID"));
			innerJson.put(resultSet.getString("UseName"));
			innerJson.put(resultSet.getString("DateCreated"));
			innerJson.put(resultSet.getString("gbllocation"));
			innerJson.put(resultSet.getString("GblBarCodeDC"));
			innerJson.put(resultSet.getString("GblDocumentSource"));
			innerJson.put(resultSet.getString("GblAmount"));
			innerJson.put(resultSet.getString("StepName"));
			innerJson.put(resultSet.getString("StartTime"));
			innerJson.put(resultSet.getString("EndTime"));
			list.put(innerJson);
			
		}
		System.out.println(list);

		return list;

	}
	
	
	
	

	 public List getCasesCreatedCompleted(FunctionAppBean functionAppBean)  throws SQLException, JSONException {
			String query = "select StepName, count(*) as count from ap_custombrminvoice  group by StepName";
			

			System.out.println(query);
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			ResultSet resultSet = pstmt.executeQuery();

			ArrayList list=new ArrayList();
			while (resultSet.next()) {
				functionTable innerJson=new functionTable();
			
				innerJson.setName(resultSet.getString("StepName"));
		
				innerJson.setCount(resultSet.getString("count"));
				innerJson.setPeriod1("0");
				innerJson.setPeriod2("0");
				innerJson.setPeriod3("0");
				innerJson.setPeriod4("0");
				
				list.add(innerJson);
				
			}
			System.out.println(list);

			return list;

		}
	    public JSONArray getCaseCreatedCompanyCompleted(String columnValue) throws SQLException, JSONException{
			String query = "select * from ap_custombrminvoice where StepName=?";
			
			System.out.println(query);
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, columnValue);

			ResultSet resultSet = pstmt.executeQuery();

			JSONArray list=new JSONArray();
			

			
			
			while (resultSet.next()) {
				JSONArray innerJson=new JSONArray();
			
				innerJson.put(resultSet.getString("FCaseFolderID"));
				innerJson.put(resultSet.getString("UseName"));
				innerJson.put(resultSet.getString("DateCreated"));
				innerJson.put(resultSet.getString("gbllocation"));
				innerJson.put(resultSet.getString("GblBarCodeDC"));
				innerJson.put(resultSet.getString("GblDocumentSource"));
				innerJson.put(resultSet.getString("GblAmount"));
				innerJson.put(resultSet.getString("StepName"));
				innerJson.put(resultSet.getString("StartTime"));
				innerJson.put(resultSet.getString("EndTime"));
				list.put(innerJson);
				
			}
			System.out.println(list);

			return list;

		}
		
	    public List getRoleWiseTimeTaken(FunctionAppBean functionAppBean)  throws SQLException, JSONException {
			String query = "select GblCurrentRole, count(*) as count from ap_custombrminvoice  group by GblCurrentRole";
			

			System.out.println(query);
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			ResultSet resultSet = pstmt.executeQuery();

			ArrayList list=new ArrayList();
			while (resultSet.next()) {
				functionTable innerJson=new functionTable();
			
				innerJson.setName(resultSet.getString("GblCurrentRole"));			
				innerJson.setCount(resultSet.getString("count"));
				innerJson.setPeriod1("0");
				innerJson.setPeriod2("0");
				innerJson.setPeriod3("0");
				innerJson.setPeriod4("0");
				list.add(innerJson);
				
			}
			System.out.println(list);

			return list;

		}
	    public JSONArray getRoleWiseTimeTaken(String columnValue) throws SQLException, JSONException{
			String query = "select * from ap_custombrminvoice where GblCurrentRole=?";
			
			System.out.println(query);
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, columnValue);

			ResultSet resultSet = pstmt.executeQuery();

			JSONArray list=new JSONArray();
			

			
			
			while (resultSet.next()) {
				JSONArray innerJson=new JSONArray();
			
				innerJson.put(resultSet.getString("FCaseFolderID"));
				innerJson.put(resultSet.getString("UseName"));
				innerJson.put(resultSet.getString("DateCreated"));
				innerJson.put(resultSet.getString("gbllocation"));
				innerJson.put(resultSet.getString("GblBarCodeDC"));
				innerJson.put(resultSet.getString("GblDocumentSource"));
				innerJson.put(resultSet.getString("GblAmount"));
				innerJson.put(resultSet.getString("StepName"));
				innerJson.put(resultSet.getString("StartTime"));
				innerJson.put(resultSet.getString("EndTime"));
				list.put(innerJson);
				
			}
			System.out.println(list);

			return list;

		}
		
	    public List getTotalCycleTime(FunctionAppBean functionAppBean)  throws SQLException, JSONException {
String query = "select StepName, count(*) as count from ap_custombrminvoice  group by StepName";
			

			System.out.println(query);
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			ResultSet resultSet = pstmt.executeQuery();

			ArrayList list=new ArrayList();
			while (resultSet.next()) {
				functionTable innerJson=new functionTable();
			
				innerJson.setName(resultSet.getString("StepName"));
		
				innerJson.setCount(resultSet.getString("count"));
				innerJson.setPeriod1("0");
				innerJson.setPeriod2("0");
				innerJson.setPeriod3("0");
				innerJson.setPeriod4("0");
				
				list.add(innerJson);
				
			}
			System.out.println(list);

			return list;

		}
	    public JSONArray getTotalCycleTime(String columnValue) throws SQLException, JSONException{
String query = "select * from ap_custombrminvoice where StepName=?";
			
			System.out.println(query);
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, columnValue);

			ResultSet resultSet = pstmt.executeQuery();

			JSONArray list=new JSONArray();
			

			
			
			while (resultSet.next()) {
				JSONArray innerJson=new JSONArray();
			
				innerJson.put(resultSet.getString("FCaseFolderID"));
				innerJson.put(resultSet.getString("UseName"));
				innerJson.put(resultSet.getString("DateCreated"));
				innerJson.put(resultSet.getString("gbllocation"));
				innerJson.put(resultSet.getString("GblBarCodeDC"));
				innerJson.put(resultSet.getString("GblDocumentSource"));
				innerJson.put(resultSet.getString("GblAmount"));
				innerJson.put(resultSet.getString("StepName"));
				innerJson.put(resultSet.getString("StartTime"));
				innerJson.put(resultSet.getString("EndTime"));
				list.put(innerJson);
				
			}
			System.out.println(list);

			return list;

		}
	
	    public List getNumberOfCasesHR() throws SQLException{
			// TODO Auto-generated method stub
			String tableName[]={"Business","PersonnelArea","PayrollArea", "Location","DisplayBy","Roles","Users","CaseType","DocType"};
			String tableColumn[]={"CompanyDisplayName","PersonnelAreaDisplayName","PayrollAreaDisplayName", "LocationDisplayName" ,"Period", "ARRoles","Username","CaseName","DocName"};
		
				List list=new ArrayList();	
			for(int countTable=0;countTable<tableName.length;countTable++){
		     
			String query = "select * from "+tableName[countTable];
			System.out.println(dataSource);
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		
			ResultSet resultSet = pstmt.executeQuery();
		

			FunctionApplication funName=new FunctionApplication();	
			funName.setName(tableName[countTable]);
			while (resultSet.next()) {

				if(tableName[countTable].equals("DisplayBy"))
					funName.value.add(resultSet.getString(tableColumn[countTable])+" - <p style='color:blue'>"+resultSet.getString("description")+"</p>");
				else
				funName.value.add(resultSet.getString(tableColumn[countTable]));
				
			
				
			
			}
			list.add(funName);
			}
			System.out.println(list);

			return list;
		
		}
		
		
	    public List getNumberOfCasesCreatedHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException {
	    	String query = "select GblCompanyName, gbllocation,gblpayrollarea, count(*) as count from ap_custombrminvoice  group by GblCompanyname,GblLocation,gblpayrollarea";
	    	

			System.out.println(query);
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			ResultSet resultSet = pstmt.executeQuery();

			ArrayList list=new ArrayList();
			while (resultSet.next()) {
				functionTable innerJson=new functionTable();
			
				innerJson.setCompany(resultSet.getString("GblCompanyName"));
				innerJson.setLocation(resultSet.getString("gbllocation"));
				innerJson.setPayrollarea(resultSet.getString("gblpayrollarea"));
				innerJson.setCount(resultSet.getString("count"));
				innerJson.setPeriod1("0");
				innerJson.setPeriod2("0");
				innerJson.setPeriod3("0");
				list.add(innerJson);
				
			}
			System.out.println(list);

			return list;


		}
	    public JSONArray getNumberOfCasesCreatedHR(String columnValue) throws SQLException, JSONException{
	    	String query = "select * from ap_custombrminvoice where GblCompanyName=? and gbllocation=?,gblpayrollarea=?";
	    	
			System.out.println(query);
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
			pstmt.setString(1, columnValue.split("@")[0]);
			pstmt.setString(2, columnValue.split("@")[1]);
			pstmt.setString(3, columnValue.split("@")[2]);
			ResultSet resultSet = pstmt.executeQuery();

			JSONArray list=new JSONArray();
			while (resultSet.next()) {
				JSONArray innerJson=new JSONArray();
			
				innerJson.put(resultSet.getString("FCaseFolderID"));
				innerJson.put(resultSet.getString("UseName"));
				innerJson.put(resultSet.getString("DateCreated"));
				innerJson.put(resultSet.getString("gbllocation"));
				innerJson.put(resultSet.getString("GblBarCodeDC"));
				innerJson.put(resultSet.getString("GblDocumentSource"));
				innerJson.put(resultSet.getString("GblAmount"));
				innerJson.put(resultSet.getString("StepName"));
				innerJson.put(resultSet.getString("StartTime"));
				innerJson.put(resultSet.getString("EndTime"));
				list.put(innerJson);
				
			}
			System.out.println(list);

          return list;
		}
	
		 public List getCasesCreatedCompletedHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException {
				String query = "select StepName, count(*) as count from ap_custombrminvoice  group by StepName";
				

				System.out.println(query);
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				ResultSet resultSet = pstmt.executeQuery();

				ArrayList list=new ArrayList();
				while (resultSet.next()) {
					functionTable innerJson=new functionTable();
				
					innerJson.setName(resultSet.getString("StepName"));
			
					innerJson.setCount(resultSet.getString("count"));
					innerJson.setPeriod1("0");
					innerJson.setPeriod2("0");
					innerJson.setPeriod3("0");
					innerJson.setPeriod4("0");
					
					list.add(innerJson);
					
				}
				System.out.println(list);

				return list;

			}
		    public JSONArray getCaseCreatedCompanyCompletedHR(String columnValue) throws SQLException, JSONException{
				String query = "select * from ap_custombrminvoice where StepName=?";
				
				System.out.println(query);
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				pstmt.setString(1, columnValue);

				ResultSet resultSet = pstmt.executeQuery();

				JSONArray list=new JSONArray();
				

				
				
				while (resultSet.next()) {
					JSONArray innerJson=new JSONArray();
				
					innerJson.put(resultSet.getString("FCaseFolderID"));
					innerJson.put(resultSet.getString("UseName"));
					innerJson.put(resultSet.getString("DateCreated"));
					innerJson.put(resultSet.getString("gbllocation"));
					innerJson.put(resultSet.getString("GblBarCodeDC"));
					innerJson.put(resultSet.getString("GblDocumentSource"));
					innerJson.put(resultSet.getString("GblAmount"));
					innerJson.put(resultSet.getString("StepName"));
					innerJson.put(resultSet.getString("StartTime"));
					innerJson.put(resultSet.getString("EndTime"));
					list.put(innerJson);
					
				}
				System.out.println(list);

				return list;

			}
			
		    public List getRoleWiseTimeTakenHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException {
				String query = "select GblCurrentRole, count(*) as count from ap_custombrminvoice  group by GblCurrentRole";
				

				System.out.println(query);
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				ResultSet resultSet = pstmt.executeQuery();

				ArrayList list=new ArrayList();
				while (resultSet.next()) {
					functionTable innerJson=new functionTable();
				
					innerJson.setName(resultSet.getString("GblCurrentRole"));			
					innerJson.setCount(resultSet.getString("count"));
					innerJson.setPeriod1("0");
					innerJson.setPeriod2("0");
					innerJson.setPeriod3("0");
					innerJson.setPeriod4("0");
					list.add(innerJson);
					
				}
				System.out.println(list);

				return list;

			}
		    public JSONArray getRoleWiseTimeTakenHR(String columnValue) throws SQLException, JSONException{
				String query = "select * from ap_custombrminvoice where GblCurrentRole=?";
				
				System.out.println(query);
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				pstmt.setString(1, columnValue);

				ResultSet resultSet = pstmt.executeQuery();

				JSONArray list=new JSONArray();
				

				
				
				while (resultSet.next()) {
					JSONArray innerJson=new JSONArray();
				
					innerJson.put(resultSet.getString("FCaseFolderID"));
					innerJson.put(resultSet.getString("UseName"));
					innerJson.put(resultSet.getString("DateCreated"));
					innerJson.put(resultSet.getString("gbllocation"));
					innerJson.put(resultSet.getString("GblBarCodeDC"));
					innerJson.put(resultSet.getString("GblDocumentSource"));
					innerJson.put(resultSet.getString("GblAmount"));
					innerJson.put(resultSet.getString("StepName"));
					innerJson.put(resultSet.getString("StartTime"));
					innerJson.put(resultSet.getString("EndTime"));
					list.put(innerJson);
					
				}
				System.out.println(list);

				return list;

			}
			
		    public List getTotalCycleTimeHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException {
	String query = "select StepName, count(*) as count from ap_custombrminvoice  group by StepName";
				

				System.out.println(query);
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				ResultSet resultSet = pstmt.executeQuery();

				ArrayList list=new ArrayList();
				while (resultSet.next()) {
					functionTable innerJson=new functionTable();
				
					innerJson.setName(resultSet.getString("StepName"));
			
					innerJson.setCount(resultSet.getString("count"));
					innerJson.setPeriod1("0");
					innerJson.setPeriod2("0");
					innerJson.setPeriod3("0");
					innerJson.setPeriod4("0");
					
					list.add(innerJson);
					
				}
				System.out.println(list);

				return list;

			}
		    public JSONArray getTotalCycleTimeAR(String columnValue) throws SQLException, JSONException{
	String query = "select * from ap_custombrminvoice where StepName=?";
				
				System.out.println(query);
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				pstmt.setString(1, columnValue);

				ResultSet resultSet = pstmt.executeQuery();

				JSONArray list=new JSONArray();
				

				
				
				while (resultSet.next()) {
					JSONArray innerJson=new JSONArray();
				
					innerJson.put(resultSet.getString("FCaseFolderID"));
					innerJson.put(resultSet.getString("UseName"));
					innerJson.put(resultSet.getString("DateCreated"));
					innerJson.put(resultSet.getString("gbllocation"));
					innerJson.put(resultSet.getString("GblBarCodeDC"));
					innerJson.put(resultSet.getString("GblDocumentSource"));
					innerJson.put(resultSet.getString("GblAmount"));
					innerJson.put(resultSet.getString("StepName"));
					innerJson.put(resultSet.getString("StartTime"));
					innerJson.put(resultSet.getString("EndTime"));
					list.put(innerJson);
					
				}
				System.out.println(list);

				return list;

			}
		
			
		    public List getNumberOfCasesCreatedAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException {
		    	String query = "select GblCompanyName, gbllocation, count(*) as count from ap_custombrminvoice  group by GblCompanyname,GblLocation";
		    	

				System.out.println(query);
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				ResultSet resultSet = pstmt.executeQuery();

				ArrayList list=new ArrayList();
				while (resultSet.next()) {
					functionTable innerJson=new functionTable();
				
					innerJson.setCompany(resultSet.getString("GblCompanyName"));
					innerJson.setLocation(resultSet.getString("gbllocation"));
				//	innerJson.setPayrollarea(resultSet.getString("gblpayrollarea"));
					innerJson.setCount(resultSet.getString("count"));
					innerJson.setPeriod1("0");
					innerJson.setPeriod2("0");
					innerJson.setPeriod3("0");
					list.add(innerJson);
					
				}
				System.out.println(list);

				return list;


			}
		    public JSONArray getNumberOfCasesCreatedAR(String columnValue) throws SQLException, JSONException{
		    	String query = "select * from ap_custombrminvoice where GblCompanyName=? and gbllocation=?";
		    	
				System.out.println(query);
				PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
				pstmt.setString(1, columnValue.split("@")[0]);
				pstmt.setString(2, columnValue.split("@")[1]);
			//	pstmt.setString(3, columnValue.split("@")[2]);
				ResultSet resultSet = pstmt.executeQuery();

				JSONArray list=new JSONArray();
				while (resultSet.next()) {
					JSONArray innerJson=new JSONArray();
				
					innerJson.put(resultSet.getString("FCaseFolderID"));
					innerJson.put(resultSet.getString("UseName"));
					innerJson.put(resultSet.getString("DateCreated"));
					innerJson.put(resultSet.getString("gbllocation"));
					innerJson.put(resultSet.getString("GblBarCodeDC"));
					innerJson.put(resultSet.getString("GblDocumentSource"));
					innerJson.put(resultSet.getString("GblAmount"));
					innerJson.put(resultSet.getString("StepName"));
					innerJson.put(resultSet.getString("StartTime"));
					innerJson.put(resultSet.getString("EndTime"));
					list.put(innerJson);
					
				}
				System.out.println(list);

	          return list;
			}
		
			 public List getCasesCreatedCompletedAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException {
					String query = "select StepName, count(*) as count from ap_custombrminvoice  group by StepName";
					

					System.out.println(query);
					PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
					ResultSet resultSet = pstmt.executeQuery();

					ArrayList list=new ArrayList();
					while (resultSet.next()) {
						functionTable innerJson=new functionTable();
					
						innerJson.setName(resultSet.getString("StepName"));
				
						innerJson.setCount(resultSet.getString("count"));
						innerJson.setPeriod1("0");
						innerJson.setPeriod2("0");
						innerJson.setPeriod3("0");
						innerJson.setPeriod4("0");
						
						list.add(innerJson);
						
					}
					System.out.println(list);

					return list;

				}
			    public JSONArray getCaseCreatedCompanyCompletedAR(String columnValue) throws SQLException, JSONException{
					String query = "select * from ap_custombrminvoice where StepName=?";
					
					System.out.println(query);
					PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
					pstmt.setString(1, columnValue);

					ResultSet resultSet = pstmt.executeQuery();

					JSONArray list=new JSONArray();
					

					
					
					while (resultSet.next()) {
						JSONArray innerJson=new JSONArray();
					
						innerJson.put(resultSet.getString("FCaseFolderID"));
						innerJson.put(resultSet.getString("UseName"));
						innerJson.put(resultSet.getString("DateCreated"));
						innerJson.put(resultSet.getString("gbllocation"));
						innerJson.put(resultSet.getString("GblBarCodeDC"));
						innerJson.put(resultSet.getString("GblDocumentSource"));
						innerJson.put(resultSet.getString("GblAmount"));
						innerJson.put(resultSet.getString("StepName"));
						innerJson.put(resultSet.getString("StartTime"));
						innerJson.put(resultSet.getString("EndTime"));
						list.put(innerJson);
						
					}
					System.out.println(list);

					return list;

				}
				
			    public List getRoleWiseTimeTakenAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException {
					String query = "select GblCurrentRole, count(*) as count from ap_custombrminvoice  group by GblCurrentRole";
					

					System.out.println(query);
					PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
					ResultSet resultSet = pstmt.executeQuery();

					ArrayList list=new ArrayList();
					while (resultSet.next()) {
						functionTable innerJson=new functionTable();
					
						innerJson.setName(resultSet.getString("GblCurrentRole"));			
						innerJson.setCount(resultSet.getString("count"));
						innerJson.setPeriod1("0");
						innerJson.setPeriod2("0");
						innerJson.setPeriod3("0");
						innerJson.setPeriod4("0");
						list.add(innerJson);
						
					}
					System.out.println(list);

					return list;

				}
			    public JSONArray getRoleWiseTimeTakenAR(String columnValue) throws SQLException, JSONException{
					String query = "select * from ap_custombrminvoice where GblCurrentRole=?";
					
					System.out.println(query);
					PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
					pstmt.setString(1, columnValue);

					ResultSet resultSet = pstmt.executeQuery();

					JSONArray list=new JSONArray();
					

					
					
					while (resultSet.next()) {
						JSONArray innerJson=new JSONArray();
					
						innerJson.put(resultSet.getString("FCaseFolderID"));
						innerJson.put(resultSet.getString("UseName"));
						innerJson.put(resultSet.getString("DateCreated"));
						innerJson.put(resultSet.getString("gbllocation"));
						innerJson.put(resultSet.getString("GblBarCodeDC"));
						innerJson.put(resultSet.getString("GblDocumentSource"));
						innerJson.put(resultSet.getString("GblAmount"));
						innerJson.put(resultSet.getString("StepName"));
						innerJson.put(resultSet.getString("StartTime"));
						innerJson.put(resultSet.getString("EndTime"));
						list.put(innerJson);
						
					}
					System.out.println(list);

					return list;

				}
				
			    public List getTotalCycleTimeAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException {
		String query = "select StepName, count(*) as count from ap_custombrminvoice  group by StepName";
					

					System.out.println(query);
					PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
					ResultSet resultSet = pstmt.executeQuery();

					ArrayList list=new ArrayList();
					while (resultSet.next()) {
						functionTable innerJson=new functionTable();
					
						innerJson.setName(resultSet.getString("StepName"));
				
						innerJson.setCount(resultSet.getString("count"));
						innerJson.setPeriod1("0");
						innerJson.setPeriod2("0");
						innerJson.setPeriod3("0");
						innerJson.setPeriod4("0");
						
						list.add(innerJson);
						
					}
					System.out.println(list);

					return list;

				}
			    public JSONArray getTotalCycleTimeHR(String columnValue) throws SQLException, JSONException{
		String query = "select * from ap_custombrminvoice where StepName=?";
					
					System.out.println(query);
					PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
					pstmt.setString(1, columnValue);

					ResultSet resultSet = pstmt.executeQuery();

					JSONArray list=new JSONArray();
					

					
					
					while (resultSet.next()) {
						JSONArray innerJson=new JSONArray();
					
						innerJson.put(resultSet.getString("FCaseFolderID"));
						innerJson.put(resultSet.getString("UseName"));
						innerJson.put(resultSet.getString("DateCreated"));
						innerJson.put(resultSet.getString("gbllocation"));
						innerJson.put(resultSet.getString("GblBarCodeDC"));
						innerJson.put(resultSet.getString("GblDocumentSource"));
						innerJson.put(resultSet.getString("GblAmount"));
						innerJson.put(resultSet.getString("StepName"));
						innerJson.put(resultSet.getString("StartTime"));
						innerJson.put(resultSet.getString("EndTime"));
						list.put(innerJson);
						
					}
					System.out.println(list);

					return list;

				}
}
