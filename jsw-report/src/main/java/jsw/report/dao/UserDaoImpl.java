package jsw.report.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONArray;

import jsw.report.dao.UserDao;
import jsw.report.viewBean.LoginBean;
import jsw.report.viewBean.Menutable;
import jsw.report.viewBean.RegistrationBean;
import jsw.report.viewBean.Tests;

/**
 * @author CENTAUR
 */
public class UserDaoImpl implements UserDao {

	DataSource dataSource;

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String isValidUser(String username, String password) throws SQLException {
		String query = "Select count(1),role from users where binary username = ? and password = ?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		ResultSet resultSet = pstmt.executeQuery();
		if (resultSet.next()) {

			String myid = resultSet.getString(2);
			System.out.println(myid);
			return myid;
		} else
			return "0";
	}

	public String getScreenID(String role) throws SQLException {
		String query = "Select screenId from role where role_name=?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, role);
		ResultSet resultSet = pstmt.executeQuery();
		if (resultSet.next()) {

			String myid = resultSet.getString(1);
			System.out.println(myid);
			return myid;
		} else
			return "0";
	}
	
	
	public List getMenuNames(String role) throws SQLException{
		String query = "select * from menutable where menu_id in ("+role+")";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		
		ResultSet resultSet = pstmt.executeQuery();
		List MenuNames=new ArrayList();
		while(resultSet.next()){
			Menutable menu=new Menutable();
			menu.setMenuId(resultSet.getInt("menu_id"));
			menu.setMenuName(resultSet.getString("menu_name"));
			menu.setSubMenu(resultSet.getString("sub_menu"));
			menu.setIsleaf(resultSet.getString("isleaf"));
			menu.setAction(resultSet.getString("action"));
			menu.setHref(resultSet.getString("href"));
			MenuNames.add(menu);
			
			
			
			
		}
		
		return MenuNames;
	}
	
	
	public boolean insertUser(String usename, String password, String name, String email, String gender,
			String nationality, String passportNo, String issueDate, String expiryDate, String actor)
			throws SQLException {
		// TODO Auto-generated method stub
		int row = 0;
		String sql = "insert into users(username,password,name,email,Gender,Nationality,Passport_No,Issue_Date,Expiry_date,Actor) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement p = dataSource.getConnection().prepareStatement(sql);
		p.setString(1, usename);
		p.setString(2, password);
		p.setString(3, name);
		p.setString(4, email);
		p.setString(5, gender);
		p.setString(6, nationality);
		p.setString(7, passportNo);
		p.setString(8, issueDate);
		p.setString(9, expiryDate);
		p.setString(10, actor);
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

	public RegistrationBean getProfile(String username) throws SQLException {
		System.out.println(username);
		String query = "Select * from users where username = ?";
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		pstmt.setString(1, username);
		ResultSet resultSet = pstmt.executeQuery();
		RegistrationBean lg = new RegistrationBean();
		if (resultSet.next()) {
			System.out.println(resultSet.getString("username"));
			lg.setId(resultSet.getInt("id"));
			lg.setUsename(resultSet.getString("username"));
			lg.setName(resultSet.getString("name"));
			lg.setEmail(resultSet.getString("email"));
			lg.setGender(resultSet.getString("Gender"));
			lg.setNationality(resultSet.getString("Nationality"));
			lg.setPassportNo(resultSet.getString("Passport_No"));
			lg.setIssueDate(resultSet.getString("Issue_Date"));
			lg.setExpiryDate(resultSet.getString("Expiry_Date"));
			lg.setActor(resultSet.getString("Actor"));
			System.out.println("Regissss" + lg);

		}

		return lg;

	}
	
	
	public JSONArray getUserHistory() throws SQLException{
		// TODO Auto-generated method stub
		String query = "SELECT * FROM chat_summary where is_displayed_to_user='1'";
		System.out.println(dataSource);
		PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
		ResultSet resultSet = pstmt.executeQuery();

		JSONArray jsonFinal=new JSONArray();
		while (resultSet.next()) {
			 JSONArray json=new JSONArray();
			
			json.put(resultSet.getString("message"));
			json.put(resultSet.getString("created_time").substring(11));
		json.put(resultSet.getString("direction"));
		json.put(resultSet.getInt("id"));
		json.put(resultSet.getString("created_time").substring(0,10));
		
			jsonFinal.put(json);
		}
	
		
		return jsonFinal;
		
	
	}
	

}