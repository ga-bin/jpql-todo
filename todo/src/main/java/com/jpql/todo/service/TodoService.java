package com.jpql.todo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jpql.todo.dto.TodoDTO;
import com.jpql.todo.dto.UserDTO;
import com.jpql.todo.entity.Todo;
import com.jpql.todo.entity.User;
import com.jpql.todo.repository.TodoRepository;
import com.jpql.todo.repository.UserRepository;

@Service
public class TodoService {

	private final TodoRepository todoRepository;
	private final UserRepository userRepository;
	
	public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;
	}
	
	public void insTodo(TodoDTO todoDTO, UserDTO loginUser) {
		Optional<User> optionalUser = userRepository.findById(loginUser.getUserId());
		User user = optionalUser.get();

		Todo todo = new Todo();
		todo.setDate(todoDTO.getDate());
		todo.setRegistUser(user);
		todo.setTask(todoDTO.getTask());
		todoRepository.save(todo);
	}
	
	public List<TodoDTO> findTodo(UserDTO loginUser) {
		List<Todo> todoList = todoRepository.findByRegistUser(loginUser.toEntity());
		List<TodoDTO> todoDTOList = todoList.stream().map(Todo::toDTO).collect(Collectors.toList());
		return todoDTOList;
	}

	public void delTodo(int todoId) {
		Optional<Todo> optionalTodo = todoRepository.findById(todoId);
		Todo todo = optionalTodo.get();
		todo.setRegistUser(null);
		todoRepository.delete(todo);
	}

}
