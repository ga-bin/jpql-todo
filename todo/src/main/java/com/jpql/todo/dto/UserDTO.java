package com.jpql.todo.dto;

import com.jpql.todo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private int userId;
	private String id;
	private String nickname;
	private String password;
	
	
	public User toEntity() {
		User.UserBuilder builder = User.builder();
		
		builder.userId(userId)
				.id(id)
				.nickname(nickname)
				.password(password);
		
		return builder.build();
	}
}
