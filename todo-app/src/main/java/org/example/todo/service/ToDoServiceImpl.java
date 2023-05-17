package org.example.todo.service;

import java.util.List;

import org.example.todo.dao.ToDoDao;
import org.example.todo.dao.ToDoDaoImpl;
import org.example.todo.model.ToDo;

 

public class ToDoServiceImpl implements ToDoService {
	
	private ToDoDao toDoDao;
	
	{
		toDoDao=new ToDoDaoImpl();
	}

	@Override
	public List<ToDo> getAllToDos() {
		return toDoDao.getAllToDos();
	}

	@Override
	public ToDo getToDoById(int toDoId) {
		return toDoDao.getToDoById(toDoId);
	}

	@Override
	public ToDo createToDo(ToDo toDo) {
		return toDoDao.createToDo(toDo);
	}
	
	@Override
	public ToDo updateToDo(int toDoId, ToDo toDo) {
		return toDoDao.updateToDo(toDoId, toDo);
	}
	
	@Override
	public void deleteToDoById(int toDoId) {
		toDoDao.deleteToDoById(toDoId);
	}

	@Override
	public void deleteAllToDos() {
		toDoDao.deleteAllToDos();
	}

}
