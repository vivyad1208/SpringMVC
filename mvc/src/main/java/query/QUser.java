package query;

public enum QUser {

	FETCH_BY_USERNAME("User.fetchByUserName"),
	FETCH_BY_USERNAME_$1("username"),

	FETCH_BY_USERNAME_AND_PASSWORD("User.fetchByUserNameAndPassword"),
	FETCH_BY_USERNAME_AND_PASSWORD_$1("username"),
	FETCH_BY_USERNAME_AND_PASSWORD_$2("userpwd");

	String query;

	QUser(String q){
		query = q;
	}

	public String query() {
		return query;
	}
}
