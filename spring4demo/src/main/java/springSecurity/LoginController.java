package springSecurity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/admin")
public class LoginController {
	@RequestMapping(value = "/admin/{page}", method = RequestMethod.GET)
	public String dispatch(@PathVariable String page, ModelMap model) {
		model.addAttribute("message", "logining");
		return "thymeleaf/admin/" + page;
	}

//	@RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
//	public String login(ModelMap model) {
//		model.addAttribute("message", "logining");
//		return "thymeleaf/login";
//	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@RequestParam(value = "error", required = false) String error, ModelMap mode) {
		if (error != null) {
			mode.addAttribute("message", "用户名或密码错误");
		}
		return "thymeleaf/login";
	}

	@RequestMapping(value = "/logouted", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(ModelMap model) {
		model.addAttribute("message", "logouted");
		return "thymeleaf/logout";
	}
}
