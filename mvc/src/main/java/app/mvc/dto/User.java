package app.mvc.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import util.StringUtil;

@Entity
@Table(name="users", schema="pnb")
@NamedQueries ({
	@NamedQuery( name = "User.fetchByUserName", query = "SELECT u FROM User u WHERE u.username=:username" ),
	@NamedQuery( name = "User.fetchByUserNameAndPassword", query = "SELECT u FROM User u WHERE u.username=:username AND u.userpwd=:userpwd" )
})
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String username;
	String userpwd;
	int deleteflag;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public int getDeleteflag() {
		return deleteflag;
	}
	public void setDeleteflag(int deleteflag) {
		this.deleteflag = deleteflag;
	}

	public String toString() {
		return StringUtil.joins("UserId:",id,  " || ",  "Username:",username);
	}
}
