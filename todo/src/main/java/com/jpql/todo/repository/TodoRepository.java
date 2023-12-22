package com.jpql.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpql.todo.entity.Todo;
import com.jpql.todo.entity.User;


public interface TodoRepository extends JpaRepository<Todo, Integer> {

	public List<Todo> findByRegistUser(User entity);
	
}
