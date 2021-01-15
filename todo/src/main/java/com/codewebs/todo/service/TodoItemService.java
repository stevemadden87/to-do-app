package com.codewebs.todo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewebs.todo.entities.TodoItem;
import com.codewebs.todo.entities.User;
import com.codewebs.todo.repository.TodoItemRepository;

@Service
public class TodoItemService {

	private final TodoItemRepository todoItemRepository;

	@Autowired
	public TodoItemService(TodoItemRepository todoItemRepository) {
		this.todoItemRepository = todoItemRepository;
	}
	
	public TodoItem createNewTodoItem(TodoItem item) {
		return todoItemRepository.save(item);
	}
	
	public TodoItem changeDoneStateForTodoItem(TodoItem item) {
		if (item != null) {
			item.setIsDone(!item.getIsDone());
			todoItemRepository.save(item);
			return item;
		}
		return null;
	}

	public Boolean deleteTodoItem(TodoItem item) {
		if (item != null) {
			
			todoItemRepository.delete(item);
			return true;
		}
		return false;
	}

	public TodoItem editTodoItem(TodoItem editedItem) {
		editedItem.setDescription(editedItem.getDescription());
		return todoItemRepository.save(editedItem);
	}

	public List<TodoItem> getAllItemsForOwner(User owner) {
		return todoItemRepository.findByOwner(owner);

	}

	public TodoItem getItem(Integer id) {
		return todoItemRepository.findById(id).orElseThrow();
	}

}
