package com.jsw.dms.extraction.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

public class DmsExtractionServiceImpl implements DmsExtractionService {

	@Autowired
	Properties prop;
	
	//@Autowired
	//DmsDataExtractor dataExtractor;
	
	//private Report_Roster_COM_AutoDateVer4 dmsRoaster;
	
	public void extractDmsData() {
		try {
			Report_Roster_COM_AutoDateVer4 dmsRoaster= new Report_Roster_COM_AutoDateVer4();
			dmsRoaster.generateReport();
			
			//dataExtractor.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
