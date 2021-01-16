package com.codewebs.todo.service;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import com.codewebs.todo.entities.TodoItem;
import com.codewebs.todo.entities.User;
import com.codewebs.todo.repository.TodoItemRepository;

@RunWith(SpringRunner.class)
public class ToDoItemServiceTest {

	@InjectMocks
	private TodoItemService todoItemService;

	@Mock
	private UserService userService;

	@Mock
	TodoItemRepository todoItemRepository;

	@Captor
	private ArgumentCaptor<TodoItem> toDoItemArgumentCaptor;

//	@Test
//	public void getAllItemsForOwner() {
//		
//		User user = User.builder().id(1).build();
//		TodoItem todoItem1 = TodoItem.builder().id(1).description("Item 1 description").isDone(false)
//				.lastUpdated(new java.util.Date()).owner(user).build();
//		TodoItem todoItem2 = TodoItem.builder().id(2).description("Item 2 description").isDone(false)
//				.lastUpdated(new java.util.Date()).owner(user).build();
//		
//		List<TodoItem> toDoItemsList1 = new ArrayList<TodoItem>();
//		toDoItemsList1.add(todoItem1);
//		toDoItemsList1.add(todoItem2);
//		
//		todoItemService.getAllItemsForOwner(user);
//		verify(todoItemRepository, times(1)).save(toDoItemArgumentCaptor.capture());
//		List<TodoItem> todoItemsList2 = toDoItemArgumentCaptor.getAllValues();
//		
//		assertTrue(CollectionUtils.isEqualCollection(toDoItemsList1, todoItemsList2));
//	}

	@Test
	public void deleteTodoItem() {
		
		TodoItem todoItem = TodoItem.builder().id(1).description("Item description").build();
		todoItemService.deleteTodoItem(todoItem);
		
		Mockito.verify(todoItemRepository, times(1)).save(toDoItemArgumentCaptor.capture());
		TodoItem deletedItem = toDoItemArgumentCaptor.getValue();
		
		assertNull(todoItemService.getItem(deletedItem.getId()));

	}

	@Test
	public void createNewTodoItem() {
		User user = User.builder().id(1).build();
		TodoItem todoItem = TodoItem.builder().id(1).description("Item description").owner(user).build();
		todoItemService.createNewTodoItem(todoItem.getDescription(), todoItem.getOwner());

		verify(todoItemRepository, times(1)).save(toDoItemArgumentCaptor.capture());
		TodoItem todoItem2 = toDoItemArgumentCaptor.getValue();

		assertEquals("Item description", todoItem2.getDescription());
		assertEquals(1, todoItem2.getId());

	}

	@Test
	public void changeDoneStateForTodoItem() {
		
		User user = User.builder().id(1).build();
		TodoItem todoItem = TodoItem.builder().id(1).description("Task name").isDone(false)
				.lastUpdated(new java.util.Date()).owner(user).build();
		todoItemService.changeDoneStateForTodoItem(todoItem);

		verify(todoItemRepository, times(1)).save(toDoItemArgumentCaptor.capture());
		TodoItem todoItem2 = toDoItemArgumentCaptor.getValue();

		assertEquals(true, todoItem2.getIsDone());
		assertEquals(1, todoItem2.getId());
	}

	@Test
	public void editTodoItem() {
		
		TodoItem toDoItem = TodoItem.builder().id(1).description("New description name").build();
		todoItemService.editTodoItem(toDoItem, toDoItem.getDescription());

		verify(todoItemRepository, times(1)).save(toDoItemArgumentCaptor.capture());
		TodoItem editedTodoItem = toDoItemArgumentCaptor.getValue();

		assertEquals(toDoItem.getId(), editedTodoItem.getId());
		assertEquals(toDoItem.getDescription(), editedTodoItem.getDescription());
	}

}
