package sevlet;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String act = request.getParameter("act");

		if (act == null || act.equals("")) {
			act = "execute";
		}

		Method method;
		try {
			method = this.getClass().getMethod(act, HttpServletRequest.class, HttpServletResponse.class);
			String targetUri = method.invoke(this, request, response) + "";
			if (targetUri.startsWith("redirect:")) {
				response.sendRedirect(targetUri.substring(9, targetUri.length()));
			} else {
				request.getRequestDispatcher(targetUri).forward(request, response);
			}
		} catch (Exception e) {
			response.sendError(400, e.getMessage());
			e.printStackTrace();
		}
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendError(400, "请使用参数act指定您要访问的方法");
	}
}