package sevlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.Student;
import service.StudentService;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	StudentService studentService;

	@Override
	public void init() throws ServletException {
//		studentService = CtxUtil.getBean(StudentService.class);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
		studentService = ctx.getBean(StudentService.class);
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("id");

		Student stu = studentService.getStudent(Integer.parseInt(user));

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.append("<!DOCTYPE html>").append("<html><head></head><body>").append("student是" + stu)
				.append("</body></html>");
	}

}
