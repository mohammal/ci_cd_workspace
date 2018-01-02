package com.jsw.dms.extraction.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.jsw.dms.extraction.beans.AccountPayable;

public class DBUtil {
	
	Connection conn;
	Statement stmt;
	
	DBUtil(){
		
	}
	
	public  Connection getConnection(String url)throws SQLException   {
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn=DriverManager.getConnection(  
				"jdbc:sqlserver://10.10.2.145;databaseName=DMSDB","sa","jswsteel@123"); 
		Statement stmt=conn.createStatement(); 
		System.out.println("Initialize conn");
		return conn;
		
		
	}
	public void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		DBUtil db =new DBUtil();
		try {
			db.getConnection("");
			//db.test();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public void test(){
		
		try {
			
			java.util.Date dmsDate= new java.util.Date();
			java.sql.Date sql = new java.sql.Date(dmsDate.getTime());
		    PreparedStatement statement = conn.prepareStatement("INSERT INTO Table1(starttime,endtime,difference) VALUES(?,?,?)");
		    java.sql.Timestamp sqtime = new java.sql.Timestamp(dmsDate.getTime());
		    System.out.println(sqtime.toString());
		    //statement.setDate(1, sqtime);
		    statement.setString(1,sqtime.toString());
		    statement.setString(2,(new Date(System.currentTimeMillis())).toString());
		    statement.setLong(3, DateDiff());
		    
		    statement.execute();
		    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();	}
	//int insertedRecordsCount = statement.executeUpdate();
	}
	public void test1(){
		try {
			
			java.util.Date dmsDate= new java.util.Date();
			java.sql.Date sql = new java.sql.Date(dmsDate.getTime());
			//java.sql.Timestamp stmp = new java.sql.Timestamp(dmsDate.getTime());
		    PreparedStatement statement = conn.prepareStatement("INSERT INTO Demo1 (DOJ,LastDate) VALUES(?,?)");
		    //statement.setTimestamp(2,"?");
		//java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
	  // System.out.println(timestamp);
		statement.setDate(1, sql);
		statement.setDate(2, sql);
		statement.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//int insertedRecordsCount = statement.executeUpdate();
	}*/
	public String insertRecord(AccountPayable aprecord){
		
		PreparedStatement preparedAP;
		
		try {
		System.out.println("Inserting Record in DB");	
		getConnection("");
		preparedAP = conn.prepareStatement("insert into AP_WIP(SrNO,WorkflowName,QueueName,StepName,StartTime,EndTime,Response,UserName,F_CaseFolderID,Duration,Gbl_Amount,COM_AssociateID,Gbl_BarCodeDC,CmAcmCaseState,Gbl_CaseStatus,Gbl_CompanyName,Gbl_Currency,Gbl_CurrentRole,Gbl_DocumentSource,Gbl_InvoiceDate,Gbl_Location,Gbl_PaymentRefNo,AP_Received_Date,AP_SAPReferenceforRectification,Gbl_SAPReference,CmAcmCaseIdentifier,DateCreated,AP_DocumentNumber,Gbl_VendorCode,Gbl_VendorInvoiceNo,Gbl_VendorName,AP_CENVATAmount,AP_CENVATReferenceNo,AP_CENVATCreditEligibility,ArchiveDocumentStatus,Gbl_EmpID,Gbl_EmpName,Gbl_PONumber,DateLastModified,Comments) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		preparedAP.setString(1,aprecord.getSRNo());
		preparedAP.setString(2,aprecord.getWorkflowName());
		preparedAP.setString(3,aprecord.getQueueName());
		preparedAP.setString(4,aprecord.getStepName());
		preparedAP.setString(5, aprecord.getStartTime()==null?"":aprecord.getStartTime()+"");				
		preparedAP.setString(6,aprecord.getEndTime()==null?"":aprecord.getEndTime()+"");
		preparedAP.setString(7,aprecord.getResponse());
		preparedAP.setString(8,aprecord.getUserName());
		preparedAP.setString(9,aprecord.getF_CaseFolder());
		preparedAP.setFloat(10,aprecord.getDuration());
		preparedAP.setFloat(11,aprecord.getGbl_Amount()==null?0.0F:aprecord.getGbl_Amount());
		preparedAP.setString(12,aprecord.getCOM_AssociateID());
		preparedAP.setString(13,aprecord.getGbl_BarCodeDC());
		preparedAP.setInt(14,aprecord.getCmAcmCaseState()==null?0:aprecord.getCmAcmCaseState());
		preparedAP.setString(15,aprecord.getGbl_CaseStatus());
		preparedAP.setString(16,aprecord.getGbl_CompanyName());
		preparedAP.setString(17,aprecord.getGbl_Currency());
		preparedAP.setString(18,aprecord.getGbl_CurrentRole());
		preparedAP.setString(19,aprecord.getGbl_DocumentSource());
		preparedAP.setString(20,aprecord.getGbl_InvoiceDate()==null?"":aprecord.getGbl_InvoiceDate()+"");
		preparedAP.setString(21,aprecord.getGbl_Location());
		preparedAP.setString(22,aprecord.getGbl_PaymentRefNo());
		preparedAP.setDate(23, aprecord.getAP_Received_Date());
		preparedAP.setString(24,aprecord.getAP_SAPReferenceforRectification());
		preparedAP.setString(25,aprecord.getGbl_SAPReference());
		preparedAP.setString(26,aprecord.getCmAcmCaseIdentifier());
		preparedAP.setDate(27, aprecord.getDateCreated());
		preparedAP.setString(28,aprecord.getAP_DocumentNumber());
		preparedAP.setString(29,aprecord.getGbl_VendorCode());
		preparedAP.setString(30,aprecord.getGbl_VendorInvoiceNo());
		preparedAP.setString(31,aprecord.getGbl_VendorName());
		preparedAP.setFloat(32,aprecord.getAP_CENVATAmount()==null?0.0F :aprecord.getAP_CENVATAmount());
		preparedAP.setString(33,aprecord.getAP_CENVATReferenceNo());
		preparedAP.setString(34,aprecord.getAP_CENVATCreditEligibility());
		preparedAP.setString(35,aprecord.getArchiveDocumentStatus());
		preparedAP.setString(36,aprecord.getGbl_EmpID());
		preparedAP.setString(37,aprecord.getGbl_EmpName());
		preparedAP.setString(38,aprecord.getGbl_PONumber());
		preparedAP.setDate(39,aprecord.getDateLastModified());
		preparedAP.setString(40,aprecord.getComments());
		preparedAP.execute();
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return "";
	}
    /*public String insertRecord(AccountRecievable arrecord){
		
    	System.out.println("Inserting TAX record in DB");
    	try {
			getConnection("");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		PreparedStatement preparedAR;
		try {
		
			preparedAR = conn.prepareStatement("insert into AR_WIP("
					+ "SrNO,WorkflowName,QueueName,StepName,StartTime,EndTime,Response,UserName,Duration,F_CaseFolderID,CmAcmCaseIdentifier,COM_AssociateID,Gbl_CaseStatus,Gbl_CompanyName,Gbl_CurrentRole,Gbl_CustomerCode,Gbl_CustomerName,Gbl_DocumentSource,Gbl_ExpiryDate,Gbl_ExternalLCNumber,Gbl_InternalLCNumber,Gbl_IssueDate,Gbl_IssuingBank,Gbl_LCAdvisingBank,Gbl_AmendmentNumber,AR_LCcategory,AR_LCDispatchType,Gbl_LCType,Gbl_Location,Gbl_SAPReference,DateCreated,CmAcmCaseState,Gbl_BOENumber,COM_TransporterName,AR_TypeofBOE,Gbl_Date,COM_CPC,Gbl_BarCodeDC,AR_CreditTransferType,Gbl_Associated_SAP_ReferenceNumber,Gbl_TransferDate,Gbl_Amount,AR_BGNumber,Gbl_ExtensionDate,AR_SAPBGNumber,AP_CrDrType,Gbl_Year,AR_CreditNoteAdviceNumber,COM_TypeofMaterial,AR_TyepofAutoRebateManagement,AR_RefundAdviceNumber,Gbl_Associated_Invoice,Gbl_FinalizationStatement,Gbl_Associated_BOEs,DateLastModified,Comments) values"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedAR.setString(1,arrecord.SRNo);
			preparedAR.setString(2,arrecord.getWorkflowName());
			preparedAR.setString(3,arrecord.getQueueName());
			preparedAR.setString(4,arrecord.getStepName());	
			preparedAR.setString(5, arrecord.getStartTime()==null?"":arrecord.getStartTime()+"");				
			preparedAR.setString(6,arrecord.getEndTime()==null?"":arrecord.getEndTime()+"");
			preparedAR.setString(7,arrecord.getResponse());
			preparedAR.setString(8,arrecord.getUserName());			
			preparedAR.setFloat(9,arrecord.getDuration());
			preparedAR.setString(10,arrecord.getF_CaseFolder());
			preparedAR.setString(11,arrecord.getCmAcmCaseIdentifier());
			preparedAR.setString(12,arrecord.getCOM_AssociateID());
			preparedAR.setString(13,arrecord.getGbl_CaseStatus());
			preparedAR.setString(14,arrecord.getGbl_CompanyName());
			preparedAR.setString(15,arrecord.getGbl_CurrentRole());
			preparedAR.setString(16,arrecord.getGbl_CustomerCode());
			preparedAR.setString(17,arrecord.getGbl_CustomerName());
			preparedAR.setString(18,arrecord.getGbl_DocumentSource());			
			preparedAR.setString(19,arrecord.getGbl_ExpiryDate()==null?"":arrecord.getGbl_ExpiryDate()+"");
			preparedAR.setString(20,arrecord.getGbl_ExternalLCNumber());
			preparedAR.setString(21,arrecord.getGbl_InternalLCNumber());
			preparedAR.setString(22,arrecord.getGbl_IssueDate()==null?"":arrecord.getGbl_IssueDate()+"");
			preparedAR.setString(23,arrecord.getGbl_IssuingBank());
			preparedAR.setString(24,arrecord.getGbl_LCAdvisingBank());
			preparedAR.setInt(25,arrecord.getGbl_AmendmentNumber()==null?0:arrecord.getGbl_AmendmentNumber());
			preparedAR.setString(26,arrecord.getAR_LCcategory());
			preparedAR.setString(27,arrecord.getAR_LCDispatchType());
			preparedAR.setString(28,arrecord.getGbl_LCType());
			preparedAR.setString(29,arrecord.getGbl_Location());
			preparedAR.setString(30,arrecord.getGbl_SAPReference());				
			preparedAR.setString(31,arrecord.getDateCreated()==null?"":arrecord.getDateCreated()+"");			
			preparedAR.setInt(32,arrecord.getCmAcmCaseState()==null?0:arrecord.getCmAcmCaseState());
			preparedAR.setString(33,arrecord.getGbl_BOENumber());
			preparedAR.setString(34,arrecord.getCOM_TransporterName());
			preparedAR.setString(35,arrecord.getAR_TypeofBOE());
			preparedAR.setString(36,arrecord.getGbl_Date()==null?"":arrecord.getGbl_Date()+"");
			preparedAR.setString(37,arrecord.getCOM_CPC()==null?"false":arrecord.getCOM_CPC().toString());
			preparedAR.setString(38,arrecord.getGbl_BarCodeDC());
			preparedAR.setString(39,arrecord.getAR_CreditTransferType());
			preparedAR.setString(40,arrecord.getGbl_Associated_SAP_ReferenceNumber());
			preparedAR.setString(41,arrecord.getGbl_TransferDate()==null?"":arrecord.getGbl_TransferDate()+"");
			preparedAR.setFloat(42,arrecord.getGbl_Amount()==null?0.0F:arrecord.getGbl_Amount());
			preparedAR.setString(43,arrecord.getAR_BGNumber());
			preparedAR.setString(44,""+arrecord.getGbl_ExtensionDate()==null?"":arrecord.getGbl_ExtensionDate()+"");
			preparedAR.setString(45,arrecord.getAR_SAPBGNumber());
			preparedAR.setString(46,arrecord.getAP_CrDrType());
			preparedAR.setString(47,arrecord.getGbl_Year());
			preparedAR.setString(48,arrecord.getAR_CreditNoteAdviceNumber());
			preparedAR.setString(49,arrecord.getCOM_TypeofMaterial());
			preparedAR.setString(50,arrecord.getAR_TyepofAutoRebateManagement());
			preparedAR.setString(51,arrecord.getAR_RefundAdviceNumber());
			preparedAR.setString(52,arrecord.getGbl_Associated_Invoice());
			preparedAR.setString(53,arrecord.getGbl_FinalizationStatement());
			preparedAR.setString(54,arrecord.getGbl_Associated_BOEs());	
			preparedAR.setString(55,arrecord.getDateLastModified()==null?"":arrecord.getDateLastModified()+"");
			preparedAR.setString(56,arrecord.getComments()==null?"":arrecord.getComments()+"");
			preparedAR.execute();
			System.out.println("Inserted AR Record in DB");	
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return "";
}*/

   /* public String insertRecord(RTR rtrrecord){
    	System.out.println("Inserting RTR record in DB");
    	try {
			getConnection("");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	PreparedStatement preparedRTR;
		try {
		
			preparedRTR = conn.prepareStatement("insert into RTR_WIP(SrNO,WorkflowName,QueueName,StepName,StartTime,EndTime,Response,UserName,F_CaseFolderID,Duration,COM_AssociateID,Gbl_CompanyName,Gbl_Location,Gbl_Amount,RTR_ProvisionType,COM_TypeofMaterial,Gbl_InvoiceNumber,Gbl_BarCodeDC,RTR_Typeofrequest,RTR_MonthlySupportCostingMIS,RTR_Budgetallocationtype,Gbl_WBSNO,RTR_FAReconciliationIssueFeedback,Gbl_EmpID,Gbl_EmpName,RTR_ProjectStructureCreationRequest,Gbl_CompanyName1,Gbl_Location_1,RTR_SettlemetRunFeedback,RTR_RequestType,Gbl_SalesOrderNumber,Gbl_Frequency,RTR_RequestingDept,RTR_AssetCapitalizationType,Gbl_AssetType,Gbl_BankAccountNumber,Gbl_GLAccount,Gbl_Year,Gbl_Month,Gbl_SAPReference,Gbl_CurrentRole,Gbl_CaseStatus,CmAcmCaseIdentifier,CmAcmCaseState,DateCreated,ArchiveDocumentStatus,DateLastModified,Comments)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedRTR.setString(1,rtrrecord.SRNo);
			preparedRTR.setString(2,rtrrecord.getWorkflowName());
			preparedRTR.setString(3,rtrrecord.getQueueName());
			preparedRTR.setString(4,rtrrecord.getStepName());
			preparedRTR.setString(5, rtrrecord.getStartTime()==null?"":rtrrecord.getStartTime()+"");				
			preparedRTR.setString(6,rtrrecord.getEndTime()==null?"":rtrrecord.getEndTime()+"");
			preparedRTR.setString(7,rtrrecord.getResponse());
			preparedRTR.setString(8,rtrrecord.getUserName());
			preparedRTR.setString(9,rtrrecord.getF_CaseFolder());
			preparedRTR.setFloat(10,rtrrecord.getDuration());
			preparedRTR.setString(11,rtrrecord.getCOM_AssociateID());
			preparedRTR.setString(12,rtrrecord.getGbl_CompanyName());
			preparedRTR.setString(13,rtrrecord.getGbl_Location());
			preparedRTR.setFloat(14,rtrrecord.getGbl_Amount()==null?0.0F:rtrrecord.getGbl_Amount());
			preparedRTR.setString(15,rtrrecord.getRTR_ProvisionType()==null?"":rtrrecord.getRTR_ProvisionType());
			preparedRTR.setString(16,rtrrecord.getCOM_TypeofMaterial());
			preparedRTR.setString(17,rtrrecord.getGbl_InvoiceNumber());
			preparedRTR.setString(18,rtrrecord.getGbl_BarCodeDC());
			preparedRTR.setString(19,rtrrecord.getRTR_Typeofrequest());
			preparedRTR.setString(20,rtrrecord.getRTR_MonthlySupportCostingMIS());
			preparedRTR.setString(21,rtrrecord.getRTR_Budgetallocationtype());
			preparedRTR.setString(22,rtrrecord.getGbl_WBSNO());
			preparedRTR.setString(23,rtrrecord.getRTR_FAReconciliationIssueFeedback());
			preparedRTR.setString(24,rtrrecord.getGbl_EmpID());
			preparedRTR.setString(25,rtrrecord.getGbl_EmpName());
			preparedRTR.setString(26,rtrrecord.getRTR_ProjectStructureCreationRequest());
			preparedRTR.setString(27,rtrrecord.getGbl_CompanyName1());
			preparedRTR.setString(28,rtrrecord.getGbl_Location_1());
			preparedRTR.setString(29,rtrrecord.getRTR_SettlemetRunFeedback());
			preparedRTR.setString(30,rtrrecord.getRTR_RequestType());
			preparedRTR.setString(31,rtrrecord.getGbl_SalesOrderNumber());
			preparedRTR.setString(32,rtrrecord.getGbl_Frequency());
			preparedRTR.setString(33,rtrrecord.getRTR_RequestingDept());
			preparedRTR.setString(34,rtrrecord.getRTR_AssetCapitalizationType());
			preparedRTR.setString(35,rtrrecord.getGbl_AssetType());
			preparedRTR.setString(36,rtrrecord.getGbl_BankAccountNumber());
			preparedRTR.setString(37,rtrrecord.getGbl_GLAccount());
			preparedRTR.setString(38,rtrrecord.getGbl_Year());
			preparedRTR.setString(39,rtrrecord.getGbl_Month());
			preparedRTR.setString(40,rtrrecord.getGbl_SAPReference());
			preparedRTR.setString(41,rtrrecord.getGbl_CurrentRole());
			preparedRTR.setString(42,rtrrecord.getGbl_CaseStatus());
			preparedRTR.setString(43,rtrrecord.getCmAcmCaseIdentifier());
			preparedRTR.setInt(44,rtrrecord.getCmAcmCaseState()==null?0:rtrrecord.getCmAcmCaseState());
			preparedRTR.setString(45,rtrrecord.getDateCreated()==null?"":rtrrecord.getDateCreated()+"");
			preparedRTR.setString(46,rtrrecord.getArchiveDocumentStatus());
			preparedRTR.setString(47,rtrrecord.getDateLastModified()==null?"":rtrrecord.getDateLastModified()+"");			
			preparedRTR.setString(48,rtrrecord.getComments());
			preparedRTR.execute();
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return "";
    }
	*/
    
  /*  public String insertRecord(HR hrrecord){
    	
    	System.out.println("Inserting HR record in DB");
    	try {
			getConnection("");
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	PreparedStatement preparedHR;
		try {
			
		    preparedHR = conn.prepareStatement("insert into HRM_WIP(SrNO,WorkflowName,QueueName	,StepName,StartTime	,EndTime,Response,UserName,F_CaseFolderID,Duration,COM_AssociateID,CmAcmCaseState,Gbl_CaseStatus,Gbl_CompanyName,Gbl_PayrollArea,Gbl_PersonnelArea,Gbl_CurrentRole,Gbl_Location,CmAcmCaseIdentifier,DateCreated,DateLastModified,Comments) "
		    		+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    preparedHR.setString(1,hrrecord.getSRNo());
			preparedHR.setString(2,hrrecord.getWorkflowName());
			preparedHR.setString(3,hrrecord.getQueueName());
			preparedHR.setString(4,hrrecord.getStepName());
			preparedHR.setString(5, hrrecord.getStartTime()==null?"":hrrecord.getStartTime()+"");				
			preparedHR.setString(6,hrrecord.getEndTime()==null?"":hrrecord.getEndTime()+"");
			preparedHR.setString(7,hrrecord.getResponse()==null?"":hrrecord.getResponse()+"");
			preparedHR.setString(8,hrrecord.getUserName());
			preparedHR.setString(9,hrrecord.getF_CaseFolder());
			preparedHR.setFloat(10,hrrecord.getDuration()==null?0.0F:hrrecord.getDuration());
			preparedHR.setString(11,hrrecord.getCOM_AssociateID());
			preparedHR.setInt(12,hrrecord.getCmAcmCaseState()==null?0:hrrecord.getCmAcmCaseState());
			preparedHR.setString(13,hrrecord.getGbl_CaseStatus());
			preparedHR.setString(14,hrrecord.getGbl_CompanyName());
			preparedHR.setString(15,hrrecord.getGbl_PayrollArea());
			preparedHR.setString(16,hrrecord.getGbl_PersonnelArea());
			preparedHR.setString(17,hrrecord.getGbl_CurrentRole());
			preparedHR.setString(18,hrrecord.getGbl_Location());
			preparedHR.setString(19,hrrecord.getCmAcmCaseIdentifier());
			preparedHR.setString(20,hrrecord.getDateCreated()==null?"":hrrecord.getDateCreated()+"");
			preparedHR.setString(21,hrrecord.getDateLastModified()==null?"":hrrecord.getDateLastModified()+"");
			preparedHR.setString(22,hrrecord.getComments());			
			preparedHR.execute();
			System.out.println("Insert HR record in DB successfuly");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return "";
    }*/
   /* public String insertRecord(TAX taxrecord){
    	System.out.println("Inserting TAX record in DB");
    	try {
			getConnection("");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	PreparedStatement preparedTAX;
		try {
		
			//preparedTAX = conn.prepareStatement("insert into [dbo].[TAX_WIP](SRNo,WorkflowName,QueueName,StepName,StartTime,EndTime,Response,UserName,Duration,COM_ABGformatsenttovendor,Gbl_AcknowledgementNumber,Gbl_Amount,TAX_AREType,COM_AssociateID,Gbl_BarCodeDC,Gbl_CaseStatus,Gbl_CompanyName,Gbl_CurrentRole,Gbl_EmpID,TAX_ExciseReturnFormType,Gbl_FormNumber,Gbl_Frequency,Gbl_Location,AP_MISReport,Gbl_Month,AP_PortalPaymentType,Gbl_QuarterOfYear,Gbl_Reason,Gbl_ReceiptNumber,TAX_Requestor,Gbl_SAPReference,Gbl_TDSandTCSFormType,Gbl_TDSReturnFormType,TAX_TypeofForm,TAX_TypeofJE,AP_TypeofPayment,TAX_TypeofReturn,TAX_TypeofServiceTaxPayment,TAX_TypeofTDS,Gbl_VATCSTReturnType,Gbl_Year,CmAcmCaseIdentifier,DateCreated,Id,DateLastModified,Comments)  values (?,?,?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,?)");
			preparedTAX = conn.prepareStatement("insert into [dbo].[TAX_WIP](SRNo,WorkflowName,QueueName,StepName,StartTime,EndTime,Response,UserName,F_CaseFolderID,Duration,COM_ABGformatsenttovendor,Gbl_AcknowledgementNumber,Gbl_Amount,TAX_AREType,COM_AssociateID,Gbl_BarCodeDC,Gbl_CaseStatus,Gbl_CompanyName,Gbl_CurrentRole,Gbl_EmpID,TAX_ExciseReturnFormType,Gbl_FormNumber,Gbl_Frequency,Gbl_Location,AP_MISReport,Gbl_Month,AP_PortalPaymentType,Gbl_QuarterOfYear,Gbl_Reason,Gbl_ReceiptNumber,TAX_Requestor,Gbl_SAPReference,Gbl_TDSandTCSFormType,Gbl_TDSReturnFormType,TAX_TypeofForm,TAX_TypeofJE,AP_TypeofPayment,TAX_TypeofReturn,TAX_TypeofServiceTaxPayment,TAX_TypeofTDS,Gbl_VATCSTReturnType,Gbl_Year,CmAcmCaseIdentifier,DateCreated,Id,DateLastModified,Comments)" 
			      + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					
					
					//+ ",Gbl_FormNumber,Gbl_Frequency,Gbl_Location,AP_MISReport,Gbl_Month,AP_PortalPaymentType,Gbl_QuarterOfYear,Gbl_Reason,Gbl_ReceiptNumber,TAX_Requestor,Gbl_SAPReference,Gbl_TDSandTCSFormType,Gbl_TDSReturnFormType,TAX_TypeofForm,TAX_TypeofJE,AP_TypeofPayment,TAX_TypeofReturn,TAX_TypeofServiceTaxPayment,TAX_TypeofTDS,Gbl_VATCSTReturnType,Gbl_Year,CmAcmCaseIdentifier,DateCreated,Id,DateLastModified,Comments)  values (?,?,?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?)");
					
			preparedTAX.setString(1,taxrecord.SRNo);
			preparedTAX.setString(2,taxrecord.getWorkflowName());
			preparedTAX.setString(3,taxrecord.getQueueName());
			preparedTAX.setString(4,taxrecord.getStepName());
			///preparedTAX.setTimestamp(5, convert(datetime,taxrecord.getStartTime(),105));
			preparedTAX.setDate(5, taxrecord.getStartTime() );
			//preparedTAX.setTimestamp(6, taxrecord.getEndTime());
			preparedTAX.setDate(6, taxrecord.getEndTime());
			preparedTAX.setString(7,taxrecord.getResponse());
			preparedTAX.setString(8,taxrecord.getUserName());
			preparedTAX.setString(9,taxrecord.getF_CaseFolder());
			preparedTAX.setFloat(10,taxrecord.getDuration());
			preparedTAX.setString(11,taxrecord.getCOM_ABGformatsenttovendor());
			preparedTAX.setString(12,taxrecord.getGbl_AcknowledgementNumber());
			preparedTAX.setFloat(13,taxrecord.getGbl_Amount()==null?0.0F:taxrecord.getGbl_Amount());
			preparedTAX.setString(14,taxrecord.getTAX_AREType());
			preparedTAX.setString(15,taxrecord.getCOM_AssociateID());
			preparedTAX.setString(16,taxrecord.getGbl_BarCodeDC());
			preparedTAX.setString(17,taxrecord.getGbl_CaseStatus());
			preparedTAX.setString(18,taxrecord.getGbl_CompanyName());
			preparedTAX.setString(19,taxrecord.getGbl_CurrentRole());
			preparedTAX.setString(20,taxrecord.getGbl_EmpID());
			preparedTAX.setString(21,taxrecord.getTAX_ExciseReturnFormType());
			preparedTAX.setString(22,taxrecord.getGbl_FormNumber());
			preparedTAX.setString(23,taxrecord.getGbl_Frequency());
			preparedTAX.setString(24,taxrecord.getGbl_Location());
			preparedTAX.setString(25,taxrecord.getAP_MISReport());
			preparedTAX.setString(26,taxrecord.getGbl_Month());
			preparedTAX.setString(27,taxrecord.getAP_PortalPaymentType());
			preparedTAX.setString(28,taxrecord.getGbl_QuarterOfYear());
			preparedTAX.setString(29,taxrecord.getGbl_Reason());
			preparedTAX.setString(30,taxrecord.getGbl_ReceiptNumber());
			preparedTAX.setString(31,taxrecord.getTAX_Requestor());
			preparedTAX.setString(32,taxrecord.getGbl_SAPReference());
			preparedTAX.setString(33,taxrecord.getGbl_TDSandTCSFormType());
			preparedTAX.setString(34,taxrecord.getGbl_TDSReturnFormType());
			preparedTAX.setString(35,taxrecord.getTAX_TypeofForm());
			preparedTAX.setString(36,taxrecord.getTAX_TypeofJE());
			preparedTAX.setString(37,taxrecord.getAP_TypeofPayment());
			preparedTAX.setString(38,taxrecord.getTAX_TypeofReturn());
			preparedTAX.setString(39,taxrecord.getTAX_TypeofServiceTaxPayment());
			preparedTAX.setString(40,taxrecord.getTAX_TypeofTDS());
			preparedTAX.setString(41,taxrecord.getGbl_VATCSTReturnType());
			preparedTAX.setString(42,taxrecord.getGbl_Year());
			preparedTAX.setString(43,taxrecord.getCmAcmCaseIdentifier());			
			preparedTAX.setDate(44,taxrecord.getStartTime());
			preparedTAX.setString(45,taxrecord.getId());
			//preparedTAX.setTimestamp(46,taxrecord.getDateLastModified());
			preparedTAX.setDate(46, taxrecord.getStartTime());
			preparedTAX.setString(47, taxrecord.getComments());
			preparedTAX.execute();
			System.out.println("Executed successfuly");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return "";
    }*/
    /*public String insertRecord(Commercial commrecord){
    	PreparedStatement preparedComm;
		try {
		
			preparedComm = conn.prepareStatement("insert into COMMERCIAL_WIP(SRNo,Workflow Name,	QueueName,	StepName,	StartTime,	EndTime,	Response,	UserName,	Duration,	COM_AssociateID,	COM_AutoProcurementStatus,	COM_BGValidityDate,	COM_Case,	Gbl_CaseStatus,	COM_CHAName,	COM_CollatedPRNos,	COM_CollectiveNo,	Gbl_CompanyName,	COM_CompleteGRNandPaymentStatus,	COM_CPC,	COM_TaskStatus,	COM_CumulativeLineItemNo,	COM_CurrentRole,	COM_DelayStatus,	COM_DetailstobeUpdated,	Gbl_DocumentStatus,	COM_DocumentType,	AP_InvoiceDueDate,	COM_Emergency,	COM_EsugamVAT47Status,	COM_ExcessCasestatus,	COM_FloatedRFQNos,	COM_FreightForwarderAbroad,	COM_FreightForwarderDomestic,	Gbl_FRFNumber,	COM_HandleDelay,	COM_ITSMTicketNo,	COM_LastModifiedBy,	COM_LDClauseApplicable,	Gbl_LineItemNo,	Gbl_Location,	COM_LocationBuyerID,	Gbl_MaterialCode,	Gbl_MaterialDescription,	COM_MaterialReadinessStatus,	COM_MISReportStatus,	COM_NegotiationStatus,	COM_NewPOStatus,	COM_NewVendorStatus,	COM_NFANO,	COM_NFANONew,	COM_NonNegotiableDocumentsStatus,	COM_PaymentCoordinationStatus,	COM_PaymentType,	COM_LocationBuyerID,	COM_PlantVisitDate,	COM_POAcknowledgementStatus,	COM_POAmendmentStatusforNonPR,	COM_POAmendmentStatusforPR,	COM_PONONew,	COM_POReleaseStatus,	COM_POStatus,	Gbl_PONumber,	COM_PostPOformType,	COM_PRCreatorEmailID,	Gbl_PRNumber,	COM_PRStatus,	COM_Priority,	Gbl_PurchasingGroup,	COM_PurchasingGroupLocationBuyerEmailID,	COM_PurchasingOrganization,	COM_QuotationStatus,	COM_RANo,	COM_RAStartDateandTime,	COM_RAStatus,	COM_RailTransport,	COM_ReasonforPOAmendment,	COM_ReceiptCoordinationStatus_Domestic,	COM_ReceiptCoordinationStatusImports,	COM_RequestType,	COM_ReturnRejectionCase,	Gbl_RFQNumber,	COM_RFQStatus,	COM_RFQ_TaskStatus,	COM_RoadTransport,	COM_SelectedRFQNo,	COM_ShortageCaseStatus,	COM_SIENo,	COM_StoresSupportType,	COM_SWIFTCode,	COM_TaxCodeStatus,	COM_TRNO,	COM_TRStatus,	COM_TransporterName,	COM_TransporterPONo,	COM_TypeofMaterial,	COM_TypeofReport,	COM_TypeofVendor,	COM_UpdateVendorMasterFlag,	COM_VendorAddress,	COM_VendorCode,	COM_VendorEmail,	COM_VendorEvaluationStatus,	COM_VendorGeography,	COM_VendorName,	COMDocumentType,	CmAcmCaseIdentifier,	DateCreated,	Id,	DateLastModified values(?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?)");
			preparedComm.setString(1,commrecord.SRNo);
			preparedComm.setString(2,commrecord.getWorkflowName());
			preparedComm.setString(3,commrecord.getQueueName());
			preparedComm.setString(4,commrecord.getStepName());
			preparedComm.setDate(5, commrecord.getStartTime());
			preparedComm.setDate(6,(Date) commrecord.getEndTime());
			preparedComm.setString(7,commrecord.getResponse());
			preparedComm.setString(8,commrecord.getUserName());
			preparedComm.setString(9,commrecord.getF_CaseFolder());
			preparedComm.setFloat(10,commrecord.getDuration());
			preparedComm.setString(11,commrecord.getCOM_AssociateID());
			preparedComm.setString(12,commrecord.getCOM_AutoProcurementStatus());
			preparedComm.setDate(13,commrecord.getCOM_BGValidityDate());
			preparedComm.setString(14,commrecord.getCOM_Case());
			preparedComm.setString(15,commrecord.getGbl_CaseStatus());
			preparedComm.setString(16,commrecord.getCOM_CHAName());
			preparedComm.setString(17,commrecord.getCOM_CollatedPRNos());
			preparedComm.setString(18,commrecord.getCOM_CollectiveNo());
			preparedComm.setString(19,commrecord.getGbl_CompanyName());
			preparedComm.setString(20,commrecord.getCOM_CompleteGRNandPaymentStatus());
			preparedComm.setBoolean(21,commrecord.getCOM_CPC());
			preparedComm.setString(22,commrecord.getCOM_TaskStatus());
			preparedComm.setString(23,commrecord.getCOM_CumulativeLineItemNo());
			preparedComm.setString(24,commrecord.getCOM_CurrentRole());
			preparedComm.setString(25,commrecord.getCOM_DelayStatus());
			preparedComm.setString(26,commrecord.getCOM_DetailstobeUpdated());
			preparedComm.setString(27,commrecord.getGbl_DocumentStatus());
			preparedComm.setString(28,commrecord.getCOM_DocumentType());
			preparedComm.setDate(29,commrecord.getAP_InvoiceDueDate());
			preparedComm.setBoolean(30,commrecord.getCOM_Emergency());
			preparedComm.setString(31,commrecord.getCOM_EsugamVAT47Status());
			preparedComm.setString(32,commrecord.getCOM_ExcessCasestatus());
			preparedComm.setString(33,commrecord.getCOM_FloatedRFQNos());
			preparedComm.setString(34,commrecord.getCOM_FreightForwarderAbroad());
			preparedComm.setString(35,commrecord.getCOM_FreightForwarderDomestic());
			preparedComm.setString(36,commrecord.getGbl_FRFNumber());
			preparedComm.setBoolean(37,commrecord.getCOM_HandleDelay());
			preparedComm.setString(38,commrecord.getCOM_ITSMTicketNo());
			preparedComm.setString(39,commrecord.getCOM_LastModifiedBy());
			preparedComm.setBoolean(40,commrecord.getCOM_LDClauseApplicable());
			preparedComm.setInt(41,commrecord.getGbl_LineItemNo());
			preparedComm.setString(42,commrecord.getGbl_Location());
			preparedComm.setString(43,commrecord.getCOM_LocationBuyerID());
			preparedComm.setString(44,commrecord.getGbl_MaterialCode());
			preparedComm.setString(45,commrecord.getGbl_MaterialDescription());
			preparedComm.setString(46,commrecord.getCOM_MaterialReadinessStatus());
			preparedComm.setString(47,commrecord.getCOM_MISReportStatus());
			preparedComm.setString(48,commrecord.getCOM_NegotiationStatus());
			preparedComm.setString(49,commrecord.getCOM_NewPOStatus());
			preparedComm.setString(50,commrecord.getCOM_NewVendorStatus());
			preparedComm.setString(51,commrecord.getCOM_NFANO());
			preparedComm.setString(52,commrecord.getCOM_NFANONew());
			preparedComm.setString(53,commrecord.getCOM_NonNegotiableDocumentsStatus());
			preparedComm.setString(54,commrecord.getCOM_PaymentCoordinationStatus());
			preparedComm.setString(55,commrecord.getCOM_PaymentType());
			preparedComm.setString(56,commrecord.getCOM_LocationBuyerID());
			preparedComm.setDate(57,commrecord.getCOM_PlantVisitDate());
			preparedComm.setString(58,commrecord.getCOM_POAcknowledgementStatus());
			preparedComm.setString(59,commrecord.getCOM_POAmendmentStatusforNonPR());
			preparedComm.setString(60,commrecord.getCOM_POAmendmentStatusforPR());
			preparedComm.setString(61,commrecord.getCOM_PONONew());
			preparedComm.setString(62,commrecord.getCOM_POReleaseStatus());
			preparedComm.setString(63,commrecord.getCOM_POStatus());
			preparedComm.setString(64,commrecord.getGbl_PONumber());
			preparedComm.setString(65,commrecord.getCOM_PostPOformType());
			preparedComm.setString(66,commrecord.getCOM_PRCreatorEmailID());
			preparedComm.setString(67,commrecord.getGbl_PRNumber());
			preparedComm.setString(68,commrecord.getCOM_PRStatus());
			preparedComm.setBoolean(69,commrecord.getCOM_Priority());
			preparedComm.setString(70,commrecord.getGbl_PurchasingGroup());
			preparedComm.setString(71,commrecord.getCOM_PurchasingGroupLocationBuyerEmailID());
			preparedComm.setString(72,commrecord.getCOM_PurchasingOrganization());
			preparedComm.setString(73,commrecord.getCOM_QuotationStatus());
			preparedComm.setString(74,commrecord.getCOM_RANo());
			preparedComm.setDate(75,commrecord.getCOM_RAStartDateandTime());
			preparedComm.setString(76,commrecord.getCOM_RAStatus());
			preparedComm.setBoolean(77,commrecord.getCOM_RailTransport());
			preparedComm.setString(78,commrecord.getCOM_ReasonforPOAmendment());
			preparedComm.setString(79,commrecord.getCOM_ReceiptCoordinationStatus_Domestic());
			preparedComm.setString(80,commrecord.getCOM_ReceiptCoordinationStatusImports());
			preparedComm.setString(81,commrecord.getCOM_RequestType());
			preparedComm.setString(82,commrecord.getCOM_ReturnRejectionCase());
			preparedComm.setString(83,commrecord.getGbl_RFQNumber());
			preparedComm.setString(84,commrecord.getCOM_RFQStatus());
			preparedComm.setString(85,commrecord.getCOM_RFQ_TaskStatus());
			preparedComm.setBoolean(86,commrecord.getCOM_RoadTransport());
			preparedComm.setString(87,commrecord.getCOM_SelectedRFQNo());
			preparedComm.setString(88,commrecord.getCOM_ShortageCaseStatus());
			preparedComm.setString(89,commrecord.getCOM_SIENo());
			preparedComm.setString(90,commrecord.getCOM_StoresSupportType());
			preparedComm.setString(91,commrecord.getCOM_SWIFTCode());
			preparedComm.setString(92,commrecord.getCOM_TaxCodeStatus());
			preparedComm.setString(93,commrecord.getCOM_TRNO());
			preparedComm.setString(94,commrecord.getCOM_TRStatus());
			preparedComm.setString(95,commrecord.getCOM_TransporterName());
			preparedComm.setString(96,commrecord.getCOM_TransporterPONo());
			preparedComm.setString(97,commrecord.getCOM_TypeofMaterial());
			preparedComm.setString(98,commrecord.getCOM_TypeofReport());
			preparedComm.setString(99,commrecord.getCOM_TypeofVendor());
			preparedComm.setBoolean(100,commrecord.getCOM_UpdateVendorMasterFlag());
			preparedComm.setString(101,commrecord.getCOM_VendorAddress());
			preparedComm.setString(102,commrecord.getCOM_VendorCode());
			preparedComm.setString(103,commrecord.getCOM_VendorEmail());
			preparedComm.setString(104,commrecord.getCOM_VendorEvaluationStatus());
			preparedComm.setString(105,commrecord.getCOM_VendorGeography());
			preparedComm.setString(106,commrecord.getCOM_VendorName());
			preparedComm.setString(107,commrecord.getCOMDocumentType());
			preparedComm.setString(108,commrecord.getCmAcmCaseIdentifier());
			preparedComm.setDate(109,commrecord.getDateCreated());
			preparedComm.setString(110,commrecord.getId());
			preparedComm.setDate(111,commrecord.getDateLastModified());


		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return "";
    }*/
}
