package com.jpql.todo.service;

import org.springframework.stereotype.Service;

import com.jpql.todo.dto.UserDTO;
import com.jpql.todo.entity.User;
import com.jpql.todo.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void save(UserDTO userDTO) {
		userRepository.save(userDTO.toEntity());
	}

	public UserDTO findUser(UserDTO userDTO) {
		User user = userRepository.findByIdAndPassword(userDTO.getId(), userDTO.getPassword());
		return user.toDTO();
	}

}
