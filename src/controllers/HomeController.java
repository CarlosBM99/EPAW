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
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() 
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
		System.out.println("HomeController.");
		HttpSession session = request.getSession();
	    try
	    {
	    	session.setAttribute("anonymous","no");
	    	if(session.getAttribute("user") != null || request.getParameter("anonymous").equals("yes")){
		    	int count = -1;
		    	ResultSet result1 = db.executeSQL("SELECT COUNT(*) FROM posts");
		    	if(result1.next()){
		    		count = result1.getInt(1);
		    		//System.out.println("count: "+count);
		    	}
		    	ResultSet result = db.executeSQL("SELECT * FROM posts");
		    	BeanPost[] posts = new BeanPost[count];
		    	int i = 0;
	    		while(result.next())
	    		{
	    			posts[i] = new BeanPost(result.getInt("id"),result.getString("nickname"),result.getString("text"),result.getInt("likes"));
	    			//System.out.println(result.getString("text"));
	    			i++;
	    			//session.setAttribute("posts", result);
				    //dispatcher.forward(request, response);
	    		}
	    		request.setAttribute("posts", posts);
	    		request.setAttribute("posts_size", posts.length);
	    		if(request.getParameter("anonymous").equals("yes")){
	    			session.setAttribute("anonymous","yes");
	    		}
	    		RequestDispatcher dispatcher = request.getRequestDispatcher("HomeScreen.jsp");
	    		dispatcher.forward(request, response);
	    	} else {
	    		session.setAttribute("anonymous",null);
	    		RequestDispatcher dispatcher = request.getRequestDispatcher("ViewStart.jsp");
	    		dispatcher.forward(request, response);
	    	}
		}
	    catch (SQLException e)
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

