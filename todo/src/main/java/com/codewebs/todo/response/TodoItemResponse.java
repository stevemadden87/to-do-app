package com.codewebs.todo.response;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.codewebs.todo.entities.TodoItem;
import com.codewebs.todo.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemResponse {

	private Integer id;
	private String description;
	private Integer ownerId;
	private Boolean isDone = false;
	private String lastUpdated;

	public TodoItemResponse(TodoItem userItem) {
		this.id = userItem.getId();
		this.description = userItem.getDescription();
		this.ownerId = userItem.getOwner().getId();
		this.isDone = userItem.getIsDone();
		this.lastUpdated = new SimpleDateFormat("dd-MM-yyyy").format(userItem.getLastUpdated());
		System.out.println(lastUpdated);

	}

	public TodoItemResponse(Integer id, String description, User owner, Boolean isDone, Date lastUpdated) {
		
		this.id = id;
		this.description = description;
		this.ownerId = owner.getId();
		this.isDone = isDone;
		this.lastUpdated = new SimpleDateFormat("dd-MM-yyyy").format(lastUpdated);
		System.out.println(lastUpdated);
		
		
	}

}