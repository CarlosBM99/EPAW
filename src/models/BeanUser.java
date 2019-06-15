package models;

import java.io.Serializable;

public class BeanUser implements Serializable  {

	private static final long serialVersionUID = 1L;

	
	private String firstname = "";
	private String lastname = "";
	private String nickname = "";
	private String mail = "";
	private String password = "";
	private String repassword = "";
	private int isAdmin = 0;
	
	/*  Control which parameters have been correctly filled */
	// ERROR 0: ok
	// ERROR 1: incompleto
	// ERROR 2: encontrado en BD/password do not match
	// ERROR 3: carácteres ilegales
	
	private int[] error = {0,0,0,0,0,0,0}; 
	
	public BeanUser(String nickname, String firstname, String lastname){
		this.nickname = nickname;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public BeanUser(){
		
	};
	
	/* Getters */
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public String getRepassword() {
		return repassword;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	
	public String getMail() {
		return mail;
	}
	
	public int[] getError() {
		return error;
	}
	
	
	/*Setters*/
	public void setFirstname(String firstname){
		this.firstname = firstname;
	}
	public void setLastname(String lastname){
		this.lastname = lastname;
	}
	public void setMail(String mail){
		this.mail = mail;
	}
	
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public void setRepassword(String confirmation) {
		this.repassword = confirmation;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setError(int pos, int value) {
		this.error[pos] = value;
	}

	/* Logic Functions */
	public boolean checkError() {
		int[] array = this.getError();
		for (int e : array)
		      if (e != 0){
		         return false;
		      }
		return true;
	}
	
	public boolean checkPassword() {
		if(password.equals(repassword))
			return true;
		return false;
	}
	
	public void resetError() {
		error = new int[]{0,0,0,0,0,0,0};
	}
	
	/*Check if all the fields are filled correctly */
	public boolean isComplete() {
		if(!this.hasValue(getNickname())){
			this.setError(2, 1);
		}	
		else if(this.hasWhitespaces(nickname)){
			this.setError(2, 3);
		}
		
		if(!this.hasValue(getMail())){
			this.setError(3, 1);
		}
		else if(this.hasWhitespaces(mail)){
			this.setError(3, 3);	
		}
		
		if(!this.hasValue(getPassword())){
			this.setError(4, 1);
		}
		else if(this.hasWhitespaces(password)){
			this.setError(4, 3);
		}
		
		if(!this.hasValue(getFirstname())){
			this.setError(0, 1);
		}
		
		if(!this.hasValue(getLastname())){
			this.setError(1, 1);
		}
		
		if(!this.hasValue(getRepassword())){
			this.setError(5, 1);
		}
		
	    return(this.checkError());
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
	private boolean hasWhitespaces(String str) {
		return str.replaceAll("\\s","").length() != str.length();
	}
}