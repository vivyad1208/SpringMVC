package app.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.mvc.model.Feedback;
import constants.PAGES;

@Controller
public class FeebbackController {

	@RequestMapping("/feedback")
	public ModelAndView toFeedbackPage() {
		return new ModelAndView(PAGES.FEEDBACK.getPage(), "command", new Feedback());
	}

	@RequestMapping("/feedbackregister")
	public String feedbackRegister() {
		return PAGES.FEEDBACK.getPage();
	}

}
