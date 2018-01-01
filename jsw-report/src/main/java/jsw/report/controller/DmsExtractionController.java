package jsw.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jsw.dms.extraction.service.DmsExtractionService;

@Controller
@RequestMapping(value = "/dmsExtraction")
public class DmsExtractionController {
	
	@Autowired
	private DmsExtractionService dmsExtractionService;
	
	@RequestMapping(value = "/extractDmsData", method = RequestMethod.GET)
	
	public ModelAndView extractDmsData(){
	
		dmsExtractionService.extractDmsData();
		ModelAndView model = new ModelAndView("home");
		return model;
	}

	/*public void setDmsExtractionService(DmsExtractionService dmsExtractionService) {
		this.dmsExtractionService = dmsExtractionService;
	}*/
	

}
