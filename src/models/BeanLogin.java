package models;

public class BeanLogin {

	private String nickname = "";
	private String password = "";

	/*  Control which parameters have been correctly filled */
	// ERROR 0: ok
	// ERROR 1: Empty nickname or password
	// ERROR 2: Wrong nickname or password

	private int error = 0;

	public String getNickname(){
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public int getError() {
		return error;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public void setPassword(String password) {
		this.password = password;
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
		if(!this.hasValue(getPassword())) {
			this.setError(1);
		}
	    return(this.checkError());
	}

	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}


}
