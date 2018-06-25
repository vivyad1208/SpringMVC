package app.service;

import org.springframework.web.servlet.view.RedirectView;

public interface RedirectService {

	/**
	 * Redirect to the given page.
	 * @param {@link String}
	 * @param boolean
	 * @return {@link RedirectView}
	 */
	public RedirectView toPage(String page, boolean contextFlag);



	/**
	 * Redirect to the given page with same context.
	 * @param {@link String}
	 * @return {@link RedirectView}
	 */
	public RedirectView toPage(String page);
}
