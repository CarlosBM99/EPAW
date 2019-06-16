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
@WebServlet("/SaveUserAdminPostController")
public class SaveUserAdminPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DAO db;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveUserAdminPostController() 
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
		
		System.out.println("SaveUserAdminPostController.");
		
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		String text = request.getParameter("text");
		
		System.out.println(id + text);
		
		try {
		   
		   String query = "UPDATE posts SET text = '" + text +"' WHERE id = "+id+";";
		   db.executeUpdate(query);
		   query = "SELECT * from posts WHERE id = "+id+";";
		   ResultSet r = db.executeSQL(query);
		   if(r.next()){
			   String nickname = r.getString("nickname");
			   System.out.println("nick: "+ nickname);
			   RequestDispatcher dispatcher = request.getRequestDispatcher("ShowUserAdminProfileController?nickname="+ nickname);
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

