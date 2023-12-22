package com.jpql.todo.entity;

import com.jpql.todo.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String id;
	private String nickname;
	private String password;
	
	
	public UserDTO toDTO() {
		UserDTO.UserDTOBuilder builder = UserDTO.builder();
		
		builder.userId(userId)
				.id(id)
				.nickname(nickname)
				.password(password);
		
		return builder.build();
	}
	
}
