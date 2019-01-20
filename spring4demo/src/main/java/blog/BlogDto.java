package blog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

import dao.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybatis.entity.BlogContent;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class BlogDto {

	@NotNull
	@Size(min=1, message="个数必须大于{min}")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@NotNull
	private String content;

}
