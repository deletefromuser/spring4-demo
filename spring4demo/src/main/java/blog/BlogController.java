package blog;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.dozermapper.core.Mapper;

import dao.Blog;
import mybatis.entity.BlogContent;
import mybatis.mapper.BlogContentMapper;

@Controller
@RequestMapping("/blogEdit")
public class BlogController {

	// @Autowired
	// Blog blog;

	@Autowired
	Logger logger;
	
	@Autowired
	Mapper mapper;

	@Autowired
	BlogContentMapper blogContentMapper;
	
	@Autowired
	BlogDto sessionScopeTest;

	// @Autowired
	// Blog item;

	// @GetMapping()
	// public String init(BlogContent blogContent, Blog item, ModelMap model) {
	// blogContent.setTitle("test bean");
	// item.setContent("holy shit");
	// return "thymeleaf/blogEdit";
	// }
	//
	// @PostMapping()
	// public String create(BlogContent blogContent, Blog blog, ModelMap model)
	// {
	// logger.warn(blogContent.getTitle());
	// logger.warn(blogContent.getContent());
	// logger.warn(blog.getTitle());
	// logger.warn(blog.getContent());
	//
	// int id = blogContentMapper.insertSelective(blogContent);
	// model.addAttribute("newid", id);
	// return "thymeleaf/blogEdit";
	// }

	@GetMapping()
	public String init(BlogDto blogContent, ModelMap model) {
//		blogContent.getBlogContent().setTitle("test bean");
//		blogContent.getBlog().setContent("holy shit");
		sessionScopeTest.setTitle("sessionTest1");
		return "thymeleaf/blogEdit";
	}

	@PostMapping()
	public String create(@Valid BlogDto blogDto, BindingResult bindingResult,ModelMap model) {
		logger.warn(blogDto.toString());
		
		if(bindingResult.hasErrors()) {
			return "thymeleaf/blogEdit";
		}
		
		BlogContent newBlog = mapper.map(blogDto, BlogContent.class);

		blogContentMapper.insertSelective(newBlog);
		model.addAttribute("newid", newBlog.getId());
		sessionScopeTest.setTitle("sessionTest2");
		return "thymeleaf/blogEdit";
	}

}
