package blog;

import java.util.Date;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.Blog;
import mybatis.entity.BlogContent;

@Controller
@RequestMapping("/blogEdit")
public class BlogController {

	// @Autowired
	// Blog blog;

	@Autowired
	Logger logger;

	@GetMapping()
	public String init(BlogContent blog, ModelMap model) {
		blog.setTitle("test bean");
		// model.addAttribute("blog", blog);
		return "thymeleaf/blogEdit";
	}

	@PostMapping()
	public String create(@ModelAttribute BlogContent blog, ModelMap model) {
		System.out.println(blog.getTitle());
		return "thymeleaf/blogEdit";
	}

	// @PostMapping()
	// public String create(@RequestParam String title, @RequestParam("content")
	// String con, ModelMap model) {
	// logger.warn(title + con);
	// return "thymeleaf/blogEdit";
	// }

}
