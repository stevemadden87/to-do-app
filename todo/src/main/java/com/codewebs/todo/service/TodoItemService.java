package com.codewebs.todo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.codewebs.todo.entities.TodoItem;
import com.codewebs.todo.entities.User;
import com.codewebs.todo.repository.TodoItemRepository;
import com.codewebs.todo.request.Pagination;
import com.codewebs.todo.response.TodoItemResponse;

@Service
public class TodoItemService {

	private final TodoItemRepository todoItemRepository;

	@Autowired
	public TodoItemService(TodoItemRepository todoItemRepository) {
		this.todoItemRepository = todoItemRepository;
	}
	
	public TodoItem createNewTodoItem(String description, User owner) {
		TodoItem todoItem = new TodoItem();
		todoItem.setDescription(description);
		todoItem.setIsDone(false);
		todoItem.setOwner(owner);
		todoItem.setLastUpdated(new Date());
		return todoItemRepository.save(todoItem);
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

	public TodoItem editTodoItem(TodoItem editedItem, String description) {
		editedItem.setDescription(description);
		editedItem.setLastUpdated(new Date());
		editedItem.setIsDone(false);
		return todoItemRepository.save(editedItem);
	}

	public Page<TodoItem> getAllItemsForOwnerPaginated(User owner, org.springframework.data.domain.Pageable paging) {
		return todoItemRepository.findByOwnerOrderByIdDesc(owner, paging);

	}
	
	public Page<TodoItem> getAllItemsForOwnerDefault(User owner, org.springframework.data.domain.Pageable paging) {
		return todoItemRepository.findByOwnerOrderByIdDesc(owner, paging);

	}

	public TodoItem getItem(Integer id) {
		return todoItemRepository.findById(id).orElseThrow();
	}
	
	

}
