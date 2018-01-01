package jsw.report.delegate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import jsw.report.service.VwService;
import jsw.report.viewBean.*;

public class VwDelegate {

	private VwService vwService;

	public VwService getVwService() {
		return vwService;
	}

	public void setVwService(VwService vwService) {
		this.vwService = vwService;
	}

	//*****************************Master********************
	//**************Master belapur**************
		public List getFunctionManagement() throws SQLException{
			// TODO Auto-generated method stub
			return null;//vwService.getFunctionManagement();
		}
		public boolean addFunction(Function contract) throws SQLException{
			return false;//vwService. addFunction(contract);
		}
		public boolean deletefunction(int id) throws SQLException {
			// TODO Auto-generated method stub
			return false;// vwService.deleteFunction(id);
		}
		public Function editFunction(int id) throws SQLException{
			return null;//vwService.editFunction(id);
		}
		public boolean updatefunction(Function stage) throws SQLException{
			return false;//vwService.updatefunction(stage);
		}
	//****************************End*********************
	
	
	
	public boolean updateTest(Tests test) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.updateTest(test);
	}

	public boolean updateDashboard(RegistrationBean screenID) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.updateDashboard(screenID);
	}
	public boolean updateScreenID(RegistrationBean screenID) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.updateScreenID(screenID);
	}
	public Tests editTest(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.editTest(id);
	}

	public boolean deleteTest(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.deleteTest(id);
	}

	public boolean addTest(Tests test) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.addTest(test);
	}

	public boolean addRole(Role role) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.addRole(role);
	}

	public List getTest() throws SQLException {
		return vwService.getTest();
	}

	public List getRole() throws SQLException {
		return vwService.getRole();
	}

	public List<RegistrationBean> getAllUsers() throws SQLException {
		return vwService.getAllUsers();
	}

	public JSONArray getMenuTree() throws Exception {

		return vwService.getMenuTree();

	}

	public List<String> getUserName() throws SQLException {
		return vwService.getUserName();
	}

	public boolean addUser(RegistrationBean regis) throws SQLException {
		return vwService.addUser(regis);
	}

	public List getDealer() throws SQLException {
		// TODO Auto-generated method stub
		return vwService.getDealer();
	}

	public boolean deleteDealerList(int id) throws SQLException {
		return vwService.deleteDealerList(id);
	}

	public List getDealerById(int id) throws SQLException {
		return vwService.getDealerById(id);
	}

	public boolean updateDealer(Dealer stage) throws SQLException {
		return vwService.updateDealer(stage);
	}

	public boolean addDealer(Dealer test) throws SQLException {
		return vwService.addDealer(test);
	}

	// ******************Product************
	public List getOrder() throws SQLException {
		return vwService.getOrder();

	}

	public List getProduct() throws SQLException {
		return vwService.getProduct();

	}

	public boolean addOrder(Orders order) throws SQLException {
		return vwService.addOrder(order);

	}

	public boolean deleteOrderList(int id) throws SQLException {
		return vwService.deleteOrderList(id);
	}

	public boolean updateOrder(Orders stage) throws SQLException {
		return vwService.updateOrder(stage);
	}

	public List getOrderById(int id) throws SQLException {
		return vwService.getOrderById(id);

	}

	// ****************End***********
	// ******************Service************
	public List getService() throws SQLException {
		return vwService.getService();

	}

	public boolean addService(Service order) throws SQLException {
		return vwService.addService(order);

	}

	public boolean deleteServiceList(int id) throws SQLException {
		return vwService.deleteServiceList(id);
	}

	public boolean updateService(Service stage) throws SQLException {
		return vwService.updateService(stage);
	}

	public List getServiceById(int id) throws SQLException {
		return vwService.getServiceById(id);

	}

	// ***********Schem**********

	public List getDiscountOnMaterials() throws SQLException {
		// TODO Auto-generated method stub
		return vwService.getDiscountOnMaterials();
	}

	public boolean addDiscountOnMaterials(Scheme discount) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.addDiscountOnMaterials(discount);
	}
	// *********End**********

	// ***********Contract**********

	public List getContract() throws SQLException {
		// TODO Auto-generated method stub
		return vwService.getContract();
	}

	// *********End**********

	// ***********Investment**********

	public List getInvestment() throws SQLException {
		// TODO Auto-generated method stub
		return vwService.getInvestment();
	}

	// *********End**********

	// *************File Uploading Importing******

	public boolean addFileName(String stg,FileList f) throws SQLException {
		return vwService.addFileName(stg,f);

	}

	public List getFileList() throws SQLException {
		return vwService.getFileList();

	}

	public String getFileById(int id) throws SQLException {
		return vwService.getFileById(id);
	}

	public boolean deleteFileList(int id) throws SQLException {
		return vwService.deleteFileList(id);
	}
	// ************End*************

	// ************Document Type**********
	public List getDocumentList() throws SQLException {
		return vwService.getDocumentList();
	}

	public boolean addDocumentType(DocumentType regis) throws SQLException {
		return vwService.addDocumentType(regis);
	}

	// ***************End*****************

	// ************Investment Type**********
	public List getInvestmentList() throws SQLException {
		return vwService.getInvestmentList();
	}

	public boolean addInvestmentType(InvestmentType regis) throws SQLException {
		return vwService.addInvestmentType(regis);
	}

	// ***************End*****************

	// ************Binding Type**********
	public List getBindingList() throws SQLException {
		return vwService.getBindingList();
	}

	public boolean addBindingType(Binding regis) throws SQLException {
		return vwService.addBindingType(regis);
	}

	// ***************End*****************

	// ****************Associate***********
	public List getAssociateList() throws SQLException {
		return vwService.getAssociateList();

	}

	public boolean addAssociate(AssociateList regis) throws SQLException {
		return vwService.addAssociate(regis);

	}

	public List getOrderRef() throws SQLException {
		return vwService.getOrderRef();
	}

	public List getOrderAmount(int id) throws SQLException {
		return vwService.getOrderAmount(id);
	}

	// **************End*****************
	// *****************Customer***********
	public List getCustomer() throws SQLException {
		// TODO Auto-generated method stub
		return vwService.getCustomer();
	}

	public boolean addCustomer(Customer test) throws SQLException {
		return vwService.addCustomer(test);
	}

	// *******************End************
	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public RegistrationBean editUser(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.editUser(id);
	}

	public boolean deleteUser(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.deleteUser(id);
	}

	public boolean updateUser(RegistrationBean registrationBean) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.updateUser(registrationBean);
	}

	public List getApprover() throws SQLException {
		// TODO Auto-generated method stub
		return vwService.getApprover();
	}

	public List getSmr() throws SQLException {
		// TODO Auto-generated method stub
		return vwService.getSmr();
	}

	public boolean addApprover(Approver approver) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.addApprover(approver);
	}

	public Approver editApprover(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.editApprover(id);
	}

	public boolean deleteApprover(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.deleteApprover(id);
	}

	public boolean updateApprover(Approver approver) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.updateApprover(approver);
	}

	public boolean addSmr(UserBackup userbackup) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.addSmr(userbackup);
	}

	public UserBackup editSmr(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.editSmr(id);
	}

	public boolean deleteSmr(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.deleteSmr(id);
	}

	public boolean updateSmr(UserBackup userbackup) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.updateSmr(userbackup);
	}

	public List getPayment() throws SQLException {
		// TODO Auto-generated method stub
		return vwService.getPayment();
	}

	public List getDelievery() throws SQLException {
		// TODO Auto-generated method stub
		return vwService.getDelievery();
	}

	public boolean addPayment(Payment pay) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.addPayment(pay);
	}

	public boolean addDelievery(Delievery deliver) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.addDelievery(deliver);
	}

	public Payment editPayment(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.editPayment(id);
	}

	public boolean deletePayment(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.deletePayment(id);
	}

	public boolean updatePayment(Payment pay) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.updatePayment(pay);
	}

	public Delievery editDelivery(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.editDelivery(id);
	}

	public boolean deleteDelivery(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.deleteDelivery(id);
	}

	public boolean updateDelivery(Delievery pay) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.updateDelivery(pay);
	}

	public List getOrderTrans() throws SQLException {
		// TODO Auto-generated method stub
		return vwService.getOrderTrans();
	}

	public List getInvoice() throws SQLException {
		// TODO Auto-generated method stub
		return vwService.getInvoice();
	}

	public boolean addOrderTrans(OrderTrans pay) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.addOrderTrans(pay);
	}

	public boolean addInvoice(Invoice deliver) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.addInvoice(deliver);
	}

	public OrderTrans editOrderTrans(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.editOrderTrans(id);
	}

	public boolean deleteOrderTrans(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.deleteOrderTrans(id);
	}

	public boolean updateOrderTrans(OrderTrans pay) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.updateOrderTrans(pay);
	}

	public Invoice editInvoice(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.editInvoice(id);
	}

	public boolean deleteInvoice(int id) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.deleteInvoice(id);
	}

	public boolean updateInvoice(Invoice pay) throws SQLException {
		// TODO Auto-generated method stub
		return vwService.updateInvoice(pay);
	}
	
	//HR
	public List getNumberOfCases() throws SQLException{
		// TODO Auto-generated method stub
		return vwService.getNumberOfCases();
	}
	public List getNumberOfCasesHR() throws SQLException{
		// TODO Auto-generated method stub
		return vwService.getNumberOfCasesHR();
	}
	public List getCasesCreated(FunctionAppBean functionAppBean) throws SQLException, JSONException{
		return vwService.getCasesCreated(functionAppBean);
	}
	
	

    public List getCasesCreatedCompletedHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getCasesCreatedCompletedHR(functionAppBean);
	}
    public JSONArray getCaseCreatedCompanyCompletedHR(String columnValue) throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getCaseCreatedCompanyCompletedHR(columnValue);
	}
	
    public List getRoleWiseTimeTakenHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getRoleWiseTimeTakenHR(functionAppBean);
	}
    public JSONArray getRoleWiseTimeTakenHR(String columnValue) throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getRoleWiseTimeTakenHR(columnValue);
	}
	
    public List getTotalCycleTimeHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getTotalCycleTimeHR(functionAppBean);
	}
    public JSONArray getTotalCycleTimeHR(String columnValue) throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getTotalCycleTimeHR(columnValue);
	}
	
	
  // AR

    public List getNumberOfCasesCreatedAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getNumberOfCasesCreatedAR(functionAppBean);
	}
    public JSONArray getNumberOfCasesCreatedAR(String columnValue) throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getNumberOfCasesCreatedAR(columnValue);
	}

    public List getCasesCreatedCompletedAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getCasesCreatedCompletedAR(functionAppBean);
	}
    public JSONArray getCaseCreatedCompanyCompletedAR(String columnValue) throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getCaseCreatedCompanyCompletedAR(columnValue);
	}
	
    public List getRoleWiseTimeTakenAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getRoleWiseTimeTakenAR(functionAppBean);
	}
    public JSONArray getRoleWiseTimeTakenAR(String columnValue) throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getRoleWiseTimeTakenAR(columnValue);
	}
	
    public List getTotalCycleTimeAR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getTotalCycleTimeAR(functionAppBean);
	}
    public JSONArray getTotalCycleTimeAR(String columnValue) throws SQLException, JSONException{
		// TODO Auto-generated method stub
		return vwService.getTotalCycleTimeAR(columnValue);
	}

	
	
	
	// ****************End***********
//  ************* Chat **************** 
	public String getMessage(String msgID) throws SQLException{
		// TODO Auto-generated method stub
		return vwService.getMessage(msgID);
	}
	public String getCustomerMessage(String msgID) throws SQLException{
		// TODO Auto-generated method stub
		return vwService.getCustomerMessage(msgID);
	}
	public String setUserMessage(String msg,String userID) throws SQLException{
		// TODO Auto-generated method stub
		return vwService.setUserMessage(msg,userID) ;
	}
	public String setCustomerMessage(String msg, String userID ) throws SQLException{
		// TODO Auto-generated method stub
		return vwService.setCustomerMessage(msg, userID );
	}
	public String getChatWindow() throws SQLException{
		// TODO Auto-generated method stub
		return vwService.getChatWindow();
	}
	public String setChatStatus(int id) throws SQLException{
		// TODO Auto-generated method stub
		return vwService.setChatStatus(id);
	}
	public String deleteChatStatus(int id) throws SQLException{
		// TODO Auto-generated method stub
		return vwService.deleteChatStatus(id);
	}
	public String getDeActiveted() throws SQLException{
		// TODO Auto-generated method stub
		return vwService.getDeActiveted();
	}
	public int getDeActivetedId( int id) throws SQLException{
		// TODO Auto-generated method stub
		return vwService.getDeActivetedId(id) ;
	}
	public String getAlerts() throws SQLException{
		// TODO Auto-generated method stub
		return vwService.getAlerts();
	}
	public String saveFileToDatabase(String fileName) throws SQLException,IOException{
		// TODO Auto-generated method stub
		return vwService.saveFileToDatabase(fileName);
	}
	
	public boolean updatefileStatus(int id) throws SQLException{
		// TODO Auto-generated method stub
		return vwService.updatefileStatus(id);
	}
	
	public JSONArray getCaseCreatedCompany(String columnValue) throws SQLException, JSONException {
		// TODO Auto-generated method stub
		return vwService.getCaseCreatedCompany(columnValue);
	}
	
	
	 public List getCasesCreatedCompleted(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			return vwService.getCasesCreatedCompleted(functionAppBean);
		}
	    public JSONArray getCaseCreatedCompanyCompleted(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwService.getCaseCreatedCompanyCompleted(columnValue);
		}
		
	    public List getRoleWiseTimeTaken(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			return vwService.getRoleWiseTimeTaken(functionAppBean);
		}
	    public JSONArray getRoleWiseTimeTaken(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwService.getRoleWiseTimeTaken(columnValue);
		}
		
	    public List getTotalCycleTime(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			return vwService.getTotalCycleTime(functionAppBean);
		}
	    public JSONArray getTotalCycleTime(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwService.getTotalCycleTime(columnValue);
		}
	    
	    
		
		
	    public List getNumberOfCasesCreatedHR(FunctionAppBean functionAppBean)  throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwService.getNumberOfCasesCreatedHR(functionAppBean);
		}
	    public JSONArray getNumberOfCasesCreatedHR(String columnValue) throws SQLException, JSONException{
			// TODO Auto-generated method stub
			return vwService.getNumberOfCasesCreatedHR(columnValue);
		}

	    
	    
	
}
