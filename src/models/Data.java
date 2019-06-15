package models;

import java.io.Serializable;

public class Data implements Serializable  {

	private static final long serialVersionUID = 1L;

	private String firstname = "";
	private String lastname = "";
	private String nickname = ""; 
	private String mail = ""; 
	
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
	public String getMail() {
		return mail;
	}

	/*Setters*/
	public void setFirstname(String firstname){
		this.firstname = firstname;
	}
	public void setLastname(String lastname){
		this.lastname = lastname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}