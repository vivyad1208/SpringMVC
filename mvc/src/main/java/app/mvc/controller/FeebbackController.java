package app.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import app.mvc.model.Feedback;
import app.mvc.model.User;
import constants.PAGES;

@Controller
public class FeebbackController {

	@RequestMapping("/feedback")
	public ModelAndView toFeedbackPage() {
		return new ModelAndView(PAGES.FEEDBACK.getPage(), "fb", new Feedback());
	}

	@RequestMapping("/feedbackregister")
	public String feedbackRegister(@ModelAttribute Feedback feedback) {
		System.out.println(feedback);
		return PAGES.FEEDBACK.getPage();
	}

}
