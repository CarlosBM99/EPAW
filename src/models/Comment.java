package models;

import controllers.DAO;

public class Comment {
    String nickname;
    String text;

    Comment(String nickname, String text) {
        this.nickname = nickname;
        this.text = text;
    }
    
    public String getNickname(){
    	return nickname;
    }
    
    public String getText(){
    	return text;
    }
}