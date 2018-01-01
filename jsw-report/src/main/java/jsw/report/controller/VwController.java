package jsw.report.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.ModelAndView;

import excellDatabase.ExcelDatabase;
import jsw.report.delegate.VwDelegate;
import jsw.report.viewBean.*;

@Controller
@RequestMapping(value = "/mainController")
public class VwController {
	private static String UPLOADED_FOLDER = "D://";

	@Autowired
	private VwDelegate vwDelegate;

	public VwDelegate getVwDelegate() {
		return vwDelegate;
	}

	public void setVwDelegate(VwDelegate vwDelegate) {
		this.vwDelegate = vwDelegate;
	}

	@RequestMapping(value = "/deleteTest/{id}", method = RequestMethod.GET)
	public ModelAndView deleteTest(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
			LoginBean loginBean, RegistrationBean regisBean, Tests testBean, Tests testData) throws SQLException {
		ModelAndView model = new ModelAndView("TestingInfo");

		boolean isValidUser = vwDelegate.deleteTest(id);
		if (isValidUser) {
			System.out.println("delete user success");

		} else {
			request.setAttribute("message", "Invalid credentials!!");
		}
		List<Tests> list = null;
		try {

			list = vwDelegate.getTest();
			System.out.println(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("test", list);
		request.setAttribute("testBean", testBean);
		request.setAttribute("testData", testData);
		return model;
	}

	@RequestMapping(value = "/editTest/{id}", method = RequestMethod.GET)
	public ModelAndView editTest(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
			LoginBean loginBean, RegistrationBean regisBean, Tests testBean) throws SQLException {
		ModelAndView model = new ModelAndView("TestingInfo");

		Tests test = vwDelegate.editTest(id);

		request.setAttribute("testData", test);

		List<Tests> list = null;
		try {

			list = vwDelegate.getTest();
			System.out.println(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("yes", "yes");
		request.setAttribute("test", list);
		request.setAttribute("testBean", testBean);
		return model;
	}

	@RequestMapping(value = "/updateTest", method = RequestMethod.POST)
	public ModelAndView updateTest(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("TestData") Tests testData, Tests testBean) throws SQLException {
		ModelAndView model = new ModelAndView("TestingInfo");
		request.setAttribute("message", "edit UserType");
		System.out.println(testData);
		try {

			boolean insert = vwDelegate.updateTest(testData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Tests> list = null;
		try {

			list = vwDelegate.getTest();
			System.out.println(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("test", list);
		request.setAttribute("testBean", testBean);
		request.setAttribute("testData", testData);
		return model;

	}

	@RequestMapping(value = "/addTest", method = RequestMethod.POST)
	public ModelAndView addTest(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("TestBean") Tests test, Tests testBean, Tests testData) throws SQLException {
		ModelAndView model = new ModelAndView("TestingInfo");
		request.setAttribute("message", "add UserType");

		try {

			boolean insert = vwDelegate.addTest(test);

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Tests> list = null;
		try {

			list = vwDelegate.getTest();
			System.out.println(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("test", list);

		request.setAttribute("testData", testData);

		request.setAttribute("testBean", testBean);
		return model;

	}

	// ****************Add User********************

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("regisBean") RegistrationBean user) throws SQLException {
		ModelAndView model = new ModelAndView("adminRights");
		request.setAttribute("message", "add UserType");
		List<RegistrationBean> list = null;
		try {

			boolean insert = vwDelegate.addUser(user);

		} catch (Exception e) {
			e.printStackTrace();
		}

		model = adminRights(request, response, user);

		try {

			list = vwDelegate.getAllUsers();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("userList", list);

		return model;

	}
	// *****************End User***********

	@RequestMapping(value = "/monitor", method = RequestMethod.GET)
	public ModelAndView getMonitor(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView model = new ModelAndView("monitoring");
		/*
		 * Properties prop = new Properties();
		 * 
		 * InputStream input =
		 * this.getClass().getResourceAsStream("properties.properties");
		 * prop.load(input); System.out.println(prop.getProperty("OvenArray"));
		 */
		return model;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView getDashboard(HttpServletRequest request, HttpServletResponse response) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("dashboardMenu");
request.setAttribute("alertNumber", new JSONArray(vwDelegate.getAlerts()));
		return model;
	}

	@RequestMapping(value = "/userMachineAccess", method = RequestMethod.GET)
	public ModelAndView getUserMachine(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("userMachineAccess");
		List<String> list3 = null;
		try {

			list3 = vwDelegate.getUserName();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("userList", list3);
		return model;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminRights(HttpServletRequest request, HttpServletResponse response,
			RegistrationBean regisBean) {
	
		ModelAndView model = new ModelAndView("adminRights");
		List list = null;
		try {

			list = vwDelegate.getAllUsers();
			System.out.println(regisBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("userList", list);
		request.setAttribute("regisBean", new RegistrationBean());
		List<String> list3 = null;
		try {

			list3 = vwDelegate.getOrderTrans();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("orderList", list3);
		
		System.out.println(list3);
		return model;

	}

	@RequestMapping(value = "/screen", method = RequestMethod.GET)
	public ModelAndView screenRights(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean, Role screenRole) throws Exception {
		ModelAndView model = new ModelAndView("userMenuRights");

		
		List<String> list3 = null;
		try {

			list3 = vwDelegate.getUserName();

		} catch (Exception e) {
			e.printStackTrace();
		}

		List<Role> list4 = null;
		try {

			list4 = vwDelegate.getRole();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("userRole", list4);

		request.setAttribute("userList", list3);
		model.addObject("LoginBean", loginBean);
		model.addObject("regisBean", regisBean);
		model.addObject("screenRole", screenRole);

		return model;

	}

	@RequestMapping(value = "/userScreen", method = RequestMethod.GET)
	public ModelAndView userScreenRights(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean) throws Exception {
		ModelAndView model = new ModelAndView("userScreenPermission");

		JSONArray json = vwDelegate.getMenuTree();
		JSONObject main = new JSONObject();
		request.setAttribute("jsonArray", main.put("main", json).toString());

		List list = null;
		try {

			list = vwDelegate.getAllUsers();
			System.out.println(regisBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("userSList", list);

		List<String> list3 = null;
		try {

			list3 = vwDelegate.getUserName();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("message", "");

		request.setAttribute("userList", list3);

		List<Role> list4 = null;
		try {

			list4 = vwDelegate.getRole();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("userRole", list4);

		model.addObject("LoginBean", loginBean);
		model.addObject("regisBean", regisBean);

		return model;

	}

	@RequestMapping(value = "/screenAssign", method = RequestMethod.GET)
	public ModelAndView screenAssignGet(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("RegisBean") RegistrationBean regisBean,Role screenRole,
			Role role) throws Exception {

		
		ModelAndView model = new ModelAndView("newRole");
		JSONArray json = vwDelegate.getMenuTree();
		JSONObject main = new JSONObject();
		request.setAttribute("jsonArray", main.put("main", json).toString());
System.out.println("jsonArray  "+json);
		request.setAttribute("screenRole", screenRole);
		return model;

	}
	
	
	@RequestMapping(value = "/screenAssign", method = RequestMethod.POST)
	public ModelAndView screenAssign(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("RegisBean") RegistrationBean regisBean, @ModelAttribute("screenRole") Role screenRole,
			Role role) throws Exception {

		System.out.println(regisBean);

		try {

			boolean insert = vwDelegate.addRole(screenRole);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView model = new ModelAndView("userMenuRights");

	
		List<String> list3 = null;
		try {

			list3 = vwDelegate.getUserName();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("userList", list3);
		List<Role> list4 = null;
		try {

			list4 = vwDelegate.getRole();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("userRole", list4);

		model.addObject("screenRole", role);
		return model;

	}

	@RequestMapping(value = "/screenUserAssign", method = RequestMethod.POST)
	public ModelAndView screenUserAssign(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("RegisBean") RegistrationBean regisBean) throws Exception {

		ModelAndView model = new ModelAndView("userScreenPermission");
		System.out.println(regisBean);

		List<String> list3 = null;
		try {

			list3 = vwDelegate.getUserName();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("userList", list3);

		List<Role> list4 = null;
		try {

			list4 = vwDelegate.getRole();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("userRole", list4);

		try {

			boolean insert = vwDelegate.updateScreenID(regisBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("message", "Screen role Successfully assigned to user!!");
		model.addObject("LoginBean", loginBean);
		model.addObject("regisBean", regisBean);

		return model;

	}

	@RequestMapping(value = "/reports/{id}", method = RequestMethod.GET)
	public ModelAndView getReports(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) {
		ModelAndView model = new ModelAndView("reports");
		List<Invoice> list3 = null;
		try {

			list3 = vwDelegate.getInvoice();

		} catch (Exception e) {
			e.printStackTrace();
		}
		String selectedGroupId=null;
		for (int ReportCount=0; ReportCount<list3.size(); ReportCount++){
			 if(list3.get(ReportCount).getId()==id)
			selectedGroupId=list3.get(ReportCount).getAmount();
			
		}
		request.setAttribute("reportGroupList", list3);
		request.setAttribute("currentReportGroup", selectedGroupId);
		List<Delievery> list = null;
		List<Delievery> list2 = new ArrayList<Delievery>();
		try {

			list = vwDelegate.getDelievery();

		} catch (Exception e) {
			e.printStackTrace();
		}

		int list2Count=0;
		for (int ReportCount=0; ReportCount<list.size(); ReportCount++){
			 
			String imagePath=list.get(ReportCount).getIsValidate();
		
			if(list.get(ReportCount).getRefId().equals(selectedGroupId)){
			
			  try {
	        	   File file =  new File("D:/eclipse/mobile/Faurecia/src/main/webapp/"+imagePath);
	                FileInputStream fileInputStreamReader = new FileInputStream(file);
	                byte[] bytes = new byte[(int)file.length()];
	                fileInputStreamReader.read(bytes);
	              String  encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
	              Delievery tempReport=list.get(ReportCount);
	              tempReport.setIsValidate(encodedfile);
	              list2.add(tempReport);
	          
	            } catch (FileNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
			
			
			}
			
			}
		
		
		
		Collections.reverse(list2);
		
		
		request.setAttribute("reportList", list2);
		return model;
	}

	@RequestMapping(value = "/getAudits", method = RequestMethod.GET)
	public ModelAndView getAudits(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("Audit");

		return model;
	}

	// ***************Dealer**********

	@RequestMapping(value = "/listDealer", method = RequestMethod.GET)
	public ModelAndView getDealer(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean) {
		ModelAndView model = new ModelAndView("listDealer");
		List list = null;
		try {

			list = vwDelegate.getDealer();

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("dealerList", list);

		return model;
	}

	@RequestMapping(value = "/listDealer", method = RequestMethod.POST)
	public ModelAndView listDealer(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("DealerBean") Dealer dealerBean) {
		ModelAndView model = new ModelAndView("listDealer");
		List list = null;
		try {
			boolean insert = vwDelegate.addDealer(dealerBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			list = vwDelegate.getDealer();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("dealerList", list);

		return model;
	}

	@RequestMapping(value = "/newDealer", method = RequestMethod.GET)
	public ModelAndView newDealer(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean, Dealer dealerBean) {
		ModelAndView model = new ModelAndView("newDealer");

		request.setAttribute("DealerBean", dealerBean);
		return model;
	}

	// **********End*********

	// **************Product********

	@RequestMapping(value = "/listProduct", method = RequestMethod.GET)
	public ModelAndView listProduct(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("listOrder");
		List list = null;
		try {

			list = vwDelegate.getOrder();

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("productList", list);

		return model;
	}

	@RequestMapping(value = "/newOrder", method = RequestMethod.GET)
	public ModelAndView newOrder(HttpServletRequest request, HttpServletResponse response, Orders orderBean)

	{
		ModelAndView model = new ModelAndView("newOrder");
		List<Product> lst = new ArrayList<Product>();
		try {
			lst = vwDelegate.getProduct();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("OrderBean", orderBean);
		request.setAttribute("ProductList", lst);

		return model;
	}

	@RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
	public ModelAndView AddOrder(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("OrderBean") Orders orderBean) {
		System.out.println();

		try {

			boolean insert = vwDelegate.addOrder(orderBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("OrderBean", orderBean);
		ModelAndView model = listProduct(request, response);

		return model;
	}

	@RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.GET)
	public ModelAndView deleteOrderList(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
			Orders order) throws SQLException {
		ModelAndView model = new ModelAndView("listOrder");

		boolean isValidUser = vwDelegate.deleteOrderList(id);

		List list = null;
		try {

			list = vwDelegate.getOrder();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("orderList", list);

		return model;
	}

	@RequestMapping(value = "/editOrders/{id}", method = RequestMethod.GET)
	public ModelAndView editOrder(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
			@ModelAttribute("OrderBean") Orders dealer) throws SQLException

	{

		ModelAndView model = new ModelAndView("EditOrder");
		List orderData = vwDelegate.getOrderById(id);

		List stageList = vwDelegate.getProduct();

		request.setAttribute("ProductList", stageList);
		request.setAttribute("orderList", orderData);
		request.setAttribute("id", id);

		return model;
	}

	@RequestMapping(value = "/updateOrder/{id}", method = RequestMethod.GET)
	public ModelAndView updateOrder(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
			@ModelAttribute("OrderBean") Orders stageData) throws SQLException {

		ModelAndView model = new ModelAndView("listOrder");
		request.setAttribute("message", "edit UserType");
		stageData.setId(id);

		try {

			boolean insert = vwDelegate.updateOrder(stageData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Orders> list = null;
		try {

			list = vwDelegate.getOrder();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("orderList", list);

		request.setAttribute("OrderData", stageData);
		return model;

	}

	// **************End********

	// ***************Service*******

	@RequestMapping(value = "/listservice", method = RequestMethod.GET)
	public ModelAndView listService(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("ServiceList");
		List list = null;
		try {

			list = vwDelegate.getService();

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("serviceList", list);

		return model;
	}

	@RequestMapping(value = "/newService", method = RequestMethod.GET)
	public ModelAndView newService(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("ServiceBean") Service orderBean)

	{
		ModelAndView model = new ModelAndView("NewService");
		List<Service> lst = new ArrayList<Service>();
		try {
			lst = vwDelegate.getService();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("OrderBean", orderBean);
		request.setAttribute("ServiceList", lst);

		return model;
	}

	@RequestMapping(value = "/submitService", method = RequestMethod.POST)
	public ModelAndView AddService(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("ServiceBean") Service orderBean) {
		System.out.println();

		try {

			boolean insert = vwDelegate.addService(orderBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("serviceBean", orderBean);
		ModelAndView model = listProduct(request, response);

		return model;
	}

	// ***********Schem********

	@RequestMapping(value = "/listDiscount", method = RequestMethod.GET)
	public ModelAndView listDiscountOnMaterials(HttpServletRequest request, HttpServletResponse response,
			Scheme discount) {
		ModelAndView model = new ModelAndView("schem");

		List list = null;

		try {

			list = vwDelegate.getDiscountOnMaterials();

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("DiscountOnMaterialsList", list);

		return model;
	}

	@RequestMapping(value = "/listDiscountOnMaterials", method = RequestMethod.POST)
	public ModelAndView getDiscountOnMaterials(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("DiscountOnMaterialsBean") Scheme discountOnMaterialsBean, Product product) {
		ModelAndView model = new ModelAndView("schem");
		List list = null;

		try {

			boolean insert = vwDelegate.addDiscountOnMaterials(discountOnMaterialsBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			list = vwDelegate.getDiscountOnMaterials();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("DiscountOnMaterialsList", list);

		return model;
	}

	@RequestMapping(value = "/newDiscountOnMaterials", method = RequestMethod.GET)
	public ModelAndView newDiscountOnMaterials(HttpServletRequest request, HttpServletResponse response,
			Scheme discountOnMaterialsBean, Product product) {
		ModelAndView model = new ModelAndView("newSchem");
		List productList = null;
		try {
			productList = vwDelegate.getProduct();
		} catch (Exception e) {
			e.printStackTrace();

		}
		request.setAttribute("ProductList", productList);
		request.setAttribute("DiscountOnMaterialsBean", discountOnMaterialsBean);
		return model;
	}

	// **********End*********

	// *************Contract***********

	@RequestMapping(value = "/listContract", method = RequestMethod.GET)
	public ModelAndView listContract(HttpServletRequest request, HttpServletResponse response, Contract discount) {
		ModelAndView model = new ModelAndView("ContractAgrement");

		List list = null;

		try {

			list = vwDelegate.getContract();

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("ContractList", list);

		return model;
	}

	@RequestMapping(value = "/newContract", method = RequestMethod.GET)
	public ModelAndView newContract(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("contract") Contract contract) {
		ModelAndView model = new ModelAndView("NewContract");

		return model;
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public ModelAndView submit(HttpServletRequest request, HttpServletResponse response, Contract discount) {
		ModelAndView model = listContract(request, response, discount);

		return model;
	}

	// ************End**********

	// ************Investment *********

	@RequestMapping(value = "/listInvestment", method = RequestMethod.GET)
	public ModelAndView listInvestment(HttpServletRequest request, HttpServletResponse response, Investment discount) {
		ModelAndView model = new ModelAndView("InvestmentList");

		List list = null;

		try {

			list = vwDelegate.getInvestment();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("investmentList", list);

		return model;
	}

	@RequestMapping(value = "/newInvestment", method = RequestMethod.GET)
	public ModelAndView newInvestment(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("investment") Investment contract) {
		ModelAndView model = new ModelAndView("NewInvestment");

		return model;
	}

	@RequestMapping(value = "/submitInvestment", method = RequestMethod.POST)
	public ModelAndView submitInvestment(HttpServletRequest request, HttpServletResponse response,
			Investment discount) {
		ModelAndView model = listInvestment(request, response, discount);

		return model;
	}

	// ***********End **********

	// ***********File Uploading**********

	@RequestMapping("/selectFile")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("FileUploading");
		return model;
	}

	// @RequestMapping(value = "/upload", method = RequestMethod.POST)
	@RequestMapping("/upload") // //new annotation since 4.3
    public ModelAndView singleFileUpload(HttpServletRequest request, HttpServletResponse response,@RequestParam("files") MultipartFile file,@RequestParam("documentType")String type) throws SQLException {
    	System.out.println("...."+type);
	 ModelAndView model = new ModelAndView("SuccessFullyUploaded");
	System.out.println("...."+type);
	FileList list=new FileList();
	list.setDocumentType(type);
 
        if (file.isEmpty()) {
          
            return model;
        }
         boolean insert;
        
        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
       	
            Files.write(path, bytes);
           
            insert = vwDelegate.addFileName(file.getOriginalFilename(),list);
           
          

        } catch (IOException e) {
            e.printStackTrace();
        }
       
        
        return model;
    }
	// *********** end File Uploading**********

	// **************File Importing****************

		@RequestMapping(value = "/fileList", method = RequestMethod.GET)
		public ModelAndView getFileList(HttpServletRequest request, HttpServletResponse response, FileList flist) {
			ModelAndView model = new ModelAndView("FileList");
			List<FileList> list = null;
			try {

				list = vwDelegate.getFileList();

			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("fileData", list);

			return model;

		}

		@RequestMapping(value = "/importFile/{id}", method = RequestMethod.GET)
		public ModelAndView insert(HttpServletRequest request, HttpServletResponse response, @PathVariable int id)
				throws IOException, JSONException, SQLException {
		
			ModelAndView model=null;
			String s = vwDelegate.getFileById(id);
		
			String importRasult =vwDelegate.saveFileToDatabase(s);
			
			if(importRasult=="success"){
			vwDelegate.updatefileStatus(id);
			
			model = new ModelAndView("Success");
			}
			else{
				request.setAttribute("message", importRasult);
				 model = new ModelAndView("FileList");
				 List<FileList> list = null;
					try {

						list = vwDelegate.getFileList();

					} catch (Exception e) {
						e.printStackTrace();
					}

					request.setAttribute("fileData", list);
				
			}
			
			
			return model;
		}



		@RequestMapping(value = "/deleteFile/{id}", method = RequestMethod.GET)
		public ModelAndView deleteFile(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
				FileList stage) throws SQLException {

			ModelAndView model = new ModelAndView("FileList");

			boolean isValidUser = vwDelegate.deleteFileList(id);

			List list = null;
			try {

				list = vwDelegate.getFileList();

			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("fileData", list);

			return model;
		}

	// ***********Document type**********
	@RequestMapping(value = "/documentType", method = RequestMethod.GET)
	public ModelAndView getDocument(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("DocumentType") DocumentType contract) throws SQLException {
		List list = null;

		ModelAndView model = new ModelAndView("DocumentTypeList");
		list = vwDelegate.getDocumentList();

		request.setAttribute("documentTypelist", list);
		return model;
	}

	@RequestMapping(value = "/newDocumentType", method = RequestMethod.GET)
	public ModelAndView newDocumentType(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("documentType") DocumentType contract) throws SQLException {
		ModelAndView model = new ModelAndView("NewDocumentType");

		return model;
	}

	@RequestMapping(value = "/submitDocumentType", method = RequestMethod.POST)
	public ModelAndView submitDocument(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("documentType") DocumentType contract) throws SQLException {
		if (contract.getIsActive() != "y") {
			contract.setIsActive("n");

		}
		vwDelegate.addDocumentType(contract);
		ModelAndView model = getDocument(request, response, contract);

		return model;
	}

	// *********End***********

	// ***********Investment type**********

	@RequestMapping(value = "/investmentType", method = RequestMethod.GET)
	public ModelAndView getInvestment(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("InvestmentType") InvestmentType contract) throws SQLException {
		List list = null;

		ModelAndView model = new ModelAndView("InvestmentTypeList");
		list = vwDelegate.getInvestmentList();

		request.setAttribute("investmentTypelist", list);
		return model;
	}

	@RequestMapping(value = "/newInvestmentType", method = RequestMethod.GET)
	public ModelAndView newInvestmentType(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("investmentType") InvestmentType contract) throws SQLException {
		ModelAndView model = new ModelAndView("NewInvestmentType");

		return model;
	}

	@RequestMapping(value = "/submitInvestmentType", method = RequestMethod.POST)
	public ModelAndView submitInvestment(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("investmentType") InvestmentType contract) throws SQLException {
		if (contract.getIsActive() != "y") {
			contract.setIsActive("n");

		}
		vwDelegate.addInvestmentType(contract);
		ModelAndView model = getInvestment(request, response, contract);

		return model;
	}

	// *********End***********

	// ***********Binding type**********

	@RequestMapping(value = "/bindingType", method = RequestMethod.GET)
	public ModelAndView getBinding(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bindingType") Binding contract) throws SQLException {
		List list = null;

		ModelAndView model = new ModelAndView("BindingTypeList");
		list = vwDelegate.getBindingList();

		request.setAttribute("bindingTypelist", list);
		return model;
	}

	@RequestMapping(value = "/newBindingType", method = RequestMethod.GET)
	public ModelAndView newBindingType(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bindingType") Binding contract) throws SQLException {
		ModelAndView model = new ModelAndView("NewBindingType");

		return model;
	}

	@RequestMapping(value = "/submitBindingType", method = RequestMethod.POST)
	public ModelAndView submitBinding(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bindingType") Binding contract) throws SQLException {

		if (contract.getIsActive() != "y") {
			contract.setIsActive("n");

		}

		vwDelegate.addBindingType(contract);
		ModelAndView model = getBinding(request, response, contract);

		return model;
	}

	// *********End***********

	// *****************Associate***************
	@RequestMapping(value = "/associate", method = RequestMethod.GET)
	public ModelAndView getAssociteList(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("bindingType") AssociateList contract) throws SQLException {
		List list = null;

		ModelAndView model = new ModelAndView("AssociateList");
		list = vwDelegate.getAssociateList();

		request.setAttribute("associatelist", list);
		return model;
	}

	@RequestMapping(value = "/newAssociate", method = RequestMethod.GET)
	public ModelAndView newAssociate(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("associate") AssociateList contract) throws SQLException {
		ModelAndView model = new ModelAndView("NewAssociate");

		List lst = null;
		List l2 = vwDelegate.getFileList();

		lst = vwDelegate.getOrderRef();
		System.out.println("kkk" + l2.get(1));

		// List l3=vwDelegate.getOrderAmount(id);

		// System.out.println("ppp"+l2);

		request.setAttribute("orderRef", lst.get(0));
		request.setAttribute("entityType", lst.get(1));
		// request.setAttribute("amount",l2);

		return model;
	}

	@RequestMapping(value = "/submitAssociate", method = RequestMethod.POST)
	public ModelAndView submitAssociate(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("associate") AssociateList contract) throws SQLException {

		if (contract.getIsActive() != "y") {
			contract.setIsActive("n");

		}

		vwDelegate.addAssociate(contract);
		ModelAndView model = getAssociteList(request, response, contract);

		return model;
	}

	// *********End***************

	// **************Customer************
	@RequestMapping(value = "/listCustomer", method = RequestMethod.GET)
	public ModelAndView getCustomer(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean) {
		ModelAndView model = new ModelAndView("CustomerList");
		List list = null;
		try {

			list = vwDelegate.getCustomer();

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("dealerList", list);

		return model;
	}

	@RequestMapping(value = "/newCustomer", method = RequestMethod.GET)
	public ModelAndView newcustomer(HttpServletRequest request, HttpServletResponse response, Customer dealerBean) {
		ModelAndView model = new ModelAndView("newCustomer");

		request.setAttribute("CustomerBean", dealerBean);
		return model;
	}
	// **************End***********

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
		public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
				LoginBean loginBean, RegistrationBean regisBean) throws SQLException {
			ModelAndView model = new ModelAndView("adminRights");

			boolean isValidUser = vwDelegate.deleteUser(id);
			if (isValidUser) {
				System.out.println("delete user success");

			} else {
				request.setAttribute("message", "Invalid credentials!!");
			}
			List<RegistrationBean> list = null;
			try {

				list = vwDelegate.getAllUsers();

			} catch (Exception e) {
				e.printStackTrace();
			}
			List<String> list3 = null;
			try {

				list3 = vwDelegate.getOrderTrans();

			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("orderList", list3);
			
			System.out.println(list3);
			

			List<Role> list4 = null;
			try {

				list4 = vwDelegate.getRole();

			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("userRole", list4);
			request.setAttribute("userList", list);
			model.addObject("regisBean", regisBean);
			return model;
		}

		@RequestMapping(value = "/editUser/{id}", method = RequestMethod.GET)
		public ModelAndView editUser(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
				LoginBean loginBean, RegistrationBean regisBean, Tests testBean) throws SQLException {
			ModelAndView model = new ModelAndView("updateUser");

			RegistrationBean regis = vwDelegate.editUser(id);
			System.out.println("edit User"+regis);
			request.setAttribute("regisData", regis);
			model.addObject("regisBean", regis);
			
			List<String> list3 = null;
			try {

				list3 = vwDelegate.getOrderTrans();

			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("orderList", list3);
			
			System.out.println(list3);
			

			List<Role> list4 = null;
			try {

				list4 = vwDelegate.getRole();

			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("userRole", list4);
			
			List<String> list5=new ArrayList<String>();
			list5.add("L1");list5.add("L2");list5.add("L3");list5.add("L4");list5.add("L5");list5.add("L6");
			
			list5.add("L6");list5.add("L7");
			request.setAttribute("UserLevelList", list5);
			
			return model;
		}

		@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
		public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response,
				@ModelAttribute("regisData") RegistrationBean regisData) throws SQLException {
			ModelAndView model = new ModelAndView("adminRights");
			try {

				boolean insert = vwDelegate.updateUser(regisData);

			} catch (Exception e) {
				e.printStackTrace();
			}
			List<RegistrationBean> list = null;
			try {

				list = vwDelegate.getAllUsers();

			} catch (Exception e) {
				e.printStackTrace();
			}
			RegistrationBean regisBean=new RegistrationBean();
			List<String> list3 = null;
			try {

				list3 = vwDelegate.getOrderTrans();

			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("orderList", list3);
			
			System.out.println(list3);
			

			List<Role> list4 = null;
			try {

				list4 = vwDelegate.getRole();

			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("userRole", list4);
			request.setAttribute("userList", list);
			model.addObject("regisBean", regisBean);
			return model;

		}

	// **************Approver************
	@RequestMapping(value = "/listApprover", method = RequestMethod.GET)
	public ModelAndView listApprover(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean) {
		ModelAndView model = new ModelAndView("ApproverList");
		List list = null;
		try {

			list = vwDelegate.getApprover();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("approverList", list);

		return model;
	}

	@RequestMapping(value = "/deleteApprover/{id}", method = RequestMethod.GET)
	public ModelAndView deleteApprover(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
			LoginBean loginBean, RegistrationBean regisBean, Tests testBean, Tests testData) throws SQLException {
		ModelAndView model = new ModelAndView("ApproverList");

		boolean isValidUser = vwDelegate.deleteApprover(id);
		if (isValidUser) {
			System.out.println("delete user success");

		} else {
			request.setAttribute("message", "Invalid credentials!!");
		}
		List list = null;
		try {

			list = vwDelegate.getApprover();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("approverList", list);

		return model;
	}

	@RequestMapping(value = "/editApprover/{id}", method = RequestMethod.GET)
	public ModelAndView editApprover(HttpServletRequest request, HttpServletResponse response, @PathVariable int id)
			throws SQLException {
		ModelAndView model = new ModelAndView("newApprover");

		Approver approverBean = vwDelegate.editApprover(id);

		request.setAttribute("approverBean", approverBean);

		return model;
	}

	@RequestMapping(value = "/addApprover", method = RequestMethod.GET)
	public ModelAndView addApproverGet(HttpServletRequest request, HttpServletResponse response, Approver approverBean)
			throws SQLException {
		ModelAndView model = new ModelAndView("newApprover");

		request.setAttribute("approverBean", approverBean);
		return model;

	}

	@RequestMapping(value = "/addApprover", method = RequestMethod.POST)
	public ModelAndView addApprover(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("approverBean") Approver approverBean) throws SQLException {
		ModelAndView model = new ModelAndView("ApproverList");

		if (approverBean.getId() == 0) {
			try {

				boolean insert = vwDelegate.addApprover(approverBean);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {

				boolean insert = vwDelegate.updateApprover(approverBean);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List list = null;
		try {

			list = vwDelegate.getApprover();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("approverList", list);

		return model;

	}

	// smr
	@RequestMapping(value = "/listPlant", method = RequestMethod.GET)
	public ModelAndView listSmr(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean) {
		ModelAndView model = new ModelAndView("SmrList");
		List list = null;
		try {

			list = vwDelegate.getSmr();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("smrList", list);

		return model;
	}

	@RequestMapping(value = "/deletePlant/{id}", method = RequestMethod.GET)
	public ModelAndView deleteSmr(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
			LoginBean loginBean, RegistrationBean regisBean, Tests testBean, Tests testData) throws SQLException {
		ModelAndView model = new ModelAndView("SmrList");

		boolean isValidUser = vwDelegate.deleteSmr(id);
		if (isValidUser) {
			System.out.println("delete user success");

		} else {
			request.setAttribute("message", "Invalid credentials!!");
		}
		List list = null;
		try {

			list = vwDelegate.getSmr();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("smrList", list);

		return model;
	}

	@RequestMapping(value = "/editPlant/{id}", method = RequestMethod.GET)
	public ModelAndView editSmr(HttpServletRequest request, HttpServletResponse response, @PathVariable int id)
			throws SQLException {
		ModelAndView model = new ModelAndView("newSmr");

		UserBackup smrBean = vwDelegate.editSmr(id);

		request.setAttribute("smrBean", smrBean);

		return model;
	}

	@RequestMapping(value = "/addPlant", method = RequestMethod.GET)
	public ModelAndView addSmrGet(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("smrBean") UserBackup smrBean) throws SQLException {
		ModelAndView model = new ModelAndView("newSmr");

		request.setAttribute("smrBean", smrBean);
		return model;

	}

	@RequestMapping(value = "/addPlant", method = RequestMethod.POST)
	public ModelAndView addSmr(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("smrBean") UserBackup smrBean) throws SQLException {
		ModelAndView model = new ModelAndView("SmrList");
		System.out.println("%%$$$$$$$$$$" + smrBean.getId());
		if (smrBean.getId() == 0) {
			try {

				boolean insert = vwDelegate.addSmr(smrBean);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {

				boolean insert = vwDelegate.updateSmr(smrBean);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List list = null;
		try {

			list = vwDelegate.getSmr();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("smrList", list);

		return model;

	}

	// **************End***********

	@RequestMapping(value = "/adminChat", method = RequestMethod.GET)
	public ModelAndView UserChat(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean) {
		ModelAndView model = new ModelAndView("adminChat");

		return model;
	}

	@RequestMapping(value = "/listMachine", method = RequestMethod.GET)
	public ModelAndView paymentList(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean) {
		ModelAndView model = new ModelAndView("listPayment");
		List<String> list3 = null;
		try {

			list3 = vwDelegate.getPayment();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("paymentList", list3);

		return model;
	}

	@RequestMapping(value = "/newMachine", method = RequestMethod.GET)
	public ModelAndView newPayment(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean, Payment paymentBean) {
		ModelAndView model = new ModelAndView("newPayment");
		//paymentBean.setField2("kishan");
		
		
		request.setAttribute("paymentBean", paymentBean);
		return model;
	}

	@RequestMapping(value = "/newMachine", method = RequestMethod.POST)
	public ModelAndView newPaymentPost(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("paymentBean") Payment paymentBean) {
		ModelAndView model = new ModelAndView("listPayment");

		try {
			if (paymentBean.getId() == 0) {
				boolean insert = vwDelegate.addPayment(paymentBean);
			} else
				vwDelegate.updatePayment(paymentBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> list3 = null;
		try {

			list3 = vwDelegate.getPayment();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("paymentList", list3);

		return model;
	}

	@RequestMapping(value = "/listReport", method = RequestMethod.GET)
	public ModelAndView delieveryList(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean) {
		ModelAndView model = new ModelAndView("listDelivery");
		List<String> list3 = null;
		try {

			list3 = vwDelegate.getDelievery();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("deliveryList", list3);
		return model;
	}

	@RequestMapping(value = "/newReport", method = RequestMethod.GET)
	public ModelAndView newDelievery(HttpServletRequest request, HttpServletResponse response, Delievery deliveryBean) {
		ModelAndView model = new ModelAndView("newDelivery");
		request.setAttribute("deliveryBean", deliveryBean);
		
		List list3 = null;
		try {

			list3 = vwDelegate.getInvoice();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("invoiceList", list3);

		
		
		
		return model;
	}

	@RequestMapping(value = "/newReport", method = RequestMethod.POST)
	public ModelAndView newDelieveryPost(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("deliveryBean") Delievery deliveryBean) {
		ModelAndView model = new ModelAndView("newDelivery");
		 String imagePath = null;
		  RConnection connection = null;
		  
	         try {
	             /* Create a connection to Rserve instance running
	              * on default port 6311
	              */
	        	 System.out.println(deliveryBean.getField2()+"    "+deliveryBean.getField3());
	        	 
	             connection = new RConnection();
	             
           
	             String vector1 = "c("+deliveryBean.getField2()+","+deliveryBean.getField4()+","+deliveryBean.getField6()+","+deliveryBean.getField8()+")";
	             String vector2 = "c("+deliveryBean.getField3()+","+deliveryBean.getField5()+","+deliveryBean.getField7()+","+deliveryBean.getField9()+")";
	             Date currentDate=new Date();
	             imagePath="img/"+currentDate.getDate()+currentDate.getTime()+"line_chart.png";
	             connection.eval(" png(file = 'D:/eclipse/mobile/Faurecia/src/main/webapp/"+imagePath+"')");	           
	             connection.eval("plot("+vector1+","+vector2+",type = 'o',col = 'red', xlab = '"+deliveryBean.getAmount()+"', ylab = '"+deliveryBean.getField1()+"', main = '"+deliveryBean.getRefId()+"')");
	             
	             connection.eval("dev.off()");
	             request.setAttribute("src", imagePath);
	           
	         } catch (RserveException e) {
	             e.printStackTrace();
	         }finally{
	             connection.close();
	         }
		
		
	         try {
	        	   File file =  new File("D:/eclipse/mobile/Faurecia/src/main/webapp/"+imagePath);
	                FileInputStream fileInputStreamReader = new FileInputStream(file);
	                byte[] bytes = new byte[(int)file.length()];
	                fileInputStreamReader.read(bytes);
	              String  encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
	              System.out.println(encodedfile+"^^^^^^^^^^^^^^^^");
	              request.setAttribute("srcBase64", encodedfile);
	            } catch (FileNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
		
		
	 		String tempField2=deliveryBean.getField2();
			String tempField3=deliveryBean.getField3();
		try {
			deliveryBean.setField2(deliveryBean.getField2()+","+deliveryBean.getField4()+","+deliveryBean.getField6()+","+deliveryBean.getField8());
			deliveryBean.setField3(deliveryBean.getField3()+","+deliveryBean.getField5()+","+deliveryBean.getField7()+","+deliveryBean.getField9());
			deliveryBean.setIsValidate(imagePath);
			if (deliveryBean.getId() == 0)
				vwDelegate.addDelievery(deliveryBean);
			
		
			else
				vwDelegate.updateDelivery(deliveryBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		deliveryBean.setField2(tempField2);
		deliveryBean.setField3(tempField3);
		
		request.setAttribute("deliveryBean", deliveryBean);

		return model;
	}

	@RequestMapping(value = "/deleteMachine/{id}", method = RequestMethod.GET)
	public ModelAndView deletePayment(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
			LoginBean loginBean, RegistrationBean regisBean, Tests testBean, Tests testData) throws SQLException {
		ModelAndView model = new ModelAndView("listPayment");

		boolean isValidUser = vwDelegate.deletePayment(id);
		if (isValidUser) {
			System.out.println("delete user success");

		} else {
			request.setAttribute("message", "Invalid credentials!!");
		}

		List<String> list3 = null;
		try {

			list3 = vwDelegate.getPayment();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("paymentList", list3);

		return model;
	}

	@RequestMapping(value = "/editMachine/{id}", method = RequestMethod.GET)
	public ModelAndView editPayment(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
			LoginBean loginBean, RegistrationBean regisBean, Tests testBean) throws SQLException {
		ModelAndView model = new ModelAndView("newPayment");

		Payment pay = vwDelegate.editPayment(id);

		request.setAttribute("paymentBean", pay);

		return model;
	}

	@RequestMapping(value = "/deleteReport/{id}", method = RequestMethod.GET)
	public ModelAndView deleteDelivery(HttpServletRequest request, HttpServletResponse response, @PathVariable int id)
			throws SQLException {
		ModelAndView model = new ModelAndView("listDelivery");

		boolean isValidUser = vwDelegate.deleteDelivery(id);
		if (isValidUser) {
			System.out.println("delete user success");

		} else {
			request.setAttribute("message", "Invalid credentials!!");
		}

		List list3 = null;
		try {

			list3 = vwDelegate.getDelievery();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("deliveryList", list3);

		return model;
	}

	@RequestMapping(value = "/editReport/{id}", method = RequestMethod.GET)
	public ModelAndView editDelivery(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
			LoginBean loginBean, RegistrationBean regisBean, Tests testBean) throws SQLException {
		ModelAndView model = new ModelAndView("newDelivery");

		Delievery pay = vwDelegate.editDelivery(id);

		request.setAttribute("deliveryBean", pay);

		return model;
	}

	@RequestMapping(value = "/listDashboard", method = RequestMethod.GET)
	public ModelAndView orderTransList(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean) {
		ModelAndView model = new ModelAndView("listOrderTrans");
		List<String> list3 = null;
		try {

			list3 = vwDelegate.getOrderTrans();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("orderList", list3);
		
		return model;
	}

	@RequestMapping(value = "/newDashboard", method = RequestMethod.GET)
	public ModelAndView newOrderTrans(HttpServletRequest request, HttpServletResponse response,
			OrderTrans orderTransBean) {
		ModelAndView model = new ModelAndView("newOrderTrans");
		request.setAttribute("orderTransBean", orderTransBean);
		List list = null;
		try {

			list = vwDelegate.getInvoice();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("reportGroupList", list);
		return model;
	}

	@RequestMapping(value = "/newDashboard", method = RequestMethod.POST)
	public ModelAndView newOrderTransPost(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("orderTransBean") OrderTrans orderTransBean) {
		ModelAndView model = new ModelAndView("listOrderTrans");
		
		try {
			
			if (orderTransBean.getId() == 0) {
				boolean insert = vwDelegate.addOrderTrans(orderTransBean);
			} else
				vwDelegate.updateOrderTrans(orderTransBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> list3 = null;
		
		
		try {

			list3 = vwDelegate.getOrderTrans();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("orderList", list3);
		  
	         
		
		
	request.setAttribute("orderTransBean", orderTransBean);
		return model;
	}

	@RequestMapping(value = "/listReportGroup", method = RequestMethod.GET)
	public ModelAndView orderInvoice(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean) {
		ModelAndView model = new ModelAndView("listInvoice");
		List list3 = null;
		try {

			list3 = vwDelegate.getInvoice();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("invoiceList", list3);

		return model;
	}

	@RequestMapping(value = "/newReportGroup", method = RequestMethod.GET)
	public ModelAndView newInvoice(HttpServletRequest request, HttpServletResponse response, Invoice invoiceBean) {
		ModelAndView model = new ModelAndView("newInvoice");
		request.setAttribute("invoiceBean", invoiceBean);
		return model;
	}

	@RequestMapping(value = "/newReportGroup", method = RequestMethod.POST)
	public ModelAndView newInvoicePost(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("invoiceBean") Invoice invoiceBean) {
		ModelAndView model = new ModelAndView("listInvoice");

		try {
			if (invoiceBean.getId() == 0) {
				boolean insert = vwDelegate.addInvoice(invoiceBean);
			} else
				vwDelegate.updateInvoice(invoiceBean);

		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> list3 = null;
		try {

			list3 = vwDelegate.getInvoice();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("invoiceList", list3);

		return model;
	}

	@RequestMapping(value = "/deleteDashboard/{id}", method = RequestMethod.GET)
	public ModelAndView deleteOrderTrans(HttpServletRequest request, HttpServletResponse response, @PathVariable int id,
			LoginBean loginBean, RegistrationBean regisBean, Tests testBean, Tests testData) throws SQLException {
		ModelAndView model = new ModelAndView("listOrderTrans");

		boolean isValidUser = vwDelegate.deleteOrderTrans(id);
		if (isValidUser) {
			System.out.println("delete user success");

		} else {
			request.setAttribute("message", "Invalid credentials!!");
		}

		List<String> list3 = null;
		try {

			list3 = vwDelegate.getOrderTrans();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("orderList", list3);

		return model;
	}

	@RequestMapping(value = "/editDashboard/{id}", method = RequestMethod.GET)
	public ModelAndView editOrderTrans(HttpServletRequest request, HttpServletResponse response, @PathVariable int id)
			throws SQLException {
		ModelAndView model = new ModelAndView("newOrderTrans");

		OrderTrans pay = vwDelegate.editOrderTrans(id);

		request.setAttribute("orderTransBean", pay);

		return model;
	}

	@RequestMapping(value = "/deleteReportGroup/{id}", method = RequestMethod.GET)
	public ModelAndView deleteInvoice(HttpServletRequest request, HttpServletResponse response, @PathVariable int id)
			throws SQLException {
		ModelAndView model = new ModelAndView("listInvoice");

		boolean isValidUser = vwDelegate.deleteInvoice(id);
		if (isValidUser) {
			System.out.println("delete user success");

		} else {
			request.setAttribute("message", "Invalid credentials!!");
		}

		List list3 = null;
		try {

			list3 = vwDelegate.getInvoice();

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("invoiceList", list3);

		return model;
	}

	@RequestMapping(value = "/editReportGroup/{id}", method = RequestMethod.GET)
	public ModelAndView editInvoice(HttpServletRequest request, HttpServletResponse response, @PathVariable int id)
			throws SQLException {
		ModelAndView model = new ModelAndView("newInvoice");

		Invoice pay = vwDelegate.editInvoice(id);

		request.setAttribute("invoiceBean", pay);

		return model;
	}

	// *******End*************
	
	@RequestMapping(value = "/oneOK", method = RequestMethod.GET)
	public ModelAndView oneOK(HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		ModelAndView model = new ModelAndView("oneOK");



		return model;
	}
	
	
	
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public ModelAndView getReport(HttpServletRequest request, HttpServletResponse response) throws JSONException {
		ModelAndView model = new ModelAndView("report");
		List<Invoice> list3 = null;
		try {

			list3 = vwDelegate.getInvoice();

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		request.setAttribute("reportGroupList", list3);
		System.out.println("**"+list3);
		List<Delievery> list = null;

		try {

			list = vwDelegate.getDelievery();

		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject storeReportGroup=new JSONObject();
		for (int ReportGroupCount=0; ReportGroupCount<list3.size(); ReportGroupCount++){
		
			
			JSONArray storeReport=new JSONArray();
		for (int ReportCount=0; ReportCount<list.size(); ReportCount++){
		  //	System.out.println(list3.get(ReportGroupCount).getAmount()+"  "+list.get(ReportCount).getRefId()); 
			if(list3.get(ReportGroupCount).getAmount().equals(list.get(ReportCount).getRefId())){
				
			String imagePath=list.get(ReportCount).getIsValidate();
		
			  try {
	        	   File file =  new File("D:/eclipse/mobile/Faurecia/src/main/webapp/"+imagePath);
	                FileInputStream fileInputStreamReader = new FileInputStream(file);
	                byte[] bytes = new byte[(int)file.length()];
	                fileInputStreamReader.read(bytes);
	              String  encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
	              Delievery tempReport=list.get(ReportCount);
	              tempReport.setIsValidate(encodedfile);
	          //	System.out.println(encodedfile);
	              list.set(ReportCount,tempReport);
	              storeReport.put(encodedfile);
	            } catch (FileNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
			
			}
			}
		//System.out.println(storeReport);
		storeReportGroup.put(list3.get(ReportGroupCount).getAmount(), storeReport);
		}
	
		
		Collections.reverse(list);
		System.out.println(storeReportGroup);
		request.setAttribute("reportJson", storeReportGroup.toString());
		request.setAttribute("reportList", list);
		return model;
	
}
	
	
	@RequestMapping(value = "/listNumberOfCases", method = RequestMethod.GET)
	public ModelAndView listNumberOfCases(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			FunctionAppBean functionAppBean) throws SQLException {
		ModelAndView model = new ModelAndView("listNumberOfCases");
		
		
		
		
		
		request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		request.setAttribute("FunctionAppBean", functionAppBean);
		
		return model;
	}
	
	@RequestMapping(value = "/submitNumberOfCases", method = RequestMethod.POST)
	public ModelAndView submitNumberOfCases(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("FunctionAppBean")FunctionAppBean functionAppBean) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("listNumberOfCases");
		
		System.out.println("functionAppBean"+functionAppBean);
	request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		
	request.setAttribute("listCasesCreated", vwDelegate.getCasesCreated(functionAppBean));
		return model;
	}
	
	@RequestMapping(value = "/listNumberOfCasesCompleted", method = RequestMethod.GET)
	public ModelAndView listNumberOfCasesCompleted(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			FunctionAppBean functionAppBean) throws SQLException {
		ModelAndView model = new ModelAndView("listNumberOfCasesCompleted");
	
		request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		request.setAttribute("FunctionAppBean", functionAppBean);
		
		return model;
	}
	
	@RequestMapping(value = "/submitNumberOfCasesCompleted", method = RequestMethod.POST)
	public ModelAndView submitNumberOfCasesCompleted(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("FunctionAppBean")FunctionAppBean functionAppBean) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("listNumberOfCasesCompleted");
		
		System.out.println("functionAppBean"+functionAppBean);
	request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		
	request.setAttribute("listCasesCreated", vwDelegate.getCasesCreatedCompleted(functionAppBean));
		return model;
	}
	
	
	@RequestMapping(value = "/listRoleWiseTimeTaken", method = RequestMethod.GET)
	public ModelAndView listRoleWiseTimeTaken(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			FunctionAppBean functionAppBean) throws SQLException {
		ModelAndView model = new ModelAndView("listRoleWiseTimeTaken");
	
		request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		request.setAttribute("FunctionAppBean", functionAppBean);
		
		return model;
	}
	
	@RequestMapping(value = "/submitRoleWiseTimeTaken", method = RequestMethod.POST)
	public ModelAndView submitRoleWiseTimeTaken(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("FunctionAppBean")FunctionAppBean functionAppBean) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("listRoleWiseTimeTaken");
		
		System.out.println("functionAppBean"+functionAppBean);
	request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		
	request.setAttribute("listCasesCreated", vwDelegate.getRoleWiseTimeTaken(functionAppBean));
		return model;
	}
	
	@RequestMapping(value = "/listTotalCycleTime", method = RequestMethod.GET)
	public ModelAndView listTotalCycleTime(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			FunctionAppBean functionAppBean) throws SQLException {
		ModelAndView model = new ModelAndView("listTotalTimeTaken");
	
		request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		request.setAttribute("FunctionAppBean", functionAppBean);
		
		return model;
	}
	
	@RequestMapping(value = "/submitTotalCycleTime", method = RequestMethod.POST)
	public ModelAndView submitTotalCycleTime(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("FunctionAppBean")FunctionAppBean functionAppBean) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("listTotalTimeTaken");
		
		System.out.println("functionAppBean"+functionAppBean);
	request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		
	request.setAttribute("listCasesCreated", vwDelegate.getTotalCycleTime(functionAppBean));
		return model;
	}
	
	
	
	
	@RequestMapping(value = "/reportTimeTakenLCCreationAvgDays", method = RequestMethod.GET)
	public ModelAndView reportTimeTakenLCCreationAvgDays(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		ModelAndView model = new ModelAndView("reportTimeTakenLCCreationAvgDays");
	
		 String imagePath = null;
		  RConnection connection = null;
		
	      String vector[]={"c(2.2,1.9,1.2)","c(1.5,1.9,0.8)","c(600,200,75)","c(75,20,10)"};
	      String[] main={"Time taken-LC Creation(Average Days)","Time taken-BOE Creation(Average Days)","Credit Notes Issued(in Crores)","Unidentifed Credits(in Crores)"};

	      String imageSource[]=new String[vector.length];
	      
	      for(int countVector=0;countVector<vector.length;countVector++){
	         try {
	  
	             connection = new RConnection();
	       
	             //connection.eval("colors <- c('green','orange','brown')");
	             
	             connection.eval("  M <- c('Vijaynagar','Dolvi','Salem')");
	           
	            Date currentDate=new Date();
	             imagePath=""+currentDate.getDate()+currentDate.getTime()+"barchart.png";
	             connection.eval(" png(file = 'D:/Users/rajemotw/Desktop/Moved/LBS/JSW/JSW/src/main/webapp/img/"+imagePath+"')");
	            // System.out.println(imagePath);
	             connection.eval("barplot("+vector[countVector]+",names.arg=M,col = 'blue',main='"+main[countVector]+"')");
	             
	             connection.eval("dev.off()");
	           
	           
	         } catch (RserveException e) {
	             e.printStackTrace();
	         }finally{
	             connection.close();
	         }
		
		
	         try {
	        	   File file =  new File("D:/Users/rajemotw/Desktop/Moved/LBS/JSW/JSW/src/main/webapp/img/"+imagePath);
	                FileInputStream fileInputStreamReader = new FileInputStream(file);
	                byte[] bytes = new byte[(int)file.length()];
	                fileInputStreamReader.read(bytes);
	              String  encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
	             // System.out.println(encodedfile+"^^^^^^^^^^^^^^^^");
	              //request.setAttribute("srcBase64", encodedfile);
	              
	              imageSource[countVector]=encodedfile;
	            } catch (FileNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
		
	      }
	      request.setAttribute("srcBase64", imageSource);
	      //System.out.println(imageSource[2]);
		return model;
	}
	

	
	@RequestMapping(value = "/listNumberOfCasesCreatedHR", method = RequestMethod.GET)
	public ModelAndView listNumberOfCasesCreatedHR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			FunctionAppBean functionAppBean) throws SQLException {
		ModelAndView model = new ModelAndView("listNumberOfCasesCreatedHR");
	
		request.setAttribute("functionTable", vwDelegate.getNumberOfCasesHR());
		request.setAttribute("FunctionAppBean", functionAppBean);
		
		return model;
	}
	
	@RequestMapping(value = "/submitNumberOfCasesCreatedHR", method = RequestMethod.POST)
	public ModelAndView submitNumberOfCasesCreatedHR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("FunctionAppBean")FunctionAppBean functionAppBean) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("listNumberOfCasesCreatedHR");
		
		System.out.println("functionAppBean"+functionAppBean);
	request.setAttribute("functionTable", vwDelegate.getNumberOfCasesHR());
		
	request.setAttribute("listCasesCreated", vwDelegate.getNumberOfCasesCreatedHR(functionAppBean));
		return model;
	}
	

	
	
	
	@RequestMapping(value = "/listNumberOfCasesCompletedHR", method = RequestMethod.GET)
	public ModelAndView listNumberOfCasesCompletedHR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			FunctionAppBean functionAppBean) throws SQLException {
		ModelAndView model = new ModelAndView("listNumberOfcaseCompletedHR");
		
		request.setAttribute("functionTable", vwDelegate.getNumberOfCasesHR());
		request.setAttribute("FunctionAppBean", functionAppBean);
		
		return model;
	}
	
	@RequestMapping(value = "/submitNumberOfCasesCompletedHR", method = RequestMethod.POST)
	public ModelAndView submitNumberOfCasesCompletedHR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("FunctionAppBean")FunctionAppBean functionAppBean) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("listNumberOfcaseCompletedHR");
		
		System.out.println("functionAppBean"+functionAppBean);
	request.setAttribute("functionTable", vwDelegate.getNumberOfCasesHR());
		
	request.setAttribute("listCasesCreated", vwDelegate.getCasesCreatedCompletedHR(functionAppBean));
		return model;
	}
	
	
	@RequestMapping(value = "/listRoleWiseTimeTakenHR", method = RequestMethod.GET)
	public ModelAndView listRoleWiseTimeTakenHR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			FunctionAppBean functionAppBean) throws SQLException {
		ModelAndView model = new ModelAndView("listRoleWiseTimeTakenHR");
	
		request.setAttribute("functionTable", vwDelegate.getNumberOfCasesHR());
		request.setAttribute("FunctionAppBean", functionAppBean);
		
		return model;
	}
	
	@RequestMapping(value = "/submitRoleWiseTimeTakenHR", method = RequestMethod.POST)
	public ModelAndView submitRoleWiseTimeTakenHR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("FunctionAppBean")FunctionAppBean functionAppBean) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("listRoleWiseTimeTakenHR");
		
		System.out.println("functionAppBean"+functionAppBean);
	request.setAttribute("functionTable", vwDelegate.getNumberOfCasesHR());
		
	request.setAttribute("listCasesCreated", vwDelegate.getRoleWiseTimeTakenHR(functionAppBean));
		return model;
	}
	
	@RequestMapping(value = "/listTotalCycleTimeHR", method = RequestMethod.GET)
	public ModelAndView listTotalCycleTimeHR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			FunctionAppBean functionAppBean) throws SQLException {
		ModelAndView model = new ModelAndView("listTotalTimeTakenHR");
	
		request.setAttribute("functionTable", vwDelegate.getNumberOfCasesHR());
		request.setAttribute("FunctionAppBean", functionAppBean);
		
		return model;
	}
	
	@RequestMapping(value = "/submitTotalCycleTimeHR", method = RequestMethod.POST)
	public ModelAndView submitTotalCycleTimeHR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("FunctionAppBean")FunctionAppBean functionAppBean) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("listTotalTimeTakenHR");
		
		System.out.println("functionAppBean"+functionAppBean);
	request.setAttribute("functionTable", vwDelegate.getNumberOfCasesHR());
		
	request.setAttribute("listCasesCreated", vwDelegate.getTotalCycleTimeHR(functionAppBean));
		return model;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/listNumberOfCasesCreatedAR", method = RequestMethod.GET)
	public ModelAndView listNumberOfCasesCreatedAR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			FunctionAppBean functionAppBean) throws SQLException {
		ModelAndView model = new ModelAndView("listNumberOfCasesCreatedAR");
	
		request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		request.setAttribute("FunctionAppBean", functionAppBean);
		
		return model;
	}
	
	@RequestMapping(value = "/submitNumberOfCasesCreatedAR", method = RequestMethod.POST)
	public ModelAndView submitNumberOfCasesCreatedAR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("FunctionAppBean")FunctionAppBean functionAppBean) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("listNumberOfCasesCreatedAR");
		
		System.out.println("functionAppBean"+functionAppBean);
	request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		
	request.setAttribute("listCasesCreated", vwDelegate.getNumberOfCasesCreatedAR(functionAppBean));
		return model;
	}
	

	
	
	
	@RequestMapping(value = "/listNumberOfCasesCompletedAR", method = RequestMethod.GET)
	public ModelAndView listNumberOfCasesCompletedAR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			FunctionAppBean functionAppBean) throws SQLException {
		ModelAndView model = new ModelAndView("listNumberOfCasesCompletedAR");
		
		request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		request.setAttribute("FunctionAppBean", functionAppBean);
		
		return model;
	}
	
	@RequestMapping(value = "/submitNumberOfCasesCompletedAR", method = RequestMethod.POST)
	public ModelAndView submitNumberOfCasesCompletedAR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("FunctionAppBean")FunctionAppBean functionAppBean) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("listNumberOfCasesCompletedAR");
		
		System.out.println("functionAppBean"+functionAppBean);
	request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		
	request.setAttribute("listCasesCreated", vwDelegate.getCasesCreatedCompletedAR(functionAppBean));
		return model;
	}
	
	
	@RequestMapping(value = "/listRoleWiseTimeTakenAR", method = RequestMethod.GET)
	public ModelAndView listRoleWiseTimeTakenAR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			FunctionAppBean functionAppBean) throws SQLException {
		ModelAndView model = new ModelAndView("listRoleWiseTimeTakenAR");
	
		request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		request.setAttribute("FunctionAppBean", functionAppBean);
		
		return model;
	}
	
	@RequestMapping(value = "/submitRoleWiseTimeTakenAR", method = RequestMethod.POST)
	public ModelAndView submitRoleWiseTimeTakenAR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("FunctionAppBean")FunctionAppBean functionAppBean) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("listRoleWiseTimeTakenAR");
		
		System.out.println("functionAppBean"+functionAppBean);
	request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		
	request.setAttribute("listCasesCreated", vwDelegate.getRoleWiseTimeTakenAR(functionAppBean));
		return model;
	}
	
	@RequestMapping(value = "/listTotalCycleTimeAR", method = RequestMethod.GET)
	public ModelAndView listTotalCycleTimeAR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			FunctionAppBean functionAppBean) throws SQLException {
		ModelAndView model = new ModelAndView("listTotalTimeTakenAR");
	
		request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		request.setAttribute("FunctionAppBean", functionAppBean);
		
		return model;
	}
	
	@RequestMapping(value = "/submitTotalCycleTimeAR", method = RequestMethod.POST)
	public ModelAndView submitTotalCycleTimeAR(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			@ModelAttribute("FunctionAppBean")FunctionAppBean functionAppBean) throws SQLException, JSONException {
		ModelAndView model = new ModelAndView("listTotalTimeTakenAR");
		
		System.out.println("functionAppBean"+functionAppBean);
	request.setAttribute("functionTable", vwDelegate.getNumberOfCases());
		
	request.setAttribute("listCasesCreated", vwDelegate.getTotalCycleTimeAR(functionAppBean));
		return model;
	}
	
	
	
	
}
