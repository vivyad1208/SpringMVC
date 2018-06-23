package app.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorMvcController {

	@RequestMapping
	public ModelAndView getError() {
		ModelAndView model = new ModelAndView("errorMvc");
		model.addObject("msg", "Error 404: Page Not Found!");
		return model;
	}

}
