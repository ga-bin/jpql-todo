package com.jpql.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpql.todo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByIdAndPassword(String id, String password);

}
