package com.jsw.dms.extraction.service;

import java.util.Properties;
import java.util.concurrent.Callable;

import javax.security.auth.Subject;

import org.springframework.beans.factory.annotation.Autowired;

import com.filenet.api.collection.IndependentObjectSet;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.query.SearchSQL;
import com.filenet.api.query.SearchScope;
import com.filenet.api.util.UserContext;
import com.ibm.casemgmt.api.context.P8ConnectionCache;
import com.ibm.casemgmt.api.objectref.ObjectStoreReference;

public class DmsDataExtractor extends AbstractFileNetService implements Callable {

	//@Autowired
	Properties prop;
	
	private final String DMS_QUERY = "Gbl_Amount:Float|COM_AssociateID:String|Gbl_BarCodeDC:String|CmAcmCaseState:Integer|Gbl_CaseStatus:String|Gbl_CompanyName:String|Gbl_Currency:String|Gbl_CurrentRole:String|Gbl_DocumentSource:String|Gbl_InvoiceDate:Date|Gbl_Location:String|Gbl_PaymentRefNo:String|AP_Received_Date:Date|AP_SAPReferenceforRectification:String|Gbl_SAPReference:String|CmAcmCaseIdentifier:String|DateCreated:Date|AP_DocumentNumber:String|Gbl_VendorCode:String|Gbl_VendorInvoiceNo:String|Gbl_VendorName:String|AP_CENVATAmount:Float|AP_CENVATReferenceNo:String|AP_CENVATCreditEligibility:String|ArchiveDocumentStatus:String|Gbl_EmpID:String|Gbl_EmpName:String|Gbl_PONumber:String|DateLastModified:Date"; 
	private final String DMS_QUERY_WHERE_CLAUSE= " WHERE [ 2017-02-28T23:00:00Z ]  >= 2016-12-01T00:00:00Z and  [ 2017-02-28T23:00:00Z ] <= DateCreated";
	
	@Override
	public Object call() throws Exception {
		DmsConnection dmsConnection = getDMSConnection();
		Connection connection = dmsConnection.getFileNetConn();
		P8ConnectionCache connCache = dmsConnection.getP8ConnCache();
		Subject subject = UserContext.createSubject(connection, dmsConnection.getUserName(), dmsConnection.getPassword(), null);
		UserContext.get().pushSubject(subject);
		Domain domain = dmsConnection.getDomain();
		ObjectStore objectStore = Factory.ObjectStore.fetchInstance(domain, getObjectStore(), null);
		ObjectStoreReference osRef = new ObjectStoreReference(objectStore);
		
		SearchSQL sqlObject = new SearchSQL(DMS_QUERY + DMS_QUERY_WHERE_CLAUSE);
		SearchScope searchScope = new SearchScope(objectStore);
		
		IndependentObjectSet objSet  = searchScope.fetchObjects(sqlObject,	0, null, true);
		
		return null;
	}

}
