package jsw.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//import com.jsw.sap.extraction.service.SAPDataExtractorService;

@Controller
@RequestMapping(value = "/sap")
public class SAPDataExtractionController {
	//@Autowired
	//SAPDataExtractorService dataExtractor ; 
	
	@RequestMapping(value = "/extractData", method = RequestMethod.GET)
	public ModelAndView extractDmsData() {

		//dataExtractor.extractSAPData();
		
		ModelAndView model = new ModelAndView("home");
		return model;
	}
	
}
