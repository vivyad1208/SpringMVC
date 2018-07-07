package app.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.mvc.dto.User;

@Repository
public interface  LoginRepository extends JpaRepository<User,Integer>, LoginDao, QueryDslPredicateExecutor<User> {

	List<User> fetchByUserName(@Param("username") String username);

}
