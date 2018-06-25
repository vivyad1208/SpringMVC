package app.mvc.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import app.mvc.model.User;

@Service
public class LoginService {

	public boolean isActive(HttpSession session) {
		User user = (User) session.getAttribute(User.class.getCanonicalName());
		return user!=null && user.getUserId()>0;
	}

	public boolean authenticate(User user) {
		if("jb@jb.com".equalsIgnoreCase(user.getUserName()) && "jb".equals(user.getPassword())) {
			return true;
		}
		return false;
	}
}
