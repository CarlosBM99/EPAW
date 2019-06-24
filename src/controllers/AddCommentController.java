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
@WebServlet("/AddCommentController")
public class AddCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentController() 
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
		
		System.out.println("AddCommentController.");
		
		HttpSession session = request.getSession();
		String nickname = (String) session.getAttribute("nickname");
		String post_id = request.getParameter("post_id");
		String text = request.getParameter("text");
		
		try {
			System.out.println(text);
			   String query = "INSERT INTO comments (text,nickname, post_id) VALUES ('"+ text +"','"+ nickname +"','"+ post_id +"');";
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

