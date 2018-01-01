package excellDatabase;

import java.sql.Connection;
import java.sql.DriverManager;



import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;


public class ExcelDatabase {

	static PreparedStatement stmt=null;
	public static Connection conn=null;
	 static final String DB_URL = "jdbc:mysql://localhost/lapp";
	 static final String USER = "root";
	   static final String PASS = "king";
	   static String columnAttribute="";
	   public static void createtable(String tableName,JSONArray header) throws SQLException, JSONException{
		   try {
			Class.forName("com.mysql.jdbc.Driver");
				 conn = DriverManager.getConnection(DB_URL, USER, PASS);
				 System.out.println("cooming"+conn);
				 
				 String str="CREATE TABLE"+" "+tableName+"(";
				 
				
				 for(int i=0;i<header.length();i++)
				 {
					 if(i==0)
		str+=header.getString(i).replaceAll(".\\s+","_")+" varchar(200) primary key";
		else
str+=","+header.getString(i).replaceAll(".\\s+","_").replaceAll("%","")+" varchar(200)";
				 }
				 str+=")";
				 
				 stmt = conn.prepareStatement(str);
				 
				
				
				 stmt.execute();
	
		        
	
				
		         
		         
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   finally{ 
			    if(conn!=null)
			   conn.close();}
		   
	   }
	
	public static void addData(String tableName,JSONArray column_value) throws SQLException, JSONException{
		System.out.println("comming"+column_value.length());
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection(DB_URL, USER, PASS);
			 String sql="INSERT INTO "+tableName+" "+ "VALUES(";
			 
			 for(int i=0;i<column_value.length();i++){
				 if(i==0)
				 sql+="?";
				 else
					 sql+=","+"?";
			
			 }
			 sql+=")";
			
	         stmt = conn.prepareStatement(sql);
	        
	       
	         
	         for(int i=0;i<column_value.length();i++){
	        	 System.out.println(" not comming"+column_value.getString(i));
	        	
	        	 
	        		 stmt.setString(i+1,column_value.getString(i));
	        	 
	        	}
			stmt.execute();
	         } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{ 
			    if(conn!=null)
			   conn.close();}
	}
	
	

}
