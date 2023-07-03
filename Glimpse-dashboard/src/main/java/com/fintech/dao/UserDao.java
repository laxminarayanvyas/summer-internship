package com.fintech.dao;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fintech.beans.Users;

public interface UserDao extends JpaRepository<Users, Integer> {
	
	@Query(value="SELECT UserID,UserName FROM users WHERE is_active=1 AND UserName<>'dummy_user'", nativeQuery = true)
	Set<Users> getAllUsers();
}
