package org.example.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todo")
public class ToDo {
	@Id
	@Column(name="todoId")
	private int todoId;
	@Column(name="todo_name")
	private String todoName;
	@Column(name="iscompleted")
	private Boolean iscompleted;
	
	
	public ToDo() {
		super();
	}


	public ToDo(int todoId, String todoName, Boolean iscompleted) {
		super();
		this.todoId = todoId;
		this.todoName = todoName;
		this.iscompleted = iscompleted;
	}


	public ToDo(String todoName, Boolean iscompleted) {
		this.todoName = todoName;
		this.iscompleted = iscompleted;
	}


	public int getTodoId() {
		return todoId;
	}


	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}


	public String getTodoName() {
		return todoName;
	}


	public void setTodoName(String todoName) {
		this.todoName = todoName;
	}


	public Boolean getIscompleted() {
		return iscompleted;
	}


	public void setIscompleted(Boolean iscompleted) {
		this.iscompleted = iscompleted;
	}


	@Override
	public String toString() {
		return "ToDo [todoId=" + todoId + ", todoName=" + todoName + ", iscompleted=" + iscompleted + "]";
	}
	
}
