package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.BeanPost;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/MyProfileController")
public class MyProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfileController() {
        super();
        
        try
        {
			db = new DAO();
		} catch (Exception e1)
        {
			e1.printStackTrace();
		}
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("MyProfileController.");
		HttpSession session = request.getSession();
		String nickname = session.getAttribute("nickname").toString();
		request.setAttribute("nickname", nickname);
		try {
			ResultSet result = db.executeSQL("SELECT * FROM users WHERE nickname = '"+ nickname +"'");
			if(result.next()){
				request.setAttribute("first_name", result.getString("first_name"));
				request.setAttribute("last_name", result.getString("last_name"));
				request.setAttribute("email", result.getString("email"));
				int count = -1;
		    	ResultSet result1 = db.executeSQL("SELECT COUNT(*) FROM posts WHERE nickname = '" + nickname + "';" );
		    	if(result1.next()){
		    		count = result1.getInt(1);
		    		//System.out.println("count: "+count);
		    	}
		    	ResultSet result2 = db.executeSQL("SELECT * FROM posts WHERE nickname = '" + nickname + "';");
		    	BeanPost[] posts = new BeanPost[count];
		    	int i = 0;
	    		while(result2.next())
	    		{
	    			posts[i] = new BeanPost(result2.getInt("id"),result2.getString("nickname"),result2.getString("text"),result2.getInt("likes"));
	    			i++;
	    		}
	    		request.setAttribute("posts", posts);
	    		request.setAttribute("posts_size", posts.length);
				RequestDispatcher dispatcher = request.getRequestDispatcher("MyProfile.jsp");
			    dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

