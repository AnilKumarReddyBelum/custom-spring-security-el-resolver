package com.javasampleapproach.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javasampleapproach.h2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.userName = :userName")
	User findByUserName(@Param("userName") String userName);

}
