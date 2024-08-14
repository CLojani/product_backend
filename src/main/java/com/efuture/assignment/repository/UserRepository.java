package com.efuture.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.efuture.assignment.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

	User findByEmployeeCode(String username);

}
