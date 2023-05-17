package org.example.todo.dao;

import java.util.List;

import javax.persistence.Query;

import org.example.todo.factory.MyFactory;
import org.example.todo.model.ToDo;
import org.hibernate.Session;

public class ToDoDaoImpl implements ToDoDao{
	
	private Session session;
	
	{
		session = MyFactory.getSessionFactory().openSession();
	}
	

	@Override
	public List<ToDo> getAllToDos() {
		Query query = session.createQuery("FROM ToDo");
		List<ToDo> toDosList = query.getResultList();
		 
		return toDosList;
	}


	@Override
	public ToDo getToDoById(int toDoId) {
		ToDo toDo = session.find(ToDo.class, toDoId);
		return toDo;
	}

	@Override
	public ToDo createToDo(ToDo toDo) {
		session.getTransaction().begin();
		session.save(toDo);
		session.getTransaction().commit();
		return toDo;
	}
	
	@Override
	public ToDo updateToDo(int toDoId, ToDo newToDo) {
		session.getTransaction().begin();
		
		ToDo toDo = session.get(ToDo.class, toDoId);
		if(toDo == null) {
			System.out.println("no ToDo found for the id...:"+toDoId);
		}else {
			toDo.setTodoName(newToDo.getTodoName());
			toDo.setIscompleted(newToDo.getIscompleted());
			session.save(toDo);
			
			System.out.println("ToDo has been updated...");
		}
		
		session.getTransaction().commit();
		
		return toDo;
	}
	
	@Override
	public void deleteToDoById(int toDoId) {
		session.getTransaction().begin();
		ToDo toDo = session.get(ToDo.class, toDoId);
		if(toDo == null) {
			System.out.println("no ToDo found for the id...:"+toDoId);
		}else {
			session.delete(toDo);
			System.out.println("ToDo has been deleted...");
		}
		session.getTransaction().commit();
	}


	@Override
	public void deleteAllToDos() {
		session.getTransaction().begin();
		
		Query query = session.createQuery("FROM ToDo");
		List<ToDo> toDoList = query.getResultList();
		for(ToDo s: toDoList) {
			session.delete(s);
		}
		
		session.getTransaction().commit();
	}
	

}
