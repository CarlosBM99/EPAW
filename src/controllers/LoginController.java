package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import models.BeanLogin;
import models.BeanUser;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() 
    {
        super();
        
        try
        {
			db = new DAO();
		} catch (Exception e1)
        {
			e1.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		System.out.println("LoginController.");
		
		HttpSession session = request.getSession();
		BeanLogin login = new BeanLogin();
		
	    try
	    {
			BeanUtils.populate(login, request.getParameterMap());
			login.resetError();
			
			if (login.isComplete())
	    	{
	    		System.out.println("Log-In isComplete");
	    		
	    		ResultSet result = db.executeSQL("SELECT * FROM users WHERE nickname LIKE '" + login.getNickname() + "' AND hashedPassword LIKE SHA2('" + login.getPassword() + "',256);");
	    		
	    		if(result.next())
	    		{
	    			BeanUser user = new BeanUser();
	    			user.setFirstname(result.getString("first_name"));
	    			user.setNickname(result.getString("nickname"));
	    			user.setLastname(result.getString("last_name"));
	    			user.setMail(result.getString("email"));
	    			session.setAttribute("nickname", result.getString("nickname"));
			    	session.setAttribute("user", user);
			    	request.setAttribute("user", user);
			    	RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginDone.jsp");
				    dispatcher.forward(request, response);
	    		}
	    		else
	    		{
	    			login.setError(1);
					login.setPassword("");
					request.setAttribute("login", login);
					RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
				    dispatcher.forward(request, response);
	    		}
		    } 
			else
			{
				login.setError(2);
				request.setAttribute("login",login);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
			    dispatcher.forward(request, response);
		    }
		}
	    catch (IllegalAccessException | InvocationTargetException | SQLException e)
	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

