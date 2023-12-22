package com.jpql.todo.entity;

import java.time.LocalDate;

import com.jpql.todo.dto.TodoDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int todoId;
	
	private String task;
	
	private LocalDate date;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User registUser;
	
	public TodoDTO toDTO() {
		TodoDTO.TodoDTOBuilder builder = TodoDTO.builder();
		
		builder.todoId(todoId)
				.task(task)
				.date(date);
		
		
		if(registUser != null) {
			builder.registUser(registUser.toDTO());
		}
		
		return builder.build();
	}
}
