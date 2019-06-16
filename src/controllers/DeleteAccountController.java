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
@WebServlet("/DeleteAccountController")
public class DeleteAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccountController() 
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
		
		System.out.println("DeleteAccountController.");
		
		HttpSession session = request.getSession();
		
		String nickname = request.getParameter("nickname");
		try {
			String query = "DELETE from users WHERE nickname = '"+ nickname+"';";
			db.executeUpdate(query);
			query = "DELETE from posts WHERE nickname = '"+ nickname+"';";
			db.executeUpdate(query);
			query = "DELETE from follows WHERE user_nickname = '"+ nickname+"';";
			db.executeUpdate(query);
			query = "DELETE from follows WHERE nickname = '"+ nickname+"';";
			db.executeUpdate(query);
			query = "DELETE from admins WHERE nickname = '"+ nickname+"';";
			db.executeUpdate(query);
		   RequestDispatcher dispatcher = request.getRequestDispatcher("HomeController?anonymous='no'");
		   dispatcher.forward(request, response);
		   
		} catch(Error e){
			
		} catch (SQLException e) {
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

