package jsw.report.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jsw.report.delegate.VwDelegate;
import jsw.report.viewBean.AssociateList;


@Controller
@RequestMapping(value="/NW")
public class ServiceController {
	@Autowired
	private VwDelegate vwDelegate;

	public VwDelegate getVwDelegate() {
		return vwDelegate;
	}

	public void setVwDelegate(VwDelegate vwDelegate) {
		this.vwDelegate = vwDelegate;
	}

	
	
	
	
	
	
	@RequestMapping(value="/getUserMessage/{msgID}",method=RequestMethod.GET)
	@ResponseBody
	public String getUserMessage(@PathVariable(value ="msgID") String msgID) throws SQLException{
		
		
		
		
		return vwDelegate.getMessage(msgID);
	}
	
	@RequestMapping(value="/getMessage/{msgID}",method=RequestMethod.GET)
	@ResponseBody
	public String getCustomerMessage(@PathVariable(value ="msgID") String msgID) throws SQLException{
		
		
			
		return vwDelegate.getCustomerMessage(msgID);
	}
	
	
	
	 @RequestMapping(value="/setUserMessage/{msg}/{userID}",method=RequestMethod.GET)
		public String setUserMessage(@PathVariable(value="msg")String msg,@PathVariable(value="userID")String userID) throws SQLException
		{
		return vwDelegate.setUserMessage(msg, userID);
		}
	
	 
	 
	 
	 @RequestMapping(value="/setCustomerMessage/{msg}/{userID}",method=RequestMethod.GET)
		public String setCustomerMessage(@PathVariable(value="msg")String msg,@PathVariable(value="userID")String userID) throws SQLException
		{
		return vwDelegate.setCustomerMessage(msg, userID);
	
		}
	 @RequestMapping(value="/getChatWindow",method=RequestMethod.GET)
		public String getChatWindow() throws SQLException
		{
		return vwDelegate.getChatWindow();
		}
	 
	 @RequestMapping(value="/setChatStatus/{id}",method=RequestMethod.GET)
		public String getChatWindow(@PathVariable(value="id")int id) throws SQLException
		{
		return vwDelegate.setChatStatus(id);
		}
	 
	 @RequestMapping(value="/deleteChatStatus/{id}",method=RequestMethod.GET)
		public String deleteChatStatus(@PathVariable(value="id")int id) throws SQLException
		{
		return vwDelegate.deleteChatStatus(id);
		}
	 
	 @RequestMapping(value="/getDeActivated",method=RequestMethod.GET)
		public String getDeActivated() throws SQLException
		{
		return vwDelegate.getDeActiveted();
		}
	 
	 @RequestMapping(value="/getDeActivated/{id}",method=RequestMethod.GET)
		@ResponseBody
		public int getDeActivated(@PathVariable(value="id")int id) throws SQLException
		{
		 
		 
		return vwDelegate.getDeActivetedId(id);
		
		
		}
	 
	 @RequestMapping(value="/getAlert",method=RequestMethod.GET)
		@ResponseBody
		public String getAlerts() throws SQLException
		{
		 
		 
		return vwDelegate.getAlerts();
		
		
		}

	 @RequestMapping(value="/getCaseCreatedCompany/{columnValue}",method=RequestMethod.GET)
		@ResponseBody
		public String getCaseCreatedCompany(@PathVariable(value="columnValue")String columnValue) throws SQLException, JSONException
		{
		 
		 System.out.println("hello");
		return vwDelegate.getCaseCreatedCompany(columnValue).toString();

		}
	 @RequestMapping(value="/getCaseCreatedCompanyCompleted/{columnValue}",method=RequestMethod.GET)
		@ResponseBody
		public String getCaseCreatedCompanyCompleted(@PathVariable(value="columnValue")String columnValue) throws SQLException, JSONException
		{
		 
		
		return vwDelegate.getCaseCreatedCompanyCompleted(columnValue).toString();

		}
	 @RequestMapping(value="/getRoleWiseTimeTaken/{columnValue}",method=RequestMethod.GET)
		@ResponseBody
		public String getRoleWiseTimeTaken(@PathVariable(value="columnValue")String columnValue) throws SQLException, JSONException
		{
		 
		
		return vwDelegate.getRoleWiseTimeTaken(columnValue).toString();

		}
	 @RequestMapping(value="/getTotalCycleTime/{columnValue}",method=RequestMethod.GET)
		@ResponseBody
		public String getTotalCycleTime(@PathVariable(value="columnValue")String columnValue) throws SQLException, JSONException
		{
		 
	
		return vwDelegate.getTotalCycleTime(columnValue).toString();

		}
		
	 
	 

	 @RequestMapping(value="/getCaseCreatedCompanyHR/{columnValue}",method=RequestMethod.GET)
		@ResponseBody
		public String getCaseCreatedCompanyHR(@PathVariable(value="columnValue")String columnValue) throws SQLException, JSONException
		{
		 
		// System.out.println("hello");
		return vwDelegate.getNumberOfCasesCreatedHR(columnValue).toString();

		}
	 
	 
	 
	 
	 @RequestMapping(value="/getCaseCreatedCompanyCompletedHR/{columnValue}",method=RequestMethod.GET)
		@ResponseBody
		public String getCaseCreatedCompanyCompletedHR(@PathVariable(value="columnValue")String columnValue) throws SQLException, JSONException
		{
		 
		
		return vwDelegate.getCaseCreatedCompanyCompletedHR(columnValue).toString();

		}
	 @RequestMapping(value="/getRoleWiseTimeTakenHR/{columnValue}",method=RequestMethod.GET)
		@ResponseBody
		public String getRoleWiseTimeTakenHR(@PathVariable(value="columnValue")String columnValue) throws SQLException, JSONException
		{
		 
		
		return vwDelegate.getRoleWiseTimeTakenHR(columnValue).toString();

		}
	 @RequestMapping(value="/getTotalCycleTimeHR/{columnValue}",method=RequestMethod.GET)
		@ResponseBody
		public String getTotalCycleTimeHR(@PathVariable(value="columnValue")String columnValue) throws SQLException, JSONException
		{
		 
	
		return vwDelegate.getTotalCycleTimeHR(columnValue).toString();

		}
	 
	 //AR
	 
	 


	 @RequestMapping(value="/getCaseCreatedCompanyAR/{columnValue}",method=RequestMethod.GET)
		@ResponseBody
		public String getCaseCreatedCompanyAR(@PathVariable(value="columnValue")String columnValue) throws SQLException, JSONException
		{
		 
		// System.out.println("hello");
		return vwDelegate.getNumberOfCasesCreatedAR(columnValue).toString();

		}
	 
	 
	 
	 
	 @RequestMapping(value="/getCaseCreatedCompanyCompletedAR/{columnValue}",method=RequestMethod.GET)
		@ResponseBody
		public String getCaseCreatedCompanyCompletedAR(@PathVariable(value="columnValue")String columnValue) throws SQLException, JSONException
		{
		 
		
		return vwDelegate.getCaseCreatedCompanyCompletedAR(columnValue).toString();

		}
	 @RequestMapping(value="/getRoleWiseTimeTakenAR/{columnValue}",method=RequestMethod.GET)
		@ResponseBody
		public String getRoleWiseTimeTakenAR(@PathVariable(value="columnValue")String columnValue) throws SQLException, JSONException
		{
		 
		
		return vwDelegate.getRoleWiseTimeTakenAR(columnValue).toString();

		}
	 @RequestMapping(value="/getTotalCycleTimeAR/{columnValue}",method=RequestMethod.GET)
		@ResponseBody
		public String getTotalCycleTimeAR(@PathVariable(value="columnValue")String columnValue) throws SQLException, JSONException
		{
		 
	
		return vwDelegate.getTotalCycleTimeAR(columnValue).toString();
		}
	 
	
	/* @RequestMapping(value="/generateGraph/{x}/{y}",method=RequestMethod.GET)
		@ResponseBody
		public String generateGraph(@PathVariable(value="x")int[] x,@PathVariable(value="y")int y) throws SQLException
		{
		  RConnection connection = null;
		  try {
	              Create a connection to Rserve instance running
	              * on default port 6311
	              
	             connection = new RConnection();
	 
	             String vector = "c(7,12,28,3,41)";
	             connection.eval("meanVal=mean(" + vector + ")");
	             double mean = connection.eval("meanVal").asDouble();
	             
	             System.out.println("The mean of given vector is=" + mean);
	             
	             
	             
	             String vector1 = "c(9,8,7,1)";
	             String vector2 = "c(1,3,5,6)";
	             connection.eval(" png(file = 'line_chart3.png')");
	             connection.eval("plot("+vector2+","+vector1+",type = 'o',col = 'red', xlab = 'X axis', ylab = 'Y axis', main = 'Sefety Report')");
	             
	             connection.eval("dev.off()");
	           
	         } catch (RserveException e) {
	             e.printStackTrace();
	         } catch (REXPMismatchException e) {
	             e.printStackTrace();
	         }finally{
	             connection.close();
	         }
		 
		return vwDelegate.getAlerts();
		
		
		}*/
}
