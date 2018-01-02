package com.jsw.dms.extraction.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;

import javax.security.auth.Subject;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.filenet.api.collection.StringList;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.query.SearchSQL;
import com.filenet.api.query.SearchScope;
import com.filenet.api.util.UserContext;
import com.ibm.casemgmt.api.Case;
import com.ibm.casemgmt.api.context.P8ConnectionCache;
import com.ibm.casemgmt.api.context.SimpleP8ConnectionCache;
import com.ibm.casemgmt.api.objectref.DomainReference;
import com.ibm.casemgmt.api.objectref.ObjectStoreReference;
import com.jsw.dms.extraction.beans.AccountPayable;

import filenet.vw.api.VWFetchType;
import filenet.vw.api.VWGuid;
import filenet.vw.api.VWMapDefinition;
import filenet.vw.api.VWParticipantHistory;
import filenet.vw.api.VWProcess;
import filenet.vw.api.VWRoster;
import filenet.vw.api.VWRosterQuery;
import filenet.vw.api.VWSession;
import filenet.vw.api.VWStepElement;
import filenet.vw.api.VWStepHistory;
import filenet.vw.api.VWStepOccurrenceHistory;
import filenet.vw.api.VWStepWorkObjectHistory;
import filenet.vw.api.VWWorkflowDefinition;
import filenet.vw.api.VWWorkflowHistory;

public class CallableWIPReport implements Callable {

	private static Connection conn;
	private static Subject subject;
	private String mySQLString;
	Folder folder;
	StringTokenizer propStringArray, propStringArray2;
	String propString;
	String outputfilename = "No Data";
	public static Logger logger = Logger.getLogger("ap_logger");
	String propArray[] = null;
	String prNumberArray[] = null;
	String prLocationArray[] = null;
	StringTokenizer allPropSTK = null;
	String locationSplit[] = null;
	DBUtil db;

	String propName = null;
	String propValue = null;
	String propNameType = null;
	String propNameNew = null;
	private static P8ConnectionCache connCache;
	boolean secondtime = false;
	// private static Case caseObj = null;
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	public static DomainReference domainRef = null;
	public static ObjectStoreReference osRef = null;
	public static Domain domain;
	public static ObjectStore objectStoreObj;
	
	
	

	

	ArrayList<String> stepNameList = new ArrayList<String>();
	ArrayList<String> approverParticipantList = new ArrayList<String>();
	ArrayList<String> reviewerParticipantList = new ArrayList<String>();
	ArrayList<String> commentatorParticipantList = new ArrayList<String>();
	ArrayList<String> wobNoList = new ArrayList<String>();
	Object[] valuelist = null;
	String stepNameForUserList = "";
	String stepName = "";
	// private static Properties prop;
	String prno = "";
	String lastSr = "";
	int oldM = -1;
	List list = null;
	String countn = "";
	long idCounter;
	int interval = 0;
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	String sqlQueryString = "";
	String caseType = "";
	int count;
	Properties prop;

	public Object call() throws Exception {
		// Do some special actions particular to this object
		System.out.println("hello...sqlQueryString + " + sqlQueryString);
		String file = genReport(sqlQueryString, caseType, count, prop);
		return file;
	}

	CallableWIPReport(String sqlString, String casetypeName, int cnt, Properties ppt) {

		System.out.println("Initialize the CallableReport sqlString " + sqlString + " Case type name :" + casetypeName);
		sqlQueryString = sqlString;
		caseType = casetypeName;
		count = cnt;
		prop = ppt;
		db = new DBUtil();
		

		System.out.println("Initialization Complete the CallableReport");
	}

	public String genReport(String sqlString, String casetype, int count, Properties prop) {
		

		// logger.info(" Callable Report Inside genReport method:" + sqlString);
		System.out.print("--------------- Executing Callable genReport for WIP -----------------");
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		propString = prop.getProperty("propString0");
		// System.out.println("-----genReport searchScope...." + sqlString);
		// logger.info("--------Start of genReport----------"+sqlString);
		conn = Factory.Connection.getConnection(prop.getProperty("connURI"));
		connCache = new SimpleP8ConnectionCache();
		subject = UserContext.createSubject(conn, prop.getProperty("username"), prop.getProperty("password"), null);

		UserContext.get().pushSubject(subject);
		/*
		 * CaseMgmtContext origCmctx = CaseMgmtContext .set(new
		 * CaseMgmtContext(new SimpleVWSessionCache(), connCache));
		 */
		domain = Factory.Domain.fetchInstance(conn, null, null);
		objectStoreObj = Factory.ObjectStore.fetchInstance(domain, prop.getProperty("objectStoreName"), null);
		osRef = new ObjectStoreReference(objectStoreObj);

		SearchSQL sqlObject = new SearchSQL(sqlString);
		SearchScope searchScope = new SearchScope(objectStoreObj);
		int cnt = 0;
		try {

			com.filenet.api.collection.IndependentObjectSet independentObjectSet = searchScope.fetchObjects(sqlObject,
					0, null, true);
			Iterator<Folder> folderIterator = independentObjectSet.iterator();
			
			VWSession PESession = new VWSession();
			PESession.setBootstrapCEURI(prop.getProperty("connURI"));
			PESession.logon(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("PEConn"));
			VWRoster roster = PESession.getRoster(prop.getProperty("rostername"));
			int queryFlags = VWRoster.QUERY_GET_NO_SYSTEM_FIELDS;
			// int queryFlags = VWRoster.QUERY_NO_OPTIONS;
    		
			String queryFilter = "F_CaseFolder=:A";
			String casefolderID = "";

			// write excel report
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(casetype);
			propStringArray = new StringTokenizer(propString, "|");
			propStringArray2 = new StringTokenizer(propString, "|");
			// System.out.println("Has more Tokens......."+
			// propStringArray.countTokens());
			StringBuilder columnList = new StringBuilder();
			// boolean firsttime=true;
			// if(count==1){
			String businessName=prop.getProperty("businessName");
			AccountPayable aprecord= new AccountPayable();			
			XSSFRow rowhead = sheet.createRow(0);
			rowhead.createCell(0).setCellValue("SR No");
			rowhead.createCell(1).setCellValue("Workflow Name");
			rowhead.createCell(2).setCellValue("QueueName");
			rowhead.createCell(3).setCellValue("StepName");
			rowhead.createCell(4).setCellValue("StartTime");
			rowhead.createCell(5).setCellValue("EndTime");
			rowhead.createCell(6).setCellValue("Response");
			rowhead.createCell(7).setCellValue("UseName");
			rowhead.createCell(8).setCellValue("F_CaseFolder ID");
			rowhead.createCell(9).setCellValue("Duration");

			int cntCell = 10;
			int rowCnt = 1;
			while (propStringArray.hasMoreTokens()) {

				propName = propStringArray.nextToken();
				propNameType = propName.substring(propName.indexOf(":") + 1);
				propNameNew = propName.substring(0, propName.indexOf(":"));
				rowhead.createCell(cntCell).setCellValue(propNameNew);
				cntCell++;

			}
			rowhead.createCell(cntCell).setCellValue("Comments");

			while (folderIterator.hasNext()) {
				// cnt++;
				System.out.print("*");
				list = new ArrayList();
				folder = (Folder) folderIterator.next();
				String comments = "";
				Case cs = Case.getFetchlessInstance(osRef, folder.get_Id());
				// System.out.println("Folder Name"+folder.get_Id().toString()
				// );
				// System.out.println("cs:::"+cs);
				// System.out.println("Fetched cases**********:"+cs.getId() );
				// comments=getCaseComents(cs);

				Object[] substitutionVars = { new VWGuid(folder.get_Id().toString()) };// 09/29/2016
																						// 00:00:00
																						// AM

				int fetchType = VWFetchType.FETCH_TYPE_STEP_ELEMENT;
				VWRosterQuery query = roster.createQuery(null, null, null, queryFlags, queryFilter, substitutionVars,
						fetchType);
				// int m=0;

				while (query.hasNext()) {

					// m=m+1;
					int once = 0;
					VWStepElement stepElement = (VWStepElement) query.next();
					if (stepElement != null) {

						stepName = (stepElement.getStepName() != null ? stepElement.getStepName() : "Review");
						// below condition will exclude cases which is in delay
						// state in workflow

						if (!stepName.equalsIgnoreCase("Review")) {

							// casefolderID=stepElement.getParameterValue("*F_CaseFolder").toString();

							casefolderID = folder.get_Id().toString();
							VWProcess process = stepElement.fetchProcess();
							VWWorkflowDefinition workflowDefinition = process.fetchWorkflowDefinition(false);
							VWMapDefinition[] workflowMaps = workflowDefinition.getMaps();

							for (int i = 0; i < workflowMaps.length; i++) {

								int mapID = workflowMaps[i].getMapId();

								String mapName = workflowMaps[i].getName();
								if (mapName.equalsIgnoreCase("Workflow")) {

									VWWorkflowHistory workflowHistory = process.fetchWorkflowHistory(mapID);
									int l = 0;
									while (workflowHistory.hasNext()) {

										l = l + 1;

										VWStepHistory stepHistory = workflowHistory.next();

										if (!stepNameList.contains(stepHistory.getStepName())) {
											stepNameList.add(stepHistory.getStepName());
										}
										stepNameForUserList = stepHistory.getStepName();

										int k = 0;

										while (stepHistory.hasNext()) {
											k = k + 1;
											VWStepOccurrenceHistory stepOccurenceHistory = stepHistory.next();
											int o = 0;
											while (stepOccurenceHistory.hasNext()) {
												o = o + 1;

												VWStepWorkObjectHistory stepWorkObjectHistory = stepOccurenceHistory
														.next();
												stepWorkObjectHistory.resetFetch();
												int j = 0;

												while (stepWorkObjectHistory.hasNext()) {

													j = j + 1;

													VWParticipantHistory participantHistory = stepWorkObjectHistory
															.next();
													countn = String.valueOf(idCounter++);
													// -----------

													propStringArray2 = new StringTokenizer(propString, "|");

													// valuelist = new
													// Object[propStringArray2.countTokens()+10];

													aprecord.setWorkflowName(workflowDefinition.getName());
													aprecord.setQueueName(participantHistory.getQueueName());
													aprecord.setStepName(stepNameForUserList);
													aprecord.setStartTime(
															convertUtilTOSQLDate(participantHistory.getDateReceived()));
													if (participantHistory.getCompletionDate() == null) {
														aprecord.setEndTime(null);
													} else
														aprecord.setEndTime(convertUtilTOSQLDate(
																participantHistory.getCompletionDate()));

													aprecord.setResponse((String) participantHistory.getResponse());

													aprecord.setF_CaseFolder(casefolderID);
													aprecord.setDuration(new Float(0));

													// Code to be commented
													XSSFRow row = sheet.createRow(rowCnt);
													row.createCell(0).setCellValue(Integer.parseInt(countn));
													row.createCell(1).setCellValue(workflowDefinition.getName());
													row.createCell(2).setCellValue(participantHistory.getQueueName());
													row.createCell(3).setCellValue(stepNameForUserList);
													row.createCell(4)
															.setCellValue(participantHistory.getDateReceived());

													// System.out.print(participantHistory.getCompletionDate());
													if (participantHistory.getCompletionDate() == null) {
														row.createCell(5).setCellValue("");
													} else
														row.createCell(5)
																.setCellValue(participantHistory.getCompletionDate());

													row.createCell(6)
															.setCellValue((String) participantHistory.getResponse());
													row.createCell(7).setCellValue(participantHistory.getUserName());
													row.createCell(8).setCellValue(casefolderID);
													// long diff =
													// getTimeDifference((Date)
													// logElement.getFieldValue("F_EnqueueTime"),
													// logElement.getTimeStamp());
													row.createCell(9).setCellValue("0");

													int counterVal = 10;

													/*
													 * long diff =
													 * getTimeDifference(
													 * participantHistory
													 * .getDateReceived(),
													 * participantHistory
													 * .getCompletionDate());
													 */

													// counterVal=counterVal+10;

													// Object propValueObj=null;
													while (propStringArray2.hasMoreTokens()) {

														propName = propStringArray2.nextToken();

														// propName =
														// prop.getProperty(propArray[0]);
														propNameType = propName.substring(propName.indexOf(":") + 1);
														propNameNew = propName.substring(0, propName.indexOf(":"));
														try {

															if (folder.getProperties().isPropertyPresent(propNameNew)) {

																if (propNameType.equalsIgnoreCase("String")) {

																	if (folder.getProperties()
																			.getStringValue(propNameNew) != null) {
																		row.createCell(counterVal)
																				.setCellValue(folder.getProperties()
																						.getStringValue(propNameNew));
																		setAPRecordData(
																				folder.getProperties()
																						.getStringValue(propNameNew),
																				propNameNew, aprecord, propNameType);

																	} else {
																		row.createCell(counterVal).setCellValue("");
																		setAPRecordData(null, propNameNew, aprecord,
																				propNameType);
																	}
																	// propValueObj=
																	// (folder.getProperties().getStringValue(propNameNew)!=null?folder.getProperties().getStringValue(propNameNew):"");
																}
																if (propNameType.equalsIgnoreCase("LstString")) {

																	StringList lstStr = folder.getProperties()
																			.getStringListValue(propNameNew);
																	if (lstStr != null) {
																		row.createCell(counterVal).setCellValue(
																				getMultiValuePropertyData(lstStr));
																		setAPRecordData(
																				getMultiValuePropertyData(lstStr),
																				propNameNew, aprecord, propNameType);

																	} else {

																		row.createCell(counterVal).setCellValue("");
																		setAPRecordData(null, propNameNew, aprecord,
																				propNameType);
																	}

																	// StringList
																	// lstStr=
																	// folder.getProperties().getStringListValue(propNameNew);
																	// propValueObj=getMultiValuePropertyData(lstStr);
																} else if (propNameType.equalsIgnoreCase("Float")) {

																	if (folder.getProperties()
																			.getFloat64Value(propNameNew) != null) {
																		row.createCell(counterVal)
																				.setCellValue(folder.getProperties()
																						.getFloat64Value(propNameNew));
																		/*setAPRecordData(
																				folder.getProperties()
																						.getFloat64Value(propNameNew),
																				propNameNew, aprecord, propNameType);
*/
																	} else {
																		row.createCell(counterVal).setCellValue("");
																		setAPRecordData(null, propNameNew, aprecord,
																				propNameType);
																	}
																	// propValueObj=
																	// (folder.getProperties().getFloat64Value(propNameNew)!=null?folder.getProperties().getFloat64Value(propNameNew):"");

																} else if (propNameType.equalsIgnoreCase("Integer")) {
																	if (folder.getProperties()
																			.getInteger32Value(propNameNew) != null) {
																		row.createCell(counterVal)
																				.setCellValue(folder.getProperties()
																						.getInteger32Value(
																								propNameNew));
																		setAPRecordData(
																				folder.getProperties()
																						.getInteger32Value(propNameNew),
																				propNameNew, aprecord, propNameType);

																	} else {
																		row.createCell(counterVal).setCellValue("");
																		setAPRecordData(null, propNameNew, aprecord,
																				propNameType);
																	}

																	// propValueObj=
																	// (folder.getProperties().getInteger32Value(propNameNew)!=null?folder.getProperties().getInteger32Value(propNameNew):"");
																} else if (propNameType.equalsIgnoreCase("Boolean")) {

																	if (folder.getProperties()
																			.getBooleanValue(propNameNew) != null) {
																		row.createCell(counterVal)
																				.setCellValue(folder.getProperties()
																						.getBooleanValue(propNameNew));
																		setAPRecordData(
																				folder.getProperties()
																						.getBooleanValue(propNameNew),
																				propNameNew, aprecord, propNameType);

																	} else {
																		row.createCell(counterVal).setCellValue("");
																		setAPRecordData(null, propNameNew, aprecord,
																				propNameType);

																	}
																}
																// propValueObj=(folder.getProperties().getBooleanValue(propNameNew)!=null?folder.getProperties().getBooleanValue(propNameNew):"");
															 else if (propNameType.equalsIgnoreCase("Date")) {
																if (folder.getProperties()
																		.getDateTimeValue(propNameNew) != null) {

																	row.createCell(counterVal)
																	.setCellValue(folder.getProperties()
																			.getDateTimeValue(propNameNew));

																	Date dmsDate = folder.getProperties()
																			.getDateTimeValue(propNameNew);
																	System.out.println("dmsDate:" + dmsDate);
																	java.sql.Date sql =convertUtilTOSQLDate(dmsDate);
																	System.out.println("SQL date is= " + sql);
																	setAPRecordData(sql, propNameNew, aprecord,
																			propNameType);

																	// Date
																	// sqldate=
																	// dateFormat.format(folder.getProperties().getDateTimeValue(propNameNew))
																	// ;
																}
															 else {
																	row.createCell(counterVal).setCellValue("");
																	setAPRecordData(null, propNameNew, aprecord,
																			propNameType);

																}
															 }
																 
															 

																// propValueObj=(folder.getProperties().getDateTimeValue(propNameNew)!=null?folder.getProperties().getDateTimeValue(propNameNew):"");
															}

															else {
																logger.debug("Property does not exist in casetypes");
																
																setAPRecordData(null, propNameNew, aprecord,
																		propNameType);
															}
														} catch (Exception ex) {
															ex.printStackTrace();
															// System.out.println(ex.getMessage());
															logger.info("Exception occured:" + ex.getMessage());

														}
														counterVal++;

													}

													row.createCell(counterVal).setCellValue(comments);
													System.out.println("APRecord:" + aprecord);

													aprecord.setComments(comments);
													db.insertRecord(aprecord);
													rowCnt++;

												}
											}
										}
									}

								} // end workflow
								// }
							} // end maps

						}
					}

				} // end while loop queue query
				/*
				 * if (cnt == 5) break;
				 */
			} // end folder loop
				// --

			try {
				if (rowCnt > 1) {
					Date dateexcel = new Date();
					outputfilename = prop.getProperty("temp_folder_WIP") + casetype + "_WIP_"
							+ dateFormat.format(dateexcel).toString() + "_" + count + ".xlsx";

					FileOutputStream out = new FileOutputStream(new File(outputfilename));
					workbook.write(out);
					out.close();
					PESession.logoff();
					// System.out.println("Excel written successfully..");
					logger.info("Excel written successfully.." + outputfilename);
				} else {

					logger.info("No Data exist for the duration..");
				}
			} catch (FileNotFoundException e) {
				// e.printStackTrace();
				logger.info("Exception Occured:" + e.getMessage());
			} catch (IOException e) {
				// e.printStackTrace();
				logger.info("Exception Occured:" + e.getMessage());
			} // end

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception Occured:" + e.getMessage());
		} finally {
			UserContext.get().popSubject();
		}
		return outputfilename;
	}

	public void setAPRecordData(Object obj, String strCompare, AccountPayable aprecord, String dataType) {
		System.out.println("setAPRecordData" + strCompare);
		switch (strCompare) {
		case "Gbl_Amount":
			aprecord.setGbl_Amount((Float) obj);
			/* Float */ break;
		case "Gbl_BarCodeDC":
			aprecord.setGbl_BarCodeDC((String) obj);
			/* String */ break;
		case "CmAcmCaseState":
			aprecord.setCmAcmCaseState((Integer) obj);
			/* Integer */ break;
		case "Gbl_CaseStatus":
			aprecord.setGbl_CaseStatus((String) obj);
			/* String */ break;
		case "Gbl_CompanyName":
			aprecord.setGbl_CompanyName((String) obj);
			/* String */ break;
		case "Gbl_Currency":
			aprecord.setGbl_Currency((String) obj);
			/* String */ break;

		case "Gbl_CurrentRole":
			aprecord.setGbl_CurrentRole((String) obj);
			/* String */ break;
		case "Gbl_DocumentSource":
			aprecord.setGbl_DocumentSource((String) obj);
			/* String */ break;
		case "Gbl_InvoiceDate":
			aprecord.setGbl_InvoiceDate((java.sql.Date) obj);
			/* Date */ break;
		case "Gbl_Location":
			aprecord.setGbl_Location((String) obj);
			/* String */ break;
		case "Gbl_PaymentRefNo":
			aprecord.setGbl_PaymentRefNo((String) obj);
			/* String */ break;
		case "AP_Received_Date":
			aprecord.setAP_Received_Date((java.sql.Date) obj);
			/* Date */ break;
		case "AP_SAPReferenceforRectification":
			aprecord.setAP_SAPReferenceforRectification((String) obj);
			/* String */ break;
		case "Gbl_SAPReference":
			aprecord.setGbl_SAPReference((String) obj);
			/* String */ break;
		case "CmAcmCaseIdentifier":
			aprecord.setCmAcmCaseIdentifier((String) obj);
			/* String */ break;
		case "DateCreated":
			aprecord.setDateCreated((java.sql.Date) obj);
			/* Date */ break;
		case "AP_DocumentNumber":
			aprecord.setAP_DocumentNumber((String) obj);
			/* String */ break;
		case "Gbl_VendorCode":
			aprecord.setGbl_VendorCode((String) obj);
			/* String */ break;
		case "Gbl_VendorInvoiceNo":
			aprecord.setGbl_VendorInvoiceNo((String) obj);
			/* String */ break;
		case "Gbl_VendorName":
			aprecord.setGbl_VendorName((String) obj);
			/* String */ break;
		case "AP_CENVATAmount":
			aprecord.setAP_CENVATAmount((Float) obj);
			/* Float */ break;
		case "AP_CENVATReferenceNo":
			aprecord.setAP_CENVATReferenceNo((String) obj);
			/* String */ break;
		case "AP_CENVATCreditEligibility":
			aprecord.setAP_CENVATCreditEligibility((String) obj);
			/* String */ break;
		case "ArchiveDocumentStatus":
			aprecord.setArchiveDocumentStatus((String) obj);
			/* String */ break;
		case "Gbl_EmpID":
			aprecord.setGbl_EmpID((String) obj);
			/* String */ break;
		case "Gbl_EmpName":
			aprecord.setGbl_EmpName((String) obj);
			/* String */ break;
		case "Gbl_PONumber":
			aprecord.setGbl_PONumber((String) obj);
			/* String */ break;
		case "DateLastModified":
			aprecord.setDateLastModified((java.sql.Date) obj);
			/* Date */ break;
        default: break;
		}
	}

	public String getMultiValuePropertyData(StringList lstStr) {
		if (lstStr != null && !lstStr.isEmpty()) {

			StringBuffer buffer = new StringBuffer();
			ListIterator<String> iter = lstStr.listIterator();
			while (iter.hasNext()) {
				buffer.append((String) iter.next() + ",");
			}
			// System.out.println("MultiValue:"+buffer.toString());
			return buffer.toString();

		} else
			return "";
	}

	private java.sql.Date convertUtilTOSQLDate(java.util.Date dmsDate) {

		/*java.util.Date parsed = null;
		try {
			parsed = format.parse(dmsDate.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/// System.out.println(format.parse("20110210120534"));
		//if (parsed != null) {
			java.sql.Date sql = new java.sql.Date(dmsDate.getTime());
			System.out.println("SQL date is= " + sql);
			// setAPRecordData(sql,propNameNew,aprecord,propNameType);
			return sql;
		//} else {
			//return null;
		//}
	}
}
