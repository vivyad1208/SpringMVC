package app.mvc.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -5855080760603065213L;

	private int userId;
	private String userName;
	private transient String password;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void deletePassword() {
		password = null;
	}
}
