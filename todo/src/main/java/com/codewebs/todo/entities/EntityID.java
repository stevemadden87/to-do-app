package com.codewebs.todo.entities;

import java.sql.Date;
import java.util.UUID;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.codewebs.todo.entities.TodoItem.TodoItemBuilder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;




@MappedSuperclass
@Getter
@Setter
@Builder
public class EntityID {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

}
