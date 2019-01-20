package blog;

import org.springframework.beans.factory.annotation.Autowired;

import dao.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mybatis.entity.BlogContent;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {

	private BlogContent blogContent;

	private Blog blog;

}
