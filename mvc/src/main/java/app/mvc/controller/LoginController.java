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

@Controller
@SessionAttributes
public class LoginController {

	public static final String LOGIN = "login";
	public static final String HOMEPAGE = "homepage";
	public static final String REDIRECT_LOGIN = "redirect:login";
	public static final String REDIRECT_HOMEPAGE = "redirect:homepage";

	@Autowired
	private LoginService loginService;

	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	public Object toLoginPage(HttpSession session) {
		if(loginService.isActive(session))
			return REDIRECT_HOMEPAGE;
		return new ModelAndView(LOGIN, "command", new User());
	}

	@RequestMapping(value="/authenticate", method=RequestMethod.GET)
	public String authenticationInvalid(HttpSession session) {
		if(loginService.isActive(session))
			return REDIRECT_HOMEPAGE;
		return REDIRECT_LOGIN;
	}

	/**
	 * Redirect to login page with validating the session. <br/>
	 * Uses {@link RedirectView} to redirect to the login page. <br/>
	 * The {@link RedirectView#setUrl(String)} method is used to set the name of the page to be redirected to. <br/>
	 * The {@link RedirectView#setExposeModelAttributes(boolean)} method decides whether the model attributes
	 * should be exposed as HTTP query parameters
	 * 
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public Object authenticationPage(@ModelAttribute User user, ModelMap model, HttpSession session) {
		if(loginService.isActive(session))
			return REDIRECT_HOMEPAGE;
		if(loginService.authenticate(user)) {
			user.setUserId(123);
			user.deletePassword();
			String strUser = User.class.getCanonicalName();
			session.setAttribute(strUser, user);
			model.addAttribute("userId", user.getUserId());
			model.addAttribute("userName", user.getUserName());

			RedirectView redirect = new RedirectView();
			redirect.setContextRelative(true);
	        redirect.setExposeModelAttributes(false);
			redirect.setUrl(HOMEPAGE);
			return redirect;
		}
		return REDIRECT_LOGIN;
	}

	@RequestMapping(value="/logout", method={RequestMethod.GET,RequestMethod.POST})
	public String toLogoutPage(HttpSession session) {
		session.invalidate();
		return REDIRECT_LOGIN;
	}

	@RequestMapping(value="/homepage", method={RequestMethod.GET,RequestMethod.POST})
	public String toHomePage(HttpSession session) {
		if(loginService.isActive(session))
			return HOMEPAGE;
		return REDIRECT_LOGIN;
	}
}

/*
 * RedirectView.setContextRelative(true)
When set to true the redirected URL that starts with a slash ("/") is considered as relative to the current ServletContext,
i.e. as relative to the web application root. Default is "false": A URL that starts with a slash will be interpreted as absolute,
i.e. taken as-is. If "true", the context path will be prepended to the URL in such a case.
 */
