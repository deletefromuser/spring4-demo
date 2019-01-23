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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.dozermapper.core.Mapper;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;

import bean.User;
import dao.Blog;
import mybatis.entity.BlogContent;
import mybatis.entity.BlogContentExample;
import mybatis.entity.Users;
import mybatis.mapper.BlogContentMapper;

@Controller
@RequestMapping("/blog")
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
	Parser markdownParser;
	
	@Autowired
	HtmlRenderer markdownRender;

	@GetMapping("/new")
	public String init(BlogDto blogContent, ModelMap model) {
		return "thymeleaf/blog/blogEdit";
	}

	@PostMapping("/create")
	public String create(@Valid BlogDto blogDto, BindingResult bindingResult, ModelMap model) {
		logger.warn(blogDto.toString());

		if (bindingResult.hasErrors()) {
			return "thymeleaf/blog/blogEdit";
		}

		BlogContent newBlog = mapper.map(blogDto, BlogContent.class);

		blogContentMapper.insertSelective(newBlog);
		// model.addAttribute("newid", newBlog.getId());
		return "redirect:/blog/list";
	}

	@GetMapping("/list")
	public String list(ModelMap model) {
		model.addAttribute("blogList", blogContentMapper.selectByExample(new BlogContentExample()));
		return "thymeleaf/blog/blogList";
	}

	@GetMapping("/{id}")
	public String view(@PathVariable Long id, ModelMap model) {
		BlogContent bc = blogContentMapper.selectByPrimaryKey(id);
		model.addAttribute("blog", bc);
		model.addAttribute("content", markdownRender.render(markdownParser.parse(bc.getContent())));
		return "thymeleaf/blog/blog";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Long id, ModelMap model) {
		model.addAttribute("blog", blogContentMapper.deleteByPrimaryKey(id));
		return "redirect:/blog/list";
	}

}
