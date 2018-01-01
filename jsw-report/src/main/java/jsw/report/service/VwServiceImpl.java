package jsw.report.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import jsw.report.dao.VwDao;
import jsw.report.viewBean.*;

public class VwServiceImpl implements VwService {
	private VwDao vwDao;

	public VwDao getVwDao() {
		return vwDao;
	}

	public void setVwDao(VwDao vwDao) {
		this.vwDao = vwDao;
	}

	public boolean updateTest(Tests test) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.updateTest(test);
	}

	public boolean addRole(Role role) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.addRole(role);
	}

	public boolean deleteTest(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.deleteTest(id);
	}

	public boolean addTest(Tests test) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.addTest(test);
	}

	public List getTest() throws SQLException {
		return vwDao.getTest();
	}

	public List getRole() throws SQLException {
		return vwDao.getRole();
	}

	public Tests editTest(int id) throws SQLException {
		return vwDao.editTest(id);
	}

	public List<RegistrationBean> getAllUsers() throws SQLException {
		return vwDao.getAllUsers();
	}

	public JSONArray getMenuTree() throws Exception {

		return vwDao.getMenuTree();

	}

	public List<String> getUserName() throws SQLException {
		return vwDao.getUserName();
	}

	public boolean addUser(RegistrationBean regis) throws SQLException {
		return vwDao.addUser(regis);

	}

	public boolean updateScreenID(RegistrationBean screenID) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.updateScreenID(screenID);
	}
	public boolean updateDashboard(RegistrationBean screenID) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.updateDashboard(screenID);
	}
	public List getDealer() throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.getDealer();
	}

	public boolean deleteDealerList(int id) throws SQLException {
		return vwDao.deleteDealerList(id);
	}

	public List getDealerById(int id) throws SQLException {
		return vwDao.getDealerById(id);
	}

	public boolean updateDealer(Dealer stage) throws SQLException {
		return vwDao.updateDealer(stage);
	}

	public boolean addDealer(Dealer test) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.addDealer(test);
	}

	// **************Product***********
	public List getOrder() throws SQLException {
		return vwDao.getOrder();

	}

	public List getProduct() throws SQLException {
		return vwDao.getProduct();

	}

	public boolean addOrder(Orders order) throws SQLException {
		return vwDao.addOrder(order);

	}

	public boolean deleteOrderList(int id) throws SQLException {
		return vwDao.deleteOrderList(id);
	}

	public boolean updateOrder(Orders stage) throws SQLException {
		return vwDao.updateOrder(stage);
	}

	public List getOrderById(int id) throws SQLException {
		return vwDao.getOrderById(id);

	}
	// **********End****

	// **************Service***********
	public List getService() throws SQLException {
		return vwDao.getService();

	}

	public boolean addService(Service order) throws SQLException {
		return vwDao.addService(order);

	}

	public boolean deleteServiceList(int id) throws SQLException {
		return vwDao.deleteServiceList(id);
	}

	public boolean updateService(Service stage) throws SQLException {
		return vwDao.updateService(stage);
	}

	public List getServiceById(int id) throws SQLException {
		return vwDao.getServiceById(id);

	}

	// ************Scheme*****
	public List getDiscountOnMaterials() throws SQLException {

		return vwDao.getDiscountOnMaterials();
	}

	public boolean addDiscountOnMaterials(Scheme discount) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.addDiscountOnMaterials(discount);
	}
	// ***********End***********

	// **********Contract*******
	public List getContract() throws SQLException {

		return vwDao.getContract();
	}

	// ************End*****

	// **********Investment*******
	public List getInvestment() throws SQLException {

		return vwDao.getInvestment();
	}

	// ************End*****
	// **************File Uploading Importing************
	public boolean addFileName(String file,FileList f) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.addFileName(file,f);
	}

	public List getFileList() throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.getFileList();
	}

	public String getFileById(int id) throws SQLException {
		return vwDao.getFileById(id);
	}

	public boolean deleteFileList(int id) throws SQLException {
		return vwDao.deleteFileList(id);

	}

	// **********End******************

	// ***********Document Type*********
	public List getDocumentList() throws SQLException {
		return vwDao.getDocumentList();
	}

	public boolean addDocumentType(DocumentType regis) throws SQLException {
		return vwDao.addDocumentType(regis);
	}

	// *********End*************

	// ***********Investment Type*********
	public List getInvestmentList() throws SQLException {
		return vwDao.getInvestmentList();
	}

	public boolean addInvestmentType(InvestmentType regis) throws SQLException {
		return vwDao.addInvestmentType(regis);
	}

	// *********End*************

	// ***********Binding Type*********
	public List getBindingList() throws SQLException {
		return vwDao.getBindingList();
	}

	public boolean addBindingType(Binding regis) throws SQLException {
		return vwDao.addBindingType(regis);
	}

	// *********End*************

	// ****************Associate**********
	public List getAssociateList() throws SQLException {
		return vwDao.getAssociateList();

	}

	public boolean addAssociate(AssociateList regis) throws SQLException {
		return vwDao.addAssociate(regis);

	}

	public List getOrderRef() throws SQLException {
		return vwDao.getOrderRef();
	}

	public List getOrderAmount(int id) throws SQLException {
		return vwDao.getOrderAmount(id);
	}
	// **************End******************

	// ***********Customer**********

	public List getCustomer() throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.getCustomer();
	}

	public boolean addCustomer(Customer test) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.addCustomer(test);
	}
	// ***********End************

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public RegistrationBean editUser(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.editUser(id);
	}

	public boolean deleteUser(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.deleteUser(id);
	}

	public boolean updateUser(RegistrationBean registrationBean) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.updateUser(registrationBean);
	}

	// approver
	public List getApprover() throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.getApprover();
	}

	public boolean addApprover(Approver approver) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.addApprover(approver);
	}

	public Approver editApprover(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.editApprover(id);
	}

	public boolean deleteApprover(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.deleteApprover(id);
	}

	public boolean updateApprover(Approver approver) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.updateApprover(approver);
	}

	// smr
	public List getSmr() throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.getSmr();
	}

	public boolean addSmr(UserBackup userbackup) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.addSmr(userbackup);
	}

	public UserBackup editSmr(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.editSmr(id);
	}

	public boolean deleteSmr(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.deleteSmr(id);
	}

	public boolean updateSmr(UserBackup userbackup) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.updateSmr(userbackup);
	}

	public List getPayment() throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.getPayment();
	}

	public List getDelievery() throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.getDelievery();
	}

	public boolean addPayment(Payment pay) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.addPayment(pay);
	}

	public boolean addDelievery(Delievery deliver) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.addDelievery(deliver);
	}

	public Payment editPayment(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.editPayment(id);
	}

	public boolean deletePayment(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.deletePayment(id);
	}

	public boolean updatePayment(Payment pay) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.updatePayment(pay);
	}

	public Delievery editDelivery(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.editDelivery(id);
	}

	public boolean deleteDelivery(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.deleteDelivery(id);
	}

	public boolean updateDelivery(Delievery pay) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.updateDelivery(pay);
	}

	public List getOrderTrans() throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.getOrderTrans();
	}

	public List getInvoice() throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.getInvoice();
	}

	public boolean addOrderTrans(OrderTrans pay) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.addOrderTrans(pay);
	}

	public boolean addInvoice(Invoice deliver) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.addInvoice(deliver);
	}

	public OrderTrans editOrderTrans(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.editOrderTrans(id);
	}

	public boolean deleteOrderTrans(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.deleteOrderTrans(id);
	}

	public boolean updateOrderTrans(OrderTrans pay) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.updateOrderTrans(pay);
	}

	public Invoice editInvoice(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.editInvoice(id);
	}

	public boolean deleteInvoice(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.deleteInvoice(id);
	}

	public boolean updateInvoice(Invoice pay) throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.updateInvoice(pay);
	}

	// **********End****

	
	
	//  ************* Chat **************** 
	public String getMessage(String msgID) throws SQLException{
		// TODO Auto-generated method stub
		return vwDao.getMessage(msgID);
	}
	public String getCustomerMessage(String msgID) throws SQLException{
		// TODO Auto-generated method stub
		return vwDao.getCustomerMessage(msgID);
	}
	public String setUserMessage(String msg,String userID) throws SQLException{
		// TODO Auto-generated method stub
		return vwDao.setUserMessage(msg,userID) ;
	}
	public String setCustomerMessage(String msg, String userID ) throws SQLException{
		// TODO Auto-generated method stub
		return vwDao.setCustomerMessage(msg, userID );
	}
	public String getChatWindow() throws SQLException{
		// TODO Auto-generated method stub
		return vwDao.getChatWindow();
	}
	public String setChatStatus(int id) throws SQLException{
		// TODO Auto-generated method stub
		return vwDao.setChatStatus(id);
	}
	public String deleteChatStatus(int id) throws SQLException{
		// TODO Auto-generated method stub
		return vwDao.deleteChatStatus(id);
	}
	public String getDeActiveted() throws SQLException{
		// TODO Auto-generated method stub
		return vwDao.getDeActiveted();
	}
	public int getDeActivetedId( int id) throws SQLException{
		// TODO Auto-generated method stub
		return vwDao.getDeActivetedId(id) ;
	}
	public String getAlerts() throws SQLException{
		// TODO Auto-generated method stub
		return vwDao.getAlerts();
	}
	
	public String saveFileToDatabase(String fileName) throws SQLException,IOException{
		// TODO Auto-generated method stub
		return vwDao.saveFileToDatabase(fileName);
	}
	public boolean updatefileStatus(int id) throws SQLException{
		// TODO Auto-generated method stub
		return vwDao.updatefileStatus(id);
	}
	public List getReportGraphs() throws SQLException {
		// TODO Auto-generated method stub
		return vwDao.getReportGraphs();
	}
	
	
	public List getNumberOfCases() throws SQLException{
		// TODO Auto-generated method stub
		return vwDao.getNumberOfCases();
	}

	public List getCasesCreated(FunctionAppBean functionAppBean) throws SQLException, JSONException{
		return vwDao.getCasesCreated(functionAppBean);
	}
	public JSONArray getCaseCreatedCompany(String columnValue) throws SQLException, JSONException {
		// TODO Auto-generated method stub
		return vwDao.getCaseCreatedCompany(columnValue);
	}
	
	
	
	 public List getCasesCreatedCompleted(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			return vwDao.getCasesCreatedCompleted(functionAppBean);
		}
	    public JSONArray getCaseCreatedCompanyCompleted(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getCaseCreatedCompanyCompleted(columnValue);
		}
		
	    public List getRoleWiseTimeTaken(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			return vwDao.getRoleWiseTimeTaken(functionAppBean);
		}
	    public JSONArray getRoleWiseTimeTaken(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getRoleWiseTimeTaken(columnValue);
		}
		
	    public List getTotalCycleTime(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			return vwDao.getTotalCycleTime(functionAppBean);
		}
	    public JSONArray getTotalCycleTime(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getTotalCycleTime(columnValue);
		}
		
	
	//HR
		public List getNumberOfCasesHR() throws SQLException{
			// TODO Auto-generated method stub
			return vwDao.getNumberOfCasesHR();
		}
	
	    public List getNumberOfCasesCreatedHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getNumberOfCasesCreatedHR(functionAppBean);
		}
	    public JSONArray getNumberOfCasesCreatedHR(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getNumberOfCasesCreatedHR(columnValue);
		}

	    public List getCasesCreatedCompletedHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getCasesCreatedCompletedHR(functionAppBean);
		}
	    public JSONArray getCaseCreatedCompanyCompletedHR(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getCaseCreatedCompanyCompletedHR(columnValue);
		}
		
	    public List getRoleWiseTimeTakenHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getRoleWiseTimeTakenHR(functionAppBean);
		}
	    public JSONArray getRoleWiseTimeTakenHR(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getRoleWiseTimeTakenHR(columnValue);
		}
		
	    public List getTotalCycleTimeHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getTotalCycleTimeHR(functionAppBean);
		}
	    public JSONArray getTotalCycleTimeHR(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getTotalCycleTimeHR(columnValue);
		}
		
	
	
		//AR

	
	
	    public List getNumberOfCasesCreatedAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getNumberOfCasesCreatedAR(functionAppBean);
		}
	    public JSONArray getNumberOfCasesCreatedAR(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getNumberOfCasesCreatedAR(columnValue);
		}

	    public List getCasesCreatedCompletedAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getCasesCreatedCompletedAR(functionAppBean);
		}
	    public JSONArray getCaseCreatedCompanyCompletedAR(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getCaseCreatedCompanyCompletedAR(columnValue);
		}
		
	    public List getRoleWiseTimeTakenAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getRoleWiseTimeTakenAR(functionAppBean);
		}
	    public JSONArray getRoleWiseTimeTakenAR(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getRoleWiseTimeTakenAR(columnValue);
		}
		
	    public List getTotalCycleTimeAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getTotalCycleTimeAR(functionAppBean);
		}
	    public JSONArray getTotalCycleTimeAR(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwDao.getTotalCycleTimeAR(columnValue);
		}
	
	    
	
}
