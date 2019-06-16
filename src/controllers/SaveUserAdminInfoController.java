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
import models.BeanPost;
import models.BeanUser;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/SaveUserAdminInfoController")
public class SaveUserAdminInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveUserAdminInfoController() 
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
		
		System.out.println("SaveUserAdminInfoController.");
		
		HttpSession session = request.getSession();
		System.out.println("first: " + request.getParameter("first_name") + " last: " + request.getParameter("last_name") + " nick: " +request.getParameter("nickname"));
		try {
		   
		   String query = "UPDATE users SET first_name = '" + request.getParameter("first_name") +"', last_name = '" + request.getParameter("last_name") +"' WHERE nickname = '"+ request.getParameter("nickname")+"';";
		   db.executeUpdate(query);
		   request.setAttribute("first_name", request.getParameter("first_name"));
		   request.setAttribute("last_name", request.getParameter("last_name"));
		   RequestDispatcher dispatcher = request.getRequestDispatcher("ShowUserAdminProfileController");
		   dispatcher.forward(request, response);
		   
		} catch(Error e){
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
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

