package com.jpql.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jpql.todo.dto.UserDTO;
import com.jpql.todo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String loginView() {
		return "/login.html";
	}
	
	@GetMapping("/signIn")
	public String signInView() {
		return "/signIn.html";
	}
	
	@PostMapping("/signIn")
	public String signIn(UserDTO userDTO) {
		userService.save(userDTO);
		return "redirect:/login";
	}
	
	@PostMapping("/login")
	public String login(UserDTO userDTO, HttpSession session) {
		UserDTO loginUser = userService.findUser(userDTO);
		session.setAttribute("loginUser", loginUser);
		return "redirect:/todo";
		
	} 
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:/login";
		
	}
	
	@GetMapping("/searchUser")
	public String searchUserView() {
		return "/searchUser";
	}
}
