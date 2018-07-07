package app.mvc.repository;

import app.mvc.dto.User;

public interface LoginDao {

	java.util.List<User> getUsers();

	 User getUser(String username);

	 User authenticateUser(String username, String password);
}
