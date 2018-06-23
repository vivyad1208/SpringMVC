package app.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/test")
public class WelcomeRestController {

	@RequestMapping("")
	public String testapi() {
		return "Test API!";
	}

	@RequestMapping("/helloworld")
	public String helloworld() {
		return "Test API Helloworld !";
	}
}
