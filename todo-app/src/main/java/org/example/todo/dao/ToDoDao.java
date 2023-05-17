package org.example.todo.dao;

import java.util.List;

import org.example.todo.model.ToDo;

public interface ToDoDao {
	
	public List<ToDo> getAllToDos();
	public ToDo getToDoById(int todoId);
	public ToDo createToDo(ToDo todo);
	public ToDo updateToDo(int todoId, ToDo todo);
	public void deleteToDoById(int todoId);
	public void deleteAllToDos();

}