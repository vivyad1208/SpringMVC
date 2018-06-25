package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WeclomeMvcController {

	@RequestMapping("/")
	public ModelAndView getWelcome() {
		ModelAndView model = new ModelAndView("welcomeMvc");
		model.addObject("msg", "Welcome!");
		return model;
	}

	@RequestMapping("/modelmap")
	public String getWelcome(ModelMap model) {
		model.addAttribute("msg", "Using Model Map Attribute!");
		return "welcomeMvc";
	}

}
