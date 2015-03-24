package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index")
public class Index extends HttpServlet {

	private static final String VIEW = "/WEB-INF/index.jsp";
	private static final long serialVersionUID = 1L;
	public static final String ATT_USER = "user";
	public static final String ATT_FORM = "form";

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		// Transmission de la paire d'objets request/response à notre JSP
		this.getServletContext().getRequestDispatcher(VIEW)
				.forward(request, response);
	}
}