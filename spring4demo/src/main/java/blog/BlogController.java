package blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mybatis.entity.BlogContent;

@Controller
@RequestMapping("/blogEdit")
public class BlogController {
	
	@Autowired 
	BlogContent blogContent;
	
	@GetMapping()
	public String init(ModelMap model) {
		blogContent.setTitle("test bean");
		model.addAttribute("content", blogContent);
		return "thymeleaf/blogEdit";
	}
	
	@PostMapping()
	public String create(@RequestParam("content") BlogContent blog, ModelMap model) {
		System.out.println(blog.getTitle());
		return "thymeleaf/blogEdit";
	}

}
