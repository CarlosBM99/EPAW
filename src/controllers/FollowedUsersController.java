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
@WebServlet("/FollowedUsersController")
public class FollowedUsersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO db;
    private DAO db2;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowedUsersController() 
    {
        super();
        
        try
        {
			db = new DAO();
			db2 = new DAO();
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
		System.out.println("FollowedUsersController.");
		HttpSession session = request.getSession();
	    try
	    {
	    	int count = 0;
	    	ResultSet result = db.executeSQL("SELECT COUNT(*) FROM follows where nickname='"+ session.getAttribute("nickname") +"';");
	    	if(result.next()){
	    		count += result.getInt(1);
    		}
	    	result = db.executeSQL("SELECT * FROM follows where nickname='"+ session.getAttribute("nickname") +"';");
	    	int i = 0;
	    	BeanUser[] users = new BeanUser[count];
	    	while(result.next()){
	    			ResultSet result2 = db2.executeSQL("SELECT * FROM users where nickname='"+ result.getString("user_nickname") +"';");
	    			if(result2.next()){
	    				users[i] = new BeanUser(result2.getString("nickname"),result2.getString("first_name"),result2.getString("last_name"));
	    				i++;
	    			}
	    	}
	    	request.setAttribute("users", users);
    		request.setAttribute("users_size", users.length);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("FollowedUsers.jsp");
    		dispatcher.forward(request, response);
	    	/*int count = 0;
	    	ResultSet result = db.executeSQL("SELECT * FROM follows where nickname='"+ session.getAttribute("nickname") +"';");
	    	while(result.next()){
	    		ResultSet result2 = db2.executeSQL("SELECT COUNT(*) FROM posts where nickname='"+ result.getString("user_nickname") +"';");
	    		if(result2.next()){
		    		count += result2.getInt(1);
	    		}
	    	}
	    	result = db.executeSQL("SELECT * FROM follows where nickname='"+ session.getAttribute("nickname") +"';");
	    	BeanPost[] posts = new BeanPost[count];
	    	int i = 0;
	    	while(result.next() && i<count){
	    		ResultSet result2 = db2.executeSQL("SELECT * FROM posts where nickname='"+ result.getString("user_nickname") +"';");
	    		while(result2.next()){
	    			posts[i] = new BeanPost(result2.getInt("id"),result2.getString("nickname"),result2.getString("text"),result2.getInt("likes"));
	    			i++;
	    		}
	    	}
	    	request.setAttribute("posts", posts);
    		request.setAttribute("posts_size", posts.length);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("PersonalizedTimeline.jsp");
    		dispatcher.forward(request, response);*/
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

