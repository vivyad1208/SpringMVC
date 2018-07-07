package app.mvc.repository;

import static query.QUser.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import app.mvc.dto.User;

@Repository
@Transactional(readOnly = true)
public class LoginDaoImpl implements LoginDao {

	@PersistenceContext
    EntityManager entityManager;


	@Override @SuppressWarnings("unchecked")
	public List<User> getUsers() {
		String strQuery = "SELECT u.* FROM users u WHERE u.deleteFlag=0";
		Query query = entityManager.createNativeQuery(strQuery, User.class);
		return query.getResultList();
	}


	@Override
	public User getUser(String username) {
		String strQuery = "SELECT u.* FROM users u WHERE u.username=:username AND u.deleteFlag=0";
		Query query = entityManager.createNativeQuery(strQuery, User.class);
		query.setParameter("username", username);
		Object obj = query.getSingleResult();
		return (User) obj;
	}


	@Override
	public User authenticateUser(String username, String password) {
		String strQuery = FETCH_BY_USERNAME_AND_PASSWORD.query();
		Query query = entityManager.createNamedQuery(strQuery);
		query.setParameter(FETCH_BY_USERNAME_AND_PASSWORD_$1.query(), username);
		query.setParameter(FETCH_BY_USERNAME_AND_PASSWORD_$2.query(), password);
		Object obj = query.getSingleResult();
		return (User) obj;
	}

}
