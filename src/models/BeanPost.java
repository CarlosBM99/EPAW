package models;

import java.io.Serializable;

public class BeanPost implements Serializable  {

	private static final long serialVersionUID = 1L;

	private int id = 0;
	private String text = "";
	private String nickname = "";
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
	
	/*Setters*/
	public void setText(String text){
		this.text = text;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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