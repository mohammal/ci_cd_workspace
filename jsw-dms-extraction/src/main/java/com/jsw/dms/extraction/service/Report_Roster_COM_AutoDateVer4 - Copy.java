package com.jsw.dms.extraction.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
import com.filenet.api.exception.EngineRuntimeException;
import com.filenet.api.query.SearchSQL;
import com.filenet.api.query.SearchScope;
import com.filenet.api.util.UserContext;
import com.ibm.casemgmt.api.Case;
import com.ibm.casemgmt.api.Comment;
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

/**
 * 
 * Class to extract WIP case from the DMS System
 * 
 */

public class Report_Roster_COM_AutoDateVer4 {
	
	
	private static Connection conn;
	private static Subject subject;
    private String mySQLString;    
	Folder folder;
	StringTokenizer propStringArray,propStringArray2;
	String propString;
	String outputfilename;
	public static Logger logger = Logger.getLogger(Report_Roster_COM_AutoDateVer4.class);
	String propArray[] = null;
	String prNumberArray[] = null;
	String prLocationArray[] = null;
	StringTokenizer allPropSTK = null;
	String locationSplit[] = null;

	String propName = null;
	String propValue = null;
	String propNameType = null;
	String propNameNew = null;
	private static P8ConnectionCache connCache;	
	boolean secondtime = false;
	//private static Case caseObj = null;
	
	public static DomainReference domainRef=null;
	public static ObjectStoreReference osRef=null;
	public static Domain domain;
	public static  ObjectStore objectStoreObj;

	ArrayList<String> stepNameList = new ArrayList<String>();
	ArrayList<String> approverParticipantList = new ArrayList<String>();
	ArrayList<String> reviewerParticipantList = new ArrayList<String>();
	ArrayList<String> commentatorParticipantList = new ArrayList<String>();
	ArrayList<String> wobNoList = new ArrayList<String>();
	Object[]valuelist=null;
	String stepNameForUserList = "";
	String stepName = "";
	private static Properties prop;	
	String prno= "";
	String lastSr="";	
	int oldM=-1;
	List list=null;
	String countn="";
	long idCounter;
	int interval=0;
	
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	   //get current date time with Date()
	 
	
	public Report_Roster_COM_AutoDateVer4() {
	
	try {
			prop = new Properties();
			InputStream is  = this.getClass().getResourceAsStream("/ReportToolConfig.properties");
			prop.load(is);
			//PropertyConfigurator.configure((String)prop.get("logfilepath"));
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

	}
	

	/**
	 * This method is to create the connection and get domain and objectStore
	 * 
	 * @throws Exception
	 *             , EngineRuntimeException
	 */

	public static Connection getConnection() throws EngineRuntimeException {
		try {
			logger.info("Getting connection");
			conn = Factory.Connection.getConnection(prop.getProperty("connURI"));
			connCache = new SimpleP8ConnectionCache();
			subject = UserContext.createSubject(conn,
					prop.getProperty("username"), prop.getProperty("password"),
					null);
		
			UserContext.get().pushSubject(subject);
			/*CaseMgmtContext origCmctx = CaseMgmtContext
					.set(new CaseMgmtContext(new SimpleVWSessionCache(),
							connCache));*/			
			 domain = Factory.Domain.fetchInstance(conn, null,null);
			 objectStoreObj = Factory.ObjectStore.fetchInstance(
					domain, prop.getProperty("objectStoreName"), null);			 
			 osRef = new ObjectStoreReference(objectStoreObj);	
			//System.out.println("CE subject created" + subject);
			logger.info("Created CE Connection");
		} catch (EngineRuntimeException e) {
			//e.printStackTrace();
			logger.info("Exception :"+ e.getMessage());
		}
		return conn;
	}

	public Domain getDomain() {
		// Retrieves an instance of the Domain class by the specified name
		Domain domain = Factory.Domain.fetchInstance(getConnection(), null,	null);
		logger.info("Created CE Domain"+ domain.get_Name());
		//System.out.println("CE domain created " + domain.get_Name());
		return domain;
	}

	public ObjectStore getObjectStore() {

		ObjectStore objectStoreObj = Factory.ObjectStore.fetchInstance(
				getDomain(), prop.getProperty("objectStoreName"), null);

		//System.out.println("OS name " + objectStoreObj.get_DisplayName());
		logger.info("ObjectStore Name:"+ objectStoreObj.get_DisplayName());
		return objectStoreObj;
	}

	//Build Where clause containing Date Range

	public String buildWhereClause(String ToDate, String dateType){
		
		if(ToDate.equals("NA"))
		{
			DateFormat currdateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
			Date currDate= new Date();
			String strCurrDate="";
			strCurrDate=currdateFormat.format(currDate);	
			strCurrDate=strCurrDate.substring(0, 10)+"T"+strCurrDate.substring(10, 18)+"Z";			
			return " WHERE ["+dateType+"] >="+ prop.getProperty("From_Date_WIP")+"and  ["+dateType+"] <="+strCurrDate;	
		
		}else
			
			return " WHERE ["+dateType+"] >="+ prop.getProperty("From_Date_WIP")+"and  ["+dateType+"] <="+ToDate;	
	
		
	}
	String fileName="";
	int i,loop=0;
	public void generateReport() throws Exception {
		//ObjectStore targetOS = getObjectStore();
       // System.out.println("--------Start of GenerateReport----------");
		logger.info("--------Start of GenerateReport----------");        
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");		
		int itercount=Integer.parseInt(prop.getProperty("beginCount_WIP"));
		int endCnt=Integer.parseInt(prop.getProperty("count_WIP"));		
		
		  // get current date time with Date()					
	     //	Date date = new Date();		
		 //int beginCnt=Integer.parseInt(prop.getProperty("beginCount"));
		
		String dateType=prop.getProperty("dateTypeWIP");
		//Build where clause
		String strWhereClause=buildWhereClause(prop.getProperty("To_Date_WIP"),dateType);
		
		//System.out.println("---- Date Range Criteria:"+strWhereClause);
		logger.info("Date Range:  "+ strWhereClause);   
		ArrayList <String> arrLstCaseType=new ArrayList<String>();		
		for( loop=itercount;loop<=endCnt;loop++)
		{
			idCounter = 1;	
			propString = prop.getProperty("propString0");
			mySQLString = prop.getProperty("queryString"+loop)+strWhereClause;			
			String eventlogprop=prop.getProperty("eventlog"+loop);			
			interval=Integer.parseInt(prop.getProperty("interval_WIP"));			
			ArrayList <String> arrLstTemp=new ArrayList<String>();			
		
			//IF auto=yes, date range is split based on the interval set in the property file
			if (prop.getProperty("auto_WIP").equalsIgnoreCase("yes"))
			{
				//Date currDate= new Date();
				//String strCurrDate="";
				String newtargetDate="";
				Date d1FromDate,d2ToDate;					
				String fromDate=mySQLString.substring(mySQLString.indexOf(">=")+2, (mySQLString.indexOf(">=")+22));					   
				DateFormat currdateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");				
				
				String toDate=mySQLString.substring(mySQLString.indexOf("<=")+2, (mySQLString.indexOf("<=")+22));					
				Date fromDateTemp=null;
				d1FromDate =currdateFormat.parse((fromDate.replace("T", "")).replace("Z", ""));
				d2ToDate = currdateFormat.parse((toDate.replace("T", "")).replace("Z", ""));;
				long diff = d2ToDate.getTime() - d1FromDate.getTime();
				long diffDays = diff / (24 * 60 * 60 * 1000);
				//int i=0;
				//String fileName="";
				//ExecutorService executorService = Executors.newFixedThreadPool(2);
				ArrayList<String> queryList=new ArrayList();
				while (diffDays>interval){
					i++;
					fileName="";
					fromDateTemp=d1FromDate;
					Calendar c = Calendar.getInstance();
					c.setTime(d1FromDate); // Now use today date.
					c.add(Calendar.DATE, interval); // Adding 5 days
					d1FromDate=c.getTime();
					String output = currdateFormat.format(c.getTime());
					String fromDateTempStr=currdateFormat.format(fromDateTemp);
					//System.out.println(output);					
					newtargetDate=output.substring(0, 10)+"T"+output.substring(10, 18)+"Z";
					fromDateTempStr=fromDateTempStr.substring(0, 10)+"T"+fromDateTempStr.substring(10, 18)+"Z";
					mySQLString=mySQLString.substring(0, mySQLString.indexOf("<="));
					mySQLString=mySQLString+"<="+newtargetDate;
					mySQLString=mySQLString.substring(0,mySQLString.indexOf(">="))+">="+fromDateTempStr+" and ["+dateType+"] "+mySQLString.substring( mySQLString.indexOf("<="), mySQLString.indexOf("<=")+22);
					//System.out.println("mySQLString"+ mySQLString);
					//System.out.println( "Int i value:"_+ i);
					queryList.add(mySQLString);
					//arrLstTemp.add(fileName);
					diff = d2ToDate.getTime() - d1FromDate.getTime();
					diffDays = diff / (24 * 60 * 60 * 1000);
					 
				}//end of while
				List<Future<Integer>> resultList = new ArrayList<>();
				
				ExecutorService executor = Executors.newFixedThreadPool(10);
				//Set<CallableReport> callables = new HashSet<CallableReport>();
				List<Callable> callables = new ArrayList<Callable>();
				String businessName=prop.getProperty("businessName");
			
				//ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
				for(int queryCnt=0;queryCnt<queryList.size();queryCnt++){
					
					switch (businessName) {
					case "AP":
					//System.out.println("Query String : "+queryList.get(i));
					CallableWIPReport creport=new CallableWIPReport(new String(""+queryList.get(queryCnt)),eventlogprop,queryCnt,prop);
					callables.add(creport);
					break;
					case "AR":
						//CallableWIPReportAR arreport=new CallableWIPReportAR(new String(""+queryList.get(queryCnt)),eventlogprop,queryCnt,prop);
						//callables.add(arreport);
						break;
					case "RTR":
						//CallableWIPReportRTR rtrreport=new CallableWIPReportRTR(new String(""+queryList.get(queryCnt)),eventlogprop,queryCnt,prop);
						//callables.add(rtrreport);
						break;
					case "TAX":
						//CallableWIPReportTAX taxreport=new CallableWIPReportTAX(new String(""+queryList.get(queryCnt)),eventlogprop,queryCnt,prop);
						//callables.add(taxreport);
						break;
					case "HR":
						//CallableWIPReportHR hrreport=new CallableWIPReportHR(new String(""+queryList.get(queryCnt)),eventlogprop,queryCnt,prop);
						//callables.add(hrreport);
						break;
					case "COM":
						//CallableWIPReportCOM comreport=new CallableWIPReportCOM(new String(""+queryList.get(queryCnt)),eventlogprop,queryCnt,prop);
						//callables.add(comreport);
						break;
					case "CORP":
						//CallableWIPReportCORP corpreport=new CallableWIPReportCORP(new String(""+queryList.get(queryCnt)),eventlogprop,queryCnt,prop);
						//callables.add(corpreport);
						break;
				    default: break;
					//executor.submit(creport);
				   // resultList.add(result);
				}//end of Switch
				}//end of for
				try{
					
			    System.out.println("Before Invoke");
				List<Future<String>> futures = null;//executor.invokeAll((Collection<? extends Callable<String>>) callables);
				for(Future<String> future : futures){
				    System.out.println("future.get = " + future.get());
				}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				executor.shutdown();
				
				/*executorService.execute(new Runnable() {
				    public void run() {
				    	 System.out.println("Asynchronous task loop: "+loop+"i:"+ i);
				    	 fileName=genReport(targetOS,mySQLString,eventlogprop,loop+i);
				       
				    }
				});				
				executorService.shutdown();*/
				//------
				String fromDateTempStr=currdateFormat.format(d1FromDate);				
				//String output = currdateFormat.format(d2ToDate);
				//newtargetDate=output.substring(0, 10)+"T"+output.substring(10, 18)+"Z";	
				
				//System.out.println("Getting Last Interval data"+fromDateTempStr+"::" +toDate);
				//System.out.println("Getting Last mySQLString data"+mySQLString);
				//logger.info("Getting Last mySQLString data:" + mySQLString );
				logger.info("Getting Last mySQLString data for last date interval");
				fromDateTempStr=fromDateTempStr.substring(0, 10)+"T"+fromDateTempStr.substring(10, 18)+"Z";
				mySQLString=mySQLString.substring(0, mySQLString.indexOf("<="));
				mySQLString=mySQLString+"<="+toDate;
				mySQLString=mySQLString.substring(0,mySQLString.indexOf(">="))+">="+fromDateTempStr+" and ["+dateType+"] "+mySQLString.substring( mySQLString.indexOf("<="), mySQLString.indexOf("<=")+22);
				fileName=genReport(mySQLString,eventlogprop,loop+ ++i);
				arrLstTemp.add(fileName);
				//System.out.println("Fetched Last Interval data");
				logger.info("Fetched Last Interval data");
				String outputFile=eventlogprop+"_"+dateFormat.format(new Date()).toString()+".xlsx";
				if(prop.getProperty("merge_WIP").equalsIgnoreCase("yes")){
					logger.info("Merging file");
				    // MergeExcelFiles2.mergeExcelFiles(outputFile, arrLstTemp, prop.getProperty("temp_folder_WIP"));
				}
				System.out.println("Fecting Last Interval data");
				arrLstCaseType.add(prop.getProperty("temp_folder")+outputFile);
				
				
			}
			else //If auto=no, extract report in single query
			{
				System.out.println("Auto NO SQL String::" + mySQLString);
				String fileName="";
				fileName=genReport(mySQLString,eventlogprop,1);				
				//System.out.println(fileName);
				//arrLst.add(fileName);	
				//String outputFile=eventlogprop+"_"+dateFormat.format(new Date()).toString()+".xls";
				//MergeExcelFiles.mergeExcelFiles(outputFile, arrLst, prop.getProperty("temp_folder"));				
				arrLstCaseType.add(fileName);	
			}
			
		}//end for loop
		
		
		//System.out.println("Output file name: "+outputFileWIP);
		if(prop.getProperty("merge_WIP").equalsIgnoreCase("yes")){
			logger.info("Merging file");
			String outputFileWIP=prop.getProperty("rostername")+"WIP_"+dateFormat.format(new Date()).toString()+".xlsx";
		   // MergeExcelFiles2.mergeExcelFiles(outputFileWIP, arrLstCaseType, prop.getProperty("destination_folder_WIP"));
		}
		//System.out.println("Completed All !!!");
		logger.info("-----------Completed ALL-----");
	}
	
	
	public String genReport(String sqlString, String casetype, int count){

		logger.info("Inside genReport method:" + sqlString);
		//System.out.println("-----genReport searchScope...." + sqlString);
		//logger.info("--------Start of genReport----------"+sqlString);
		conn = Factory.Connection.getConnection(prop.getProperty("connURI"));
		connCache = new SimpleP8ConnectionCache();
		subject = UserContext.createSubject(conn,
				prop.getProperty("username"), prop.getProperty("password"),
				null);
	
		UserContext.get().pushSubject(subject);
		/*CaseMgmtContext origCmctx = CaseMgmtContext
				.set(new CaseMgmtContext(new SimpleVWSessionCache(),
						connCache));*/			
		 domain = Factory.Domain.fetchInstance(conn, null,null);
		 objectStoreObj = Factory.ObjectStore.fetchInstance(
				domain, prop.getProperty("objectStoreName"), null);			 
		 osRef = new ObjectStoreReference(objectStoreObj);	
	
		SearchSQL sqlObject = new SearchSQL(sqlString);
		SearchScope searchScope = new SearchScope(objectStoreObj);
		int cnt = 0;
        try{
        	       
		com.filenet.api.collection.IndependentObjectSet independentObjectSet = searchScope
				.fetchObjects(sqlObject, 0, null, true);
		Iterator<Folder> folderIterator = independentObjectSet.iterator();		
		VWSession PESession = new VWSession();
		PESession.setBootstrapCEURI(prop.getProperty("connURI"));
		PESession.logon(prop.getProperty("username"),
		prop.getProperty("password"), prop.getProperty("PEConn"));		
		VWRoster roster = PESession.getRoster(prop.getProperty("rostername"));	
		int queryFlags = VWRoster.QUERY_GET_NO_SYSTEM_FIELDS;
		//int queryFlags = VWRoster.QUERY_NO_OPTIONS;		
		
		String queryFilter = "F_CaseFolder=:A";			
		String casefolderID="";
		
		//write excel report		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(casetype);		
	    propStringArray= new StringTokenizer(propString, "|");
		propStringArray2= new StringTokenizer(propString, "|");
		 // System.out.println("Has more Tokens......."+ propStringArray.countTokens());
	    StringBuilder columnList = new StringBuilder();
	      //  boolean firsttime=true;
	       // if(count==1){
	  
	        AccountPayable aprecord=new AccountPayable();
	    
	    
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
			int rowCnt=1;			
	        while (propStringArray.hasMoreTokens()) {   
	        	
	          	propName = propStringArray.nextToken();
                propNameType =propName.substring(propName.indexOf(":")+1);
	            propNameNew =propName.substring(0, propName.indexOf(":"));	           
	            rowhead.createCell(cntCell).setCellValue(propNameNew);
				cntCell++;
	       
	        }   	       
	        rowhead.createCell(cntCell).setCellValue("Comments");

	        while (folderIterator.hasNext()) {			
			//cnt++;
			System.out.print("*");
			list = new ArrayList();			
			folder = (Folder) folderIterator.next();			
			String comments=""; 
			Case cs = Case.getFetchlessInstance(osRef, folder.get_Id());
			//System.out.println("Folder Name"+folder.get_Id().toString() );				
			//System.out.println("cs:::"+cs);
			//System.out.println("Fetched cases**********:"+cs.getId() );
			comments=getCaseComents(cs);
			
			Object[] substitutionVars ={new VWGuid(folder.get_Id().toString())};// 09/29/2016 00:00:00 AM		
			
			int fetchType = VWFetchType.FETCH_TYPE_STEP_ELEMENT;
			VWRosterQuery query = roster.createQuery(null, null, null,
					queryFlags, queryFilter, substitutionVars, fetchType);
			//int m=0;
		
			while (query.hasNext()) {
							
				//m=m+1;
				int once=0;
				VWStepElement stepElement = (VWStepElement) query.next();
				
				if (stepElement != null) {
					
					
					stepName = (stepElement.getStepName() != null ? stepElement
							.getStepName() : "Review");
					// below condition will exclude cases which is in delay
					// state in workflow

					if (!stepName.equalsIgnoreCase("Review")) {
						
						//casefolderID=stepElement.getParameterValue("*F_CaseFolder").toString();
						
						casefolderID=folder.get_Id().toString();						
						VWProcess process = stepElement.fetchProcess();
						VWWorkflowDefinition workflowDefinition = process.fetchWorkflowDefinition(false);										
						VWMapDefinition[] workflowMaps = workflowDefinition.getMaps();

						for (int i = 0; i < workflowMaps.length; i++) {
							
							int mapID = workflowMaps[i].getMapId();
						
							String mapName = workflowMaps[i].getName();
							if (mapName.equalsIgnoreCase("Workflow")) {
								
								VWWorkflowHistory workflowHistory = process.fetchWorkflowHistory(mapID);
								int l=0;
								while (workflowHistory.hasNext()) {
									
									l=l+1;
						
									VWStepHistory stepHistory = workflowHistory.next();
									
									if (!stepNameList.contains(stepHistory
											.getStepName())) {
										stepNameList.add(stepHistory
												.getStepName());									
									}
									stepNameForUserList = stepHistory.getStepName();

									int k=0;
								
									while (stepHistory.hasNext()) {
										k=k+1;
										VWStepOccurrenceHistory stepOccurenceHistory = stepHistory
												.next();
										int o=0;
										while (stepOccurenceHistory.hasNext()) {
										   o=o+1;
											
											VWStepWorkObjectHistory stepWorkObjectHistory = stepOccurenceHistory.next();
											stepWorkObjectHistory.resetFetch();
											int j=0;
										
											while (stepWorkObjectHistory
													.hasNext()) {
												
												j=j+1;
												
												
												VWParticipantHistory participantHistory = stepWorkObjectHistory
														.next();
												 countn=	String.valueOf(idCounter++);
												 //-----------

												  propStringArray2=	new StringTokenizer(propString, "|");
										       
												//valuelist = new Object[propStringArray2.countTokens()+10];
										       
										        XSSFRow row = sheet.createRow(rowCnt);										        
												row.createCell(0).setCellValue(Integer.parseInt(countn));												
												row.createCell(1).setCellValue(workflowDefinition.getName());
												row.createCell(2).setCellValue(participantHistory.getQueueName());												
												row.createCell(3).setCellValue(stepNameForUserList);												
												row.createCell(4).setCellValue(participantHistory
														.getDateReceived());
												//System.out.print(participantHistory.getCompletionDate());
												if(participantHistory.getCompletionDate()==null){
													row.createCell(5).setCellValue("");	
												}else
													row.createCell(5).setCellValue(participantHistory.getCompletionDate());	
																							
												row.createCell(6).setCellValue((String) participantHistory.getResponse());
												row.createCell(7).setCellValue(	participantHistory.getUserName());
												row.createCell(8).setCellValue(casefolderID);
												//long diff = getTimeDifference((Date) logElement.getFieldValue("F_EnqueueTime"),
														//logElement.getTimeStamp());
												row.createCell(9).setCellValue("0");

												int counterVal=10;
											   
											        /*long diff = getTimeDifference(participantHistory
															.getDateReceived(),
															participantHistory
															.getCompletionDate());*/

												//counterVal=counterVal+10;
											       
											       // Object propValueObj=null;
											        while (propStringArray2.hasMoreTokens()) {
											        	
											        	propName = propStringArray2.nextToken();

											           // propName = prop.getProperty(propArray[0]); 
											            propNameType =propName.substring(propName.indexOf(":")+1);
											            propNameNew =propName.substring(0, propName.indexOf(":"));
											            try{         
											            	
											            if(folder.getProperties().isPropertyPresent(propNameNew)){
												            	
												            if(propNameType.equalsIgnoreCase("String")) {  
												            	
												            	if (folder.getProperties().getStringValue(propNameNew) != null)
																	row.createCell(counterVal).setCellValue(
																			folder.getProperties().getStringValue(propNameNew));
																else
																	row.createCell(counterVal).setCellValue("");
												            	
												            	//propValueObj=	(folder.getProperties().getStringValue(propNameNew)!=null?folder.getProperties().getStringValue(propNameNew):"");
												            }  if(propNameType.equalsIgnoreCase("LstString")) { 
												            	
												            	StringList lstStr = folder.getProperties().getStringListValue(propNameNew);
																if (lstStr != null) {
																	row.createCell(counterVal)
																			.setCellValue(getMultiValuePropertyData(lstStr));
																} else
																	row.createCell(counterVal).setCellValue("");
												            	
												            	
												            	//StringList lstStr=	folder.getProperties().getStringListValue(propNameNew);
												            	//propValueObj=getMultiValuePropertyData(lstStr);
												             }             
												            else if(propNameType.equalsIgnoreCase("Float")){            	
												            	if (folder.getProperties().getFloat64Value(propNameNew) != null)
																	row.createCell(counterVal).setCellValue(
																			folder.getProperties().getFloat64Value(propNameNew));
																else
																	row.createCell(counterVal).setCellValue("");
												            	//propValueObj=	(folder.getProperties().getFloat64Value(propNameNew)!=null?folder.getProperties().getFloat64Value(propNameNew):"");
												            	 
												            } else if(propNameType.equalsIgnoreCase("Integer")){       	
												            	if (folder.getProperties().getInteger32Value(propNameNew) != null)
																	row.createCell(counterVal).setCellValue(
																			folder.getProperties().getInteger32Value(propNameNew));
																else
																	row.createCell(counterVal).setCellValue("");

												           	 
												            	//propValueObj=	(folder.getProperties().getInteger32Value(propNameNew)!=null?folder.getProperties().getInteger32Value(propNameNew):"");
												           }else if(propNameType.equalsIgnoreCase("Boolean")){    
												        	   
												        		if (folder.getProperties().getBooleanValue(propNameNew) != null)
																	row.createCell(counterVal).setCellValue(
																			folder.getProperties().getBooleanValue(propNameNew));
																else
																	row.createCell(counterVal).setCellValue("");
												        	  // propValueObj=(folder.getProperties().getBooleanValue(propNameNew)!=null?folder.getProperties().getBooleanValue(propNameNew):"");
												             } else if(propNameType.equalsIgnoreCase("Date")){            	
												            	 if (folder.getProperties().getDateTimeValue(propNameNew) != null)
																		row.createCell(counterVal).setCellValue(
																				folder.getProperties().getDateTimeValue(propNameNew));
																	else
																		row.createCell(counterVal).setCellValue("");
																	
												            	// propValueObj=(folder.getProperties().getDateTimeValue(propNameNew)!=null?folder.getProperties().getDateTimeValue(propNameNew):"");
												             } 
												            
											            
											            	  }else
													          {
													        	  //propValueObj="";
													          }
											            }catch(Exception ex){
											            	//ex.printStackTrace();
											            	//System.out.println(ex.getMessage());
											            	logger.info("Exception occured:"+ ex.getMessage());
											            	
											            }
											           
												         counterVal++;										       
											        
											        }
											      
											        row.createCell(counterVal).setCellValue(comments);											        
											        rowCnt++;
												
											}
										}
									}
								}
								
							} // end workflow
						//}
					} //end maps
				
				}
				}
			}  //end while loop queue query
			/*if (cnt == 5)
				break;*/
		} //end folder loop
			//--
			
			try {		
				 if(rowCnt > 1){
				  Date dateexcel = new Date();					
				  outputfilename = prop.getProperty("temp_folder_WIP")+casetype+"_WIP_"
							+ dateFormat.format(dateexcel).toString() +"_"+count+ ".xlsx";
				
				  FileOutputStream out = 
							new FileOutputStream(new File(outputfilename));
				  workbook.write(out);
				  out.close();					
				  PESession.logoff();
				  //System.out.println("Excel written successfully..");
				  logger.info("Excel written successfully.."+ outputfilename );
				 }else {
					 
					 logger.info("No Data exist for the duration.." );
				 }
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
				 logger.info("Exception Occured:"+ e.getMessage());
			} catch (IOException e) {
				//e.printStackTrace();
				logger.info("Exception Occured:"+ e.getMessage());
			}//end
			finally{
				
				PESession=null;
				
			}
			
        }catch(Exception e){
        	e.printStackTrace();
        	logger.error("Exception Occured:"+ e.getMessage());
        }finally{
        	UserContext.get().popSubject();
        }
        return outputfilename;
	}
	
	public long getTimeDifference(Date fromDate, Date toDate) {

		// HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		long diffMinutes = 0;
		try {
			// d1 = format.parse(fromDate);
			// d2 = format.parse(toDate);

			// in milliseconds
			long diff = toDate.getTime() - fromDate.getTime();
			// long diffSeconds = diff / 1000 % 60;
			diffMinutes = diff / (60 * 1000) % 60;
			// long diffHours = diff / (60 * 60 * 1000) % 24;
			// long diffDays = diff / (24 * 60 * 60 * 1000);
			// System.out.print( " diffMinutes: "+diffMinutes);

			/*
			 * System.out.print(diffDays + " days, ");
			 * System.out.print(diffHours + " hours, ");
			 * System.out.print(diffMinutes + " minutes, ");
			 * System.out.print(diffSeconds + " seconds.");
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diffMinutes;

	}
	public static String convertGuidToDbFormat(String guid) { 
		String s1 = guid.substring(1, 3); 
		String s2 = guid.substring(3, 5); 
		String s3 = guid.substring(5, 7); 
		String s4 = guid.substring(7, 9); 
		String s5 = guid.substring(10, 12); 
		String s6 = guid.substring(12, 14); 
		String s7 = guid.substring(15, 17); 
		String s8 = guid.substring(17, 19); 
		String s9 = guid.substring(20, 24); 
		String s10 = guid.substring(25,37); 
		return s4+s3+s2+s1+s6+s5+s8+s7+s9+s10; 

		} 
	
	public static Object returnValue(String key,Map<String,Object[]> tempMap) { 
		
		Object [] objArr = tempMap.get(key);
		Object obj=objArr[0];
			if(obj instanceof Date) 
				return((Date)obj);
			else if(obj instanceof Integer)
				return((Integer)obj);
			else if(obj instanceof Boolean)
				return((Boolean)obj);
			else if(obj instanceof String)
				return((String)obj);
			else if(obj instanceof Double)
				return((Double)obj);
			return null;
		}
		
	public String getMultiValuePropertyData(StringList lstStr){
		   if(lstStr!=null && !lstStr.isEmpty()){
			   
			   StringBuffer buffer=new StringBuffer();
			   ListIterator<String>iter=lstStr.listIterator();
			   while(iter.hasNext()){
				   buffer.append((String) iter.next()+",");
		         }
			   //System.out.println("MultiValue:"+buffer.toString());
		       return buffer.toString();
		   
		   }else  
			   return "";
	   }
	
	public String getCaseComents(Case cas) {
			
			    StringBuffer strComments=new StringBuffer();
				List<Comment> commetList = cas.fetchCaseComments();
				Iterator<Comment> commIterator=null;
				Comment coment=null;
				if(commetList.size()>0){
					commIterator = commetList.iterator();
					while(commIterator.hasNext()){
						
						coment = (Comment)commIterator.next();						
						strComments.append(coment.getText()+":"+ coment.getCreator()+";");
						if(strComments.length()>=100 )
							break;
					}
				}
				
				return strComments.toString();
			} 


	public static void main(String args[]) throws Exception {
		try {

			Report_Roster_COM_AutoDateVer4 obj = new Report_Roster_COM_AutoDateVer4();
			obj.generateReport();
			System.out.println("hello");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		} finally {
			
			
			UserContext.get().popSubject();
		}
	}
}
