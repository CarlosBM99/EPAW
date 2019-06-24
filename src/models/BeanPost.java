package models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import controllers.DAO;

public class BeanPost implements Serializable  {

	private static final long serialVersionUID = 1L;
	private DAO dao;
	
	private int id = 0;
	private String text = "";
	private String nickname = "";
	private int numLikes = 0;
	private int numComments = 0;
	private List<String> nick_likes =  new LinkedList<String>();
	private List<Comment> comments =  new LinkedList<Comment>();
	int error = 0;
	
	public BeanPost(int id, String nickname, String text){
		this.id = id;
		this.nickname = nickname;
		this.text = text;
	}
	
	public BeanPost(){
		
	};
	
	/* Getters */
	public String getText() {
		return text;
	}
	public String getNickname() {
		return nickname;
	}
	
	public int getId() {
		return id;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	public int getNumComments(){
		int num = 0;
		try {
			dao = new DAO();
			ResultSet result = dao.executeSQL("SELECT COUNT(*) FROM comments where post_id='"+id+"';");
			if(result.next()){
				num = result.getInt(1);
				setNumComments(result.getInt(1));
				return num;
			} else {
				num = 0;
				setNumComments(0);
				return num;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return 0;
	}
	
	public int getNumLikes(){
		int num = 0;
		try {
			dao = new DAO();
			ResultSet result = dao.executeSQL("SELECT COUNT(*) FROM likes where post_id='"+id+"';");
			if(result.next()){
				num = result.getInt(1);
				setNumLikes(result.getInt(1));
				return num;
			} else {
				num = 0;
				setNumLikes(0);
				return num;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return 0;
	}
	
	public List<String> setNickLikes(){
		nick_likes.clear();
		try {
			dao = new DAO();
			ResultSet result = dao.executeSQL("SELECT * FROM likes where post_id='"+id+"';");
			while(result.next()){
				nick_likes.add(result.getString("nickname"));
			}
			return nick_likes;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return nick_likes;
	}
	
	public List<Comment> setComments(){
		comments.clear();
		try {
			dao = new DAO();
			ResultSet result = dao.executeSQL("SELECT * FROM comments where post_id='"+id+"';");
			while(result.next()){
				Comment c = new Comment(result.getString("nickname"),result.getString("text"));
				comments.add(c);
			}
			return comments;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return comments;
	}
	
	public List<String> getNickLikes(){
		return nick_likes;
	}
	
	/*Setters*/
	public void setText(String text){
		this.text = text;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setNumLikes(int numlikes){
		this.numLikes = numlikes;
	}
	
	public void setNumComments(int numcomments){
		this.numComments = numcomments;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setError(int value) {
		this.error=value;
	}
	
	public boolean checkError() {
		if(error!=0)
			return false;
		return true;
	}

	public void resetError() {
		error = 0;
	}
	
	public boolean isComplete() {
		if(!this.hasValue(getNickname())) {
			this.setError(1);
		}
		if(!this.hasValue(getText())) {
			this.setError(1);
		}
	    return(this.checkError());
	}

	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
}