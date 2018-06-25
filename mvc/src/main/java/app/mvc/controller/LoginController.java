package app.mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import app.mvc.model.User;
import app.mvc.service.LoginService;
import app.service.RedirectService;
import constants.PAGES;

@Controller
@SessionAttributes
public class LoginController {

	@Autowired
	private LoginService service;

	@Autowired
	private RedirectService redirectService;


	/**
	 * Request Handler for the login page. If the user session is already active,
	 * then the user is redirected to the HomePage.
	 * 
	 * {@link RequestMapping} parameters: 
	 *   1. value="/authenticate" 
	 *   2. method={RequestMethod.GET,RequestMethod.POST}
	 *   
	 * @param {@link HttpSession}
	 * @param {@link ModelMap}
	 * @return {@link ModelAndView} / {@link RedirectView}
	 */
	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	public Object toLoginPage(HttpSession session, ModelMap model) {
		if(service.isActive(session)) {
			return service.toPageWithUserAttributes(model, PAGES.HOMEPAGEREDIRECT.getPage()); }
		return new ModelAndView(PAGES.LOGIN.getPage(), "command", new User());
	}


	/**
	 * Handles the authentication request sent using GET method.
	 * If session is active and valid then the redirection is performed
	 * to the home page, if not then redirected to the login page.
	 * 
	 * {@link RequestMapping} parameters: 
	 *   1. value="/authenticate" 
	 *   2. method=RequestMethod.GET
	 * 
	 * @param {@link HttpSession}
	 * @return {@link String}
	 */
	@RequestMapping(value="/authenticate", method=RequestMethod.GET)
	public String authenticationInvalid(HttpSession session) {
		if(service.isActive(session))
			return PAGES.HOMEPAGEREDIRECT.getPage();
		return PAGES.LOGINREDIRECT.getPage();
	}


	/**
	 * Redirect to login page with validating the session.
	 * Uses {@link RedirectView} to redirect to the login page.
	 * The {@link RedirectView#setUrl(String)} method is used to set the name of the page to
	 * be redirected to. The {@link RedirectView#setExposeModelAttributes(boolean)} method
	 * decides whether the model attributes should be exposed as HTTP query parameters.
	 * 
	 * {@link RequestMapping} parameters: 
	 *   1. value="/authenticate" 
	 *   2. method={RequestMethod.POST}
	 * 
	 * @param {@link User}
	 * @param {@link ModelMap}
	 * @param {@link HttpSession}
	 * @return {@link RedirectView} / {@link String}
	 */
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public Object authenticationPage(@ModelAttribute User user, ModelMap model, HttpSession session) {
		if(service.isActive(session)) {	// User session is already active
			return PAGES.HOMEPAGEREDIRECT.getPage(); }

		if(service.authenticate(user)) {	// User authenticated successful
			/* Setting user details in HttpSession */
			user.setUserId(123);
			user.deletePassword();
			session.setAttribute(User.class.getCanonicalName(), user);
			return redirectService.toPage(PAGES.HOMEPAGE.getPage());	// Redirect to HomePage
		}

		return PAGES.LOGINREDIRECT.getPage();	// User authenticated unsuccessful
	}


	/**
	 * Request Handler for logout process.
	 * {@link RequestMapping} parameters: 
	 *   1. value="/logout" 
	 *   2. method={RequestMethod.GET,RequestMethod.POST}
	 * @param {@link HttpSession}
	 * @return {@link String}
	 */
	@RequestMapping(value="/logout", method={RequestMethod.GET,RequestMethod.POST})
	public String toLogoutPage(HttpSession session) {
		service.invalidateUser(session);
		return PAGES.LOGINREDIRECT.getPage();
	}


	/**
	 * Request Handler for the login page.
	 * {@link RequestMapping} parameters: 
	 *   1. value="/homepage" 
	 *   2. method={RequestMethod.GET,RequestMethod.POST}
	 * @param {@link HttpSession}
	 * @param {@link ModelMap}
	 * @return {@link String} strURL
	 */
	@RequestMapping(value="/homepage", method={RequestMethod.GET,RequestMethod.POST})
	public String toHomePage(HttpSession session, ModelMap model) {
		if(service.isActive(session)) {
			service.setUserAttributes(model);
			return PAGES.HOMEPAGE.getPage();
		}
		return PAGES.LOGINREDIRECT.getPage();
	}
}

/*
 * RedirectView.setContextRelative(true)
When set to true the redirected URL that starts with a slash ("/") is considered as relative to the current ServletContext,
i.e. as relative to the web application root. Default is "false": A URL that starts with a slash will be interpreted as absolute,
i.e. taken as-is. If "true", the context path will be prepended to the URL in such a case.
 */
