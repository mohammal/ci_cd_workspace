package jsw.report.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import jsw.report.delegate.LoginDelegate;
import jsw.report.viewBean.LoginBean;
import jsw.report.viewBean.RegistrationBean;
import jsw.report.viewBean.Tests;

@Controller
public class LoginController {
	@Autowired
	private LoginDelegate loginDelegate;

	public LoginDelegate getLoginDelegate() {
		return loginDelegate;
	}

	public void setLoginDelegate(LoginDelegate loginDelegate) {
		this.loginDelegate = loginDelegate;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean) {
		ModelAndView model = new ModelAndView("index");
		// LoginBean loginBean = new LoginBean();
		model.addObject("LoginBean", loginBean);
		model.addObject("regisBean", regisBean);

		return model;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean,
			RegistrationBean regisBean) {
		ModelAndView model = new ModelAndView("home");
		// LoginBean loginBean = new LoginBean();
		model.addObject("LoginBean", loginBean);
		model.addObject("regisBean", regisBean);

		return model;
	}
	
	@RequestMapping(value = "/tests", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("LoginBean") LoginBean loginBean, Tests testBean, Tests testData) {

		List<Tests> list = null;
		try {

			list = loginDelegate.getTest();
			System.out.println(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("test", list);
		request.setAttribute("testBean", testBean);
		request.setAttribute("testData", testData);
		return new ModelAndView("TestingInfo");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("LoginBean") LoginBean loginBean, Tests testBean, Tests testData) {
		ModelAndView model = null;
		try {
			String isValidUser = loginDelegate.isValidUser(loginBean.getUsername(), loginBean.getPassword());
			if (isValidUser != null && isValidUser != "0") {
				System.out.println("User Login Successful" + isValidUser);


				String screenID = loginDelegate.getScreenID(isValidUser);

				request.getSession().setAttribute("sid", screenID);
				request.getSession().setAttribute("menuNames", loginDelegate.getMenuNames(screenID));
				request.getSession().setAttribute("UserChatHistory", loginDelegate.getUserHistory());
				
				request.getSession().setAttribute("loggedUser", loginBean.getUsername());
				request.getSession().setAttribute("userName", loginBean.getUsername());
				request.getSession().setAttribute("UserId", loginDelegate.getProfile(loginBean.getUsername()).getId());

				return new ModelAndView("home");
			} else {
				model = new ModelAndView("index");
				model.addObject("regisBean", new RegistrationBean());
				request.setAttribute("message", "Invalid credentials!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/regis", method = RequestMethod.POST)
	public ModelAndView executeRegis(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("regisBean") RegistrationBean regisBean, LoginBean loginBean) throws SQLException {
		ModelAndView model = new ModelAndView("index");
		boolean reg = loginDelegate.insertUser(regisBean.getUsename(), regisBean.getName(), regisBean.getPassword(),
				regisBean.getGender(), regisBean.getEmail(), regisBean.getPassportNo(), regisBean.getIssueDate(),
				regisBean.getExpiryDate(), regisBean.getNationality(), regisBean.getActor());
		// LoginBean loginBean = new LoginBean();
		model.addObject("LoginBean", loginBean);
		model.addObject("regisBean", regisBean);
		request.setAttribute("message", "Register Successful, Go for Login!!");

		return model;
	}

	@RequestMapping(value = "/regis", method = RequestMethod.GET)
	public ModelAndView getRegis(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("regisBean") RegistrationBean regisBean, LoginBean loginBean) {
		ModelAndView model = new ModelAndView("index");
		// LoginBean loginBean = new LoginBean();
		model.addObject("LoginBean", loginBean);
		model.addObject("regisBean", regisBean);
		request.setAttribute("regis", "yes");
		return model;
	}

	@RequestMapping(value = "/calculateEmi", method = RequestMethod.GET)
	public ModelAndView calcEmi(HttpServletRequest request, HttpServletResponse response, RegistrationBean regisBean) {
		ModelAndView model = new ModelAndView("calculateEmi");
		// LoginBean loginBean = new LoginBean();
		model.addObject("regisBean", regisBean);
		request.setAttribute("message", "Registration");

		return model;
	}

	@RequestMapping(value = "/getProfile", method = RequestMethod.GET)
	public ModelAndView getProfile(HttpServletRequest request, HttpServletResponse response) {

		System.out.println(request.getSession().getAttribute("loggedUser"));
		request.setAttribute("loggedInUser", request.getSession().getAttribute("loggedUser"));
		RegistrationBean registrationBean = null;

		try {

			registrationBean = loginDelegate.getProfile((String) request.getSession().getAttribute("loggedUser"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("profile", "registrationBean", registrationBean);
	}

}
