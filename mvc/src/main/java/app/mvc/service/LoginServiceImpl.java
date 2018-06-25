package app.mvc.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.view.RedirectView;

import app.mvc.model.User;
import app.service.RedirectService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private RedirectService redirectService;

	private User user = null;

	@Override
	public boolean authenticate(User user) {
		return "jb@jb.com".equalsIgnoreCase(user.getUserName()) && "jb".equals(user.getPassword());
	}

	@Override
	public boolean isActive(HttpSession session) {
		return getUser(session)!=null && getUser().getUserId()>0;
	}

	@Override
	public boolean invalidateUser(HttpSession session) {
		boolean wasUserInvalidated = session.getAttribute(User.class.getCanonicalName())==null;
		user=null;
		session.invalidate();
		return wasUserInvalidated;
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public User getUser(HttpSession session) {
		if(user==null && session!=null) {
			user = (User) session.getAttribute(User.class.getCanonicalName()); }
		return user;
	}

	@Override
	public void setUserAttributes(ModelMap model) {
		if(user!=null) {
			model.addAttribute("userId", user.getUserId());
			model.addAttribute("userName", user.getUserName());
		}
	}

	@Override
	public RedirectView toPageWithUserAttributes(ModelMap model, String page, boolean contextFlag) {
		setUserAttributes(model);
		return redirectService.toPage(page, contextFlag);
	}

	@Override
	public RedirectView toPageWithUserAttributes(ModelMap model, String page) {
		return toPageWithUserAttributes(model, page, true);
	}
}
