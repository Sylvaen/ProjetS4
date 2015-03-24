package servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.DAOUser;

@WebServlet("/valide")
public class Valide extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static final String VUE = "/WEB-INF/Valide.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getParameter("name")!=null || req.getParameter("key")!=null){
			User user = DAOUser.getUserByUsername(req.getParameter("name"));
			if(user != null){
			    if(user.getKey().equals("")){
			        resp.sendRedirect("index"); 
			    }
			       
			    else{
			        System.out.println("getkey: "+user.getKey()+" key:"+req.getParameter("key"));
	                if(user.getKey().equals(req.getParameter("key"))){
	                    user.setValide(1);
	                    DAOUser.validerCompte(user);
	                    req.setAttribute("isValide", 1);
	                    this.getServletContext().getRequestDispatcher(VUE)
	                    .forward(req, resp);
	                }
	                else{
	                    resp.sendRedirect("inscription");
	                } 
			    }
				
			}
			else
			resp.sendRedirect("guide");

		}
		else{
			resp.sendRedirect("jouer");
		}
		
		
	}
}
