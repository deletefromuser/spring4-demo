package springSecurity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class LoginController {
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	public String login(@PathVariable String page, ModelMap model) {
		model.addAttribute("message", "logining");
		return "thymeleaf/admin/" + page;
	}
}
