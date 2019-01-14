package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")
public class HelloController {
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String printHello(ModelMap model) {
		model.addAttribute("message", "Spring MVC Framework by thymeleaf!");
		return "thymeleaf/hello";
	}

	@RequestMapping(value = "/jsp", method = RequestMethod.GET)
	public String printHelloJsp(ModelMap model) {
		model.addAttribute("message", "Spring MVC Framework by jsp!");
		return "jsp/hello";
	}

	@RequestMapping(value = "/calc/{a}/{b}", method = { RequestMethod.GET })
	public String calc(@PathVariable int a, @PathVariable int b, ModelMap model) {
		model.addAttribute("message", "sum is " + (a + b));
		return "thymeleaf/hello";
	}
}
