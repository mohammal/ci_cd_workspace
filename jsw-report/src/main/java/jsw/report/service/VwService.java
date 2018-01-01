package jsw.report.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import jsw.report.viewBean.*;

public interface VwService {

	public boolean updateTest(Tests test) throws SQLException;

	public Tests editTest(int id) throws SQLException;

	public boolean deleteTest(int id) throws SQLException;

	public boolean addTest(Tests test) throws SQLException;

	public List getTest() throws SQLException;

	public List getRole() throws SQLException;

	public List<RegistrationBean> getAllUsers() throws SQLException;

	public JSONArray getMenuTree() throws Exception;

	public List<String> getUserName() throws SQLException;

	public boolean updateScreenID(RegistrationBean screenID) throws SQLException;
	public boolean updateDashboard(RegistrationBean screenID) throws SQLException;
	public boolean addRole(Role role) throws SQLException;

	public boolean addUser(RegistrationBean regis) throws SQLException;

	public List getDealerById(int id) throws SQLException;

	public boolean addDealer(Dealer test) throws SQLException;

	public boolean deleteDealerList(int id) throws SQLException;

	public boolean updateDealer(Dealer stage) throws SQLException;

	public List getDealer() throws SQLException;

	// ***************Product******
	public List getOrder() throws SQLException;

	public List getProduct() throws SQLException;

	public boolean addOrder(Orders order) throws SQLException;

	public boolean deleteOrderList(int id) throws SQLException;

	public boolean updateOrder(Orders stage) throws SQLException;

	public List getOrderById(int id) throws SQLException;

	// ***********End**********

	// ***************Product******
	public List getService() throws SQLException;

	public boolean addService(Service order) throws SQLException;

	public boolean deleteServiceList(int id) throws SQLException;

	public boolean updateService(Service stage) throws SQLException;

	public List getServiceById(int id) throws SQLException;

	// ************Schem************
	public List getDiscountOnMaterials() throws SQLException;

	public boolean addDiscountOnMaterials(Scheme discount) throws SQLException;

	// **********End*************

	// *********Contract**********
	public List getContract() throws SQLException;
	// **********End***********

	// *********Investment**********
	public List getInvestment() throws SQLException;
	// **********End***********

	// ************File Uploading importing*********
	public List getFileList() throws SQLException;

	public String getFileById(int id) throws SQLException;

	public boolean addFileName(String file,FileList f) throws SQLException;

	public boolean deleteFileList(int id) throws SQLException;

	// ***********End*************

	// **********Document Type*******

	public List getDocumentList() throws SQLException;

	public boolean addDocumentType(DocumentType regis) throws SQLException;
	// **********End***************

	// **********Investment Type*******

	public List getInvestmentList() throws SQLException;

	public boolean addInvestmentType(InvestmentType regis) throws SQLException;
	// **********End***************

	// **********Binding Type*******

	public List getBindingList() throws SQLException;

	public boolean addBindingType(Binding regis) throws SQLException;
	// **********End***************

	// ************Associate**************
	public List getAssociateList() throws SQLException;

	public boolean addAssociate(AssociateList regis) throws SQLException;

	public List getOrderRef() throws SQLException;

	public List getOrderAmount(int id) throws SQLException;

	// ************End*******************
	// *************Customer***********
	public List getCustomer() throws SQLException;

	public boolean addCustomer(Customer test) throws SQLException;

	// **************End*************
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public boolean updateUser(RegistrationBean regis) throws SQLException;

	public RegistrationBean editUser(int id) throws SQLException;

	public boolean deleteUser(int id) throws SQLException;

	// Approver
	public List getApprover() throws SQLException;

	public boolean addApprover(Approver approver) throws SQLException;

	public boolean updateApprover(Approver approver) throws SQLException;

	public Approver editApprover(int id) throws SQLException;

	public boolean deleteApprover(int id) throws SQLException;

	// SMR
	public List getSmr() throws SQLException;

	public boolean addSmr(UserBackup userbackup) throws SQLException;

	public boolean updateSmr(UserBackup userbackup) throws SQLException;

	public UserBackup editSmr(int id) throws SQLException;

	public boolean deleteSmr(int id) throws SQLException;

	public List getPayment() throws SQLException;

	public List getDelievery() throws SQLException;

	public boolean addPayment(Payment pay) throws SQLException;

	public boolean addDelievery(Delievery deliver) throws SQLException;

	public boolean updatePayment(Payment pay) throws SQLException;

	public Payment editPayment(int id) throws SQLException;

	public boolean deletePayment(int id) throws SQLException;

	public boolean updateDelivery(Delievery pay) throws SQLException;

	public Delievery editDelivery(int id) throws SQLException;

	public boolean deleteDelivery(int id) throws SQLException;

	public List getOrderTrans() throws SQLException;

	public List getInvoice() throws SQLException;

	public boolean addOrderTrans(OrderTrans pay) throws SQLException;

	public boolean addInvoice(Invoice deliver) throws SQLException;

	public boolean updateOrderTrans(OrderTrans pay) throws SQLException;

	public OrderTrans editOrderTrans(int id) throws SQLException;

	public boolean deleteOrderTrans(int id) throws SQLException;

	public boolean updateInvoice(Invoice pay) throws SQLException;

	public Invoice editInvoice(int id) throws SQLException;

	public boolean deleteInvoice(int id) throws SQLException;
	// ***********End**********
	public List getReportGraphs() throws SQLException;
	public boolean updatefileStatus(int id) throws SQLException;
	
	
	public List getNumberOfCases() throws SQLException;
	public List getCasesCreated(FunctionAppBean functionAppBean)  throws SQLException, JSONException;
	
	public JSONArray getCaseCreatedCompany(String columnValue) throws SQLException, JSONException;
	
	
	
	 public List getCasesCreatedCompleted(FunctionAppBean functionAppBean)  throws SQLException, JSONException;
	    public JSONArray getCaseCreatedCompanyCompleted(String columnValue) throws SQLException, JSONException;
		
	    public List getRoleWiseTimeTaken(FunctionAppBean functionAppBean)  throws SQLException, JSONException;
	    public JSONArray getRoleWiseTimeTaken(String columnValue) throws SQLException, JSONException;
		
	    public List getTotalCycleTime(FunctionAppBean functionAppBean)  throws SQLException, JSONException;
	    public JSONArray getTotalCycleTime(String columnValue) throws SQLException, JSONException;
		
	    
	    //HR
		public List getNumberOfCasesHR() throws SQLException;
	    public List getNumberOfCasesCreatedHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException;
	    public JSONArray getNumberOfCasesCreatedHR(String columnValue) throws SQLException, JSONException;

	    public List getCasesCreatedCompletedHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException;
	    public JSONArray getCaseCreatedCompanyCompletedHR(String columnValue) throws SQLException, JSONException;
		
	    public List getRoleWiseTimeTakenHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException;
	    public JSONArray getRoleWiseTimeTakenHR(String columnValue) throws SQLException, JSONException;
		
	    public List getTotalCycleTimeHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException;
	    public JSONArray getTotalCycleTimeHR(String columnValue) throws SQLException, JSONException;
		
	
	    //AR
	    public List getNumberOfCasesCreatedAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException;
	    public JSONArray getNumberOfCasesCreatedAR(String columnValue) throws SQLException, JSONException;

	    
	    public List getCasesCreatedCompletedAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException;
	    public JSONArray getCaseCreatedCompanyCompletedAR(String columnValue) throws SQLException, JSONException;
		
	    public List getRoleWiseTimeTakenAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException;
	    public JSONArray getRoleWiseTimeTakenAR(String columnValue) throws SQLException, JSONException;
		
	    public List getTotalCycleTimeAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException;
	    public JSONArray getTotalCycleTimeAR(String columnValue) throws SQLException, JSONException;
		
	    
	//  ************* Chat **************** 
	public String getMessage(String msgID) throws SQLException;
	public String getCustomerMessage(String msgID) throws SQLException;
	public String setUserMessage(String msg,String userID) throws SQLException;
	public String setCustomerMessage(String msg, String userID ) throws SQLException;
	public String getChatWindow() throws SQLException;
	public String setChatStatus(int id) throws SQLException;
	public String deleteChatStatus(int id) throws SQLException;
	public String getDeActiveted() throws SQLException;
	public int getDeActivetedId( int id) throws SQLException;
	public String getAlerts() throws SQLException;
	
	public String saveFileToDatabase(String fileName) throws SQLException,IOException;
	
	
}
