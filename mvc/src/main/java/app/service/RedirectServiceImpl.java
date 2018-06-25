package app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@Service
public class RedirectServiceImpl implements RedirectService {

	@Override
	public RedirectView toPage(String page, boolean contextFlag) {
		RedirectView redirect = new RedirectView();
		redirect.setContextRelative(contextFlag);
        redirect.setExposeModelAttributes(false);	// Remove request parameters from the URL.
		redirect.setUrl(page);
		return redirect;
	}

	@Override
	public RedirectView toPage(String page) {
		return toPage(page, true);
	}

}
