package com.jsw.dms.extraction.beans;



import java.sql.Date;

public class AccountPayable   {
	
	String SRNo;		
	String WorkflowName;
	String QueueName;
	String StepName;
	Date StartTime;
	Date EndTime;
	String F_CaseFolder;
	Float Duration;
	String Response;
	String UserName;	
	Float  Gbl_Amount;
	String COM_AssociateID;
	String Gbl_BarCodeDC;
	Integer CmAcmCaseState;
	String Gbl_CaseStatus;
	String Gbl_CompanyName;
	String Gbl_Currency;
	String Gbl_CurrentRole;
	String Gbl_DocumentSource;
	Date   Gbl_InvoiceDate;
	String Gbl_Location;
	String Gbl_PaymentRefNo;
	Date   AP_Received_Date;
	String AP_SAPReferenceforRectification;
	String Gbl_SAPReference;
	String CmAcmCaseIdentifier;
	Date   DateCreated;
	String AP_DocumentNumber;
	String Gbl_VendorCode;
	String Gbl_VendorInvoiceNo;
	String Gbl_VendorName;
	Float  AP_CENVATAmount;
	String AP_CENVATReferenceNo;
	String AP_CENVATCreditEligibility;
	String ArchiveDocumentStatus;
	String Gbl_EmpID;
	String Gbl_EmpName;
	String Gbl_PONumber;
	Date   DateLastModified;
	String Comments;

	public AccountPayable(){
		
	}

	public String getSRNo() {
		return SRNo;
	}

	public void setSRNo(String sRNo) {
		SRNo = sRNo;
	}

	public String getWorkflowName() {
		return WorkflowName;
	}

	public void setWorkflowName(String workflowName) {
		WorkflowName = workflowName;
	}

	public String getQueueName() {
		return QueueName;
	}

	public void setQueueName(String queueName) {
		QueueName = queueName;
	}

	public String getStepName() {
		return StepName;
	}

	public void setStepName(String stepName) {
		StepName = stepName;
	}

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public Date getEndTime() {
		return EndTime;
	}

	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}

	public String getF_CaseFolder() {
		return F_CaseFolder;
	}

	public void setF_CaseFolder(String f_CaseFolder) {
		F_CaseFolder = f_CaseFolder;
	}

	public Float getDuration() {
		return Duration;
	}

	public void setDuration(Float duration) {
		Duration = duration;
	}

	public String getResponse() {
		return Response;
	}

	public void setResponse(String response) {
		Response = response;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public Float getGbl_Amount() {
		return Gbl_Amount;
	}

	public void setGbl_Amount(Float gbl_Amount) {
		Gbl_Amount = gbl_Amount;
	}

	public String getCOM_AssociateID() {
		return COM_AssociateID;
	}

	public void setCOM_AssociateID(String cOM_AssociateID) {
		COM_AssociateID = cOM_AssociateID;
	}

	public String getGbl_BarCodeDC() {
		return Gbl_BarCodeDC;
	}

	public void setGbl_BarCodeDC(String gbl_BarCodeDC) {
		Gbl_BarCodeDC = gbl_BarCodeDC;
	}

	public Integer getCmAcmCaseState() {
		return CmAcmCaseState;
	}

	public void setCmAcmCaseState(Integer cmAcmCaseState) {
		CmAcmCaseState = cmAcmCaseState;
	}

	public String getGbl_CaseStatus() {
		return Gbl_CaseStatus;
	}

	public void setGbl_CaseStatus(String gbl_CaseStatus) {
		Gbl_CaseStatus = gbl_CaseStatus;
	}

	public String getGbl_CompanyName() {
		return Gbl_CompanyName;
	}

	public void setGbl_CompanyName(String gbl_CompanyName) {
		Gbl_CompanyName = gbl_CompanyName;
	}

	public String getGbl_Currency() {
		return Gbl_Currency;
	}

	public void setGbl_Currency(String gbl_Currency) {
		Gbl_Currency = gbl_Currency;
	}

	public String getGbl_CurrentRole() {
		return Gbl_CurrentRole;
	}

	public void setGbl_CurrentRole(String gbl_CurrentRole) {
		Gbl_CurrentRole = gbl_CurrentRole;
	}

	public String getGbl_DocumentSource() {
		return Gbl_DocumentSource;
	}

	public void setGbl_DocumentSource(String gbl_DocumentSource) {
		Gbl_DocumentSource = gbl_DocumentSource;
	}

	public Date getGbl_InvoiceDate() {
		return Gbl_InvoiceDate;
	}

	public void setGbl_InvoiceDate(Date gbl_InvoiceDate) {
		Gbl_InvoiceDate = gbl_InvoiceDate;
	}

	public String getGbl_Location() {
		return Gbl_Location;
	}

	public void setGbl_Location(String gbl_Location) {
		Gbl_Location = gbl_Location;
	}

	public String getGbl_PaymentRefNo() {
		return Gbl_PaymentRefNo;
	}

	public void setGbl_PaymentRefNo(String gbl_PaymentRefNo) {
		Gbl_PaymentRefNo = gbl_PaymentRefNo;
	}

	public Date getAP_Received_Date() {
		return AP_Received_Date;
	}

	public void setAP_Received_Date(Date aP_Received_Date) {
		AP_Received_Date = aP_Received_Date;
	}

	public String getAP_SAPReferenceforRectification() {
		return AP_SAPReferenceforRectification;
	}

	public void setAP_SAPReferenceforRectification(String aP_SAPReferenceforRectification) {
		AP_SAPReferenceforRectification = aP_SAPReferenceforRectification;
	}

	public String getGbl_SAPReference() {
		return Gbl_SAPReference;
	}

	public void setGbl_SAPReference(String gbl_SAPReference) {
		Gbl_SAPReference = gbl_SAPReference;
	}

	public String getCmAcmCaseIdentifier() {
		return CmAcmCaseIdentifier;
	}

	public void setCmAcmCaseIdentifier(String cmAcmCaseIdentifier) {
		CmAcmCaseIdentifier = cmAcmCaseIdentifier;
	}

	public Date getDateCreated() {
		return DateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		DateCreated = dateCreated;
	}

	public String getAP_DocumentNumber() {
		return AP_DocumentNumber;
	}

	public void setAP_DocumentNumber(String aP_DocumentNumber) {
		AP_DocumentNumber = aP_DocumentNumber;
	}

	public String getGbl_VendorCode() {
		return Gbl_VendorCode;
	}

	public void setGbl_VendorCode(String gbl_VendorCode) {
		Gbl_VendorCode = gbl_VendorCode;
	}

	public String getGbl_VendorInvoiceNo() {
		return Gbl_VendorInvoiceNo;
	}

	public void setGbl_VendorInvoiceNo(String gbl_VendorInvoiceNo) {
		Gbl_VendorInvoiceNo = gbl_VendorInvoiceNo;
	}

	public String getGbl_VendorName() {
		return Gbl_VendorName;
	}

	public void setGbl_VendorName(String gbl_VendorName) {
		Gbl_VendorName = gbl_VendorName;
	}

	public Float getAP_CENVATAmount() {
		return AP_CENVATAmount;
	}

	public void setAP_CENVATAmount(Float aP_CENVATAmount) {
		AP_CENVATAmount = aP_CENVATAmount;
	}

	public String getAP_CENVATReferenceNo() {
		return AP_CENVATReferenceNo;
	}

	public void setAP_CENVATReferenceNo(String aP_CENVATReferenceNo) {
		AP_CENVATReferenceNo = aP_CENVATReferenceNo;
	}

	public String getAP_CENVATCreditEligibility() {
		return AP_CENVATCreditEligibility;
	}

	public void setAP_CENVATCreditEligibility(String aP_CENVATCreditEligibility) {
		AP_CENVATCreditEligibility = aP_CENVATCreditEligibility;
	}

	public String getArchiveDocumentStatus() {
		return ArchiveDocumentStatus;
	}

	public void setArchiveDocumentStatus(String archiveDocumentStatus) {
		ArchiveDocumentStatus = archiveDocumentStatus;
	}

	public String getGbl_EmpID() {
		return Gbl_EmpID;
	}

	public void setGbl_EmpID(String gbl_EmpID) {
		Gbl_EmpID = gbl_EmpID;
	}

	public String getGbl_EmpName() {
		return Gbl_EmpName;
	}

	public void setGbl_EmpName(String gbl_EmpName) {
		Gbl_EmpName = gbl_EmpName;
	}

	public String getGbl_PONumber() {
		return Gbl_PONumber;
	}

	public void setGbl_PONumber(String gbl_PONumber) {
		Gbl_PONumber = gbl_PONumber;
	}

	public Date getDateLastModified() {
		return DateLastModified;
	}

	public void setDateLastModified(Date dateLastModified) {
		DateLastModified = dateLastModified;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}
	
}
