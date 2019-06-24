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
@WebServlet("/AddLikeController")
public class AddLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddLikeController() 
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
		
		System.out.println("AddLikeController.");
		
		HttpSession session = request.getSession();
		String nickname = (String) session.getAttribute("nickname");
		String post_id = request.getParameter("post_id");
		
		try {
		   int num = 0;
		   ResultSet result;
		   result = db.executeSQL("SELECT * FROM likes where post_id='"+ post_id +"' AND nickname='"+ nickname +"';");
		   if(result.next()){
			   String query = "DELETE from likes where nickname='"+nickname+"' and post_id='"+post_id+"' ;";
			   db.executeUpdate(query);
			   result = db.executeSQL("SELECT COUNT(*) FROM likes where post_id='"+ post_id +"';");
			   if(result.next()){
					num = result.getInt(1);
			   }
			   session.setAttribute("numLikes", num);
			   RequestDispatcher dispatcher = request.getRequestDispatcher("Dislike.jsp");
			   dispatcher.forward(request, response);
		   } else {
			   String query = "INSERT INTO likes (nickname, post_id) VALUES ('"+ nickname +"','"+ post_id +"');";
			   db.executeUpdate(query);
			   result = db.executeSQL("SELECT COUNT(*) FROM likes where post_id='"+ post_id +"';");
			   if(result.next()){
					num = result.getInt(1);
			   }
			   session.setAttribute("numLikes", num);
			   RequestDispatcher dispatcher = request.getRequestDispatcher("Like.jsp");
			   dispatcher.forward(request, response);   
		   }
		   
		   
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

