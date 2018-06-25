package app.mvc.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.view.RedirectView;

import app.mvc.model.User;

public interface LoginService {

	/**
	 * Authenticate User
	 * @param user
	 * @return boolean
	 */
	public boolean authenticate(User user);

	/**
	 * Is the current user session active?
	 * @param {@link HttpSession}
	 * @return boolean
	 */
	public boolean isActive(HttpSession session);

	/**
	 * Invalidates the session of current user and returns the invalidated flag. <br/>
	 * Return false if the user session was already invalidated.
	 * @param {@link HttpSession}
	 * @param boolean invalidated
	 */
	public boolean invalidateUser(HttpSession session);

	/**
	 * Get user
	 * @return {@link User}
	 */
	public User getUser();

	/**
	 * Get user, if not present search user in the session.
	 * @param {@link HttpSession}
	 * @return {@link User}
	 */
	public User getUser(HttpSession session);

	/**
	 * Setting user attributes to the ModelMap.
	 * @param {@link ModelMap}
	 */
	public void setUserAttributes(ModelMap model);

	/**
	 * Redirect to the given page along with setting user attributes to the ModelMap.
	 * @param {@link ModelMap}
	 * @param {@link String}
	 * @param {@link boolean}
	 * @return {@link RedirectView}
	 */
	public RedirectView toPageWithUserAttributes(ModelMap model, String page, boolean contextFlag);


	/**
	 * Redirect to the given page with same context along with setting user attributes to the ModelMap.
	 * @param {@link ModelMap}
	 * @param {@link String}
	 * @return {@link RedirectView}
	 */
	public RedirectView toPageWithUserAttributes(ModelMap model, String page);

}
