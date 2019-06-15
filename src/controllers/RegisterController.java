package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import models.BeanUser;

/**
 * Servlet implementation class FormController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        try {
			dao = new DAO();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("RegisterController.");
		
		HttpSession session = request.getSession();
		BeanUser user = new BeanUser();
		
		try {

		   BeanUtils.populate(user, request.getParameterMap());
		   
		   user.resetError();
		   if (user.isComplete()) {
			   
				   if((dao.executeSQL("SELECT nickname FROM users WHERE nickname LIKE '"+user.getNickname()+"';")).next()){
					   user.setError(2,2);   
				   }
				   
				   if((dao.executeSQL("SELECT email FROM users WHERE email LIKE '"+user.getMail()+"';")).next()){
					   user.setError(3,2);
				   }
				   
				   if(!user.checkPassword())
					   user.setError(4, 2);
				   
				   if(user.checkError()){
					   try {
						   String query = "INSERT INTO users(first_name, last_name, nickname, email, hashedPassword) VALUES ('" + user.getFirstname() +"', '" + user.getLastname() +"', '" + user.getNickname() +"', '"+user.getMail()+"',SHA2('" + user.getPassword() + "',256));";
						   dao.executeUpdate(query);
						   request.setAttribute("user", user);
						   session.setAttribute("user", user);
						   session.setAttribute("nickname", user.getNickname());
						   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewRegisterDone.jsp");
						   dispatcher.forward(request, response);
					   } catch (SQLException e) {
						   e.printStackTrace();  
					   }
				   }else {
					   user.setPassword("");
					   user.setRepassword("");
					   request.setAttribute("user", user);
					   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewRegisterForm.jsp");
					   dispatcher.forward(request, response);
				   }
		   }else {
			   request.getSession().removeAttribute("user");
			   user.setPassword("");
			   user.setRepassword("");
			   request.setAttribute("user", user);
			   RequestDispatcher dispatcher = request.getRequestDispatcher("ViewRegisterForm.jsp");
			   dispatcher.forward(request, response); 
		   }
	    } catch (IllegalAccessException | InvocationTargetException | SQLException e) {
				e.printStackTrace();
	    }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}