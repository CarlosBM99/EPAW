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
@WebServlet("/PostController")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostController() 
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
		System.out.println("PostController.");
		HttpSession session = request.getSession();
		BeanPost post = new BeanPost();
		
	    try
	    {
	    	BeanUtils.populate(post, request.getParameterMap());
			post.resetError();
			post.setNickname(session.getAttribute("nickname").toString());
			if (post.isComplete())
	    	{
	    		System.out.println("Post is complete");
	    		int result = db.executeUpdate("INSERT INTO posts (nickname,text,likes) VALUES('"+ post.getNickname() +"','"+ post.getText() +"','"+ 0 +"');");
	    		//ResultSet result = db.executeSQL("SELECT * FROM users WHERE nickname LIKE '" + login.getNickname() + "' AND hashedPassword LIKE SHA2('" + login.getPassword() + "',256);");
	    		System.out.println("result: " + result);
	    		if(result == 1){
	    			post.setText("");
					request.setAttribute("post", post);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("HomeController?anonymous='no'");
					dispatcher.forward(request, response);
	    		} else {
	    			post.setText("");
					request.setAttribute("post", post);
				    RequestDispatcher dispatcher = request.getRequestDispatcher("HomeController?anonymous='no'");
					dispatcher.forward(request, response);
	    		}
	    		
		    } 
			else
			{
				System.out.println("Post is not complete");
				post.setError(2);
				request.setAttribute("post",post);
			    RequestDispatcher dispatcher = request.getRequestDispatcher("/HomeController");
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

