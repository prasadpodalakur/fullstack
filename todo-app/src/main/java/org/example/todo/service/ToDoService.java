package org.example.todo.service;

import java.util.List;

import org.example.todo.model.ToDo;

public interface ToDoService {
	
	public List<ToDo> getAllToDos();
	public ToDo getToDoById(int toDoId);
	public ToDo createToDo(ToDo toDo);
	public ToDo updateToDo(int toDoId, ToDo toDo);
	public void deleteToDoById(int toDoId);
	public void deleteAllToDos();
	
}
