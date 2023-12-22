package com.jpql.todo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jpql.todo.dto.TodoDTO;
import com.jpql.todo.dto.UserDTO;
import com.jpql.todo.service.TodoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TodoController {

	private final TodoService todoService;
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@RequestMapping("/todo")
	public String todo(Model model, HttpSession session) {
		
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		List<TodoDTO> todoDTOList = todoService.findTodo(loginUser);
		Map<LocalDate, List<TodoDTO>> todoMap = new HashMap<>();
		todoMap = createToDoMap(todoDTOList);
		
		model.addAttribute("currentDate", LocalDate.now());
		model.addAttribute("todoMap", todoMap);
		model.addAttribute("loginUser", loginUser);
		return "/todo.html";
	}

	private Map<LocalDate, List<TodoDTO>> createToDoMap(List<TodoDTO> todoDTOList) {
		
		Map<LocalDate, List<TodoDTO>> todoMap = new HashMap<>();
		for(TodoDTO todo : todoDTOList) {
			todoMap.computeIfAbsent(todo.getDate(), k -> new ArrayList<>()).add(todo);
		}
		return todoMap;
	}
	
	@PostMapping("/insTodo")
	public String insTodo(TodoDTO todoDTO, HttpSession session) {
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		todoService.insTodo(todoDTO, loginUser);
		return "redirect:/todo";
	}
	
	@GetMapping("/delTodo")
	public String delTodo(@RequestParam("todoId") int todoId) {
		todoService.delTodo(todoId);
		return "redirect:/todo";
	}
	
	
	
}
