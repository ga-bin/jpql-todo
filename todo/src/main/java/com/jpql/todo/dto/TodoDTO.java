package com.jpql.todo.dto;

import java.time.LocalDate;

import com.jpql.todo.entity.Todo;
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
public class TodoDTO {
	private int todoId;
	private String task;
	private LocalDate date;
	private UserDTO registUser;
	
	public Todo toEntity() {
		Todo.TodoBuilder builder = Todo.builder();
		
		
		builder.todoId(todoId)
				.task(task)
				.date(date);
		
		
		if(registUser != null) {
			builder.registUser(registUser.toEntity());
		}
		
		return builder.build();
	}
}
