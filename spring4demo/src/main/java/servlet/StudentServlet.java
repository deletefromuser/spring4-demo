package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Student;
import service.StudentService;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
//	@Resource
	StudentService studentService;

	@Override
	public void init() throws ServletException {
//		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//		studentService = ctx.getBean(StudentService.class);
		// -----------------------------------------
		studentService = CtxUtil.getBean(StudentService.class);
		// -----------------------------------------
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
//		studentService = ctx.getBean(StudentService.class);
		super.init();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("id");

		Student stu = studentService.getStudent(Integer.parseInt(user));

//		resp.setContentType("text/html");
//		resp.setCharacterEncoding("UTF-8");
//		PrintWriter writer = resp.getWriter();
//		writer.append("<!DOCTYPE html>").append("<html><head></head><body>").append("student是" + stu)
//				.append("</body></html>");

		req.setAttribute("student", stu);
		req.getRequestDispatcher("/student.jsp").forward(req, resp);

	}

}
