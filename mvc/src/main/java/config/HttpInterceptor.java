package config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import app.mvc.model.User;

public class HttpInterceptor extends HandlerInterceptorAdapter {

	@Override
	public synchronized boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);
		User user = session==null ? null : (User) session.getAttribute(User.class.getCanonicalName());
		if (user!=null && user.getUserId()>0) {
			return true;
		}
		response.sendRedirect("login");
		return false;
	}

}
