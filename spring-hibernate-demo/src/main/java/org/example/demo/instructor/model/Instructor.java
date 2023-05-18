package org.example.demo.instructor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Instructor")
public class Instructor {
	@Id
	@Column(name = "instructor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int instructorId;
	@Column(name = "instructor_name")
	private String instructorName;
	@Column(name = "instructor_ratings")
	private String instructorRating;
	public Instructor() {
		super();
	}
	public Instructor(String instructorName, String instructorRating) {
		super();
		this.instructorName = instructorName;
		this.instructorRating = instructorRating;
	}
	public int getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	public String getInstructorName() {
		return instructorName;
	}
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	public String getInstructorRating() {
		return instructorRating;
	}
	public void setInstructorRating(String instructorRating) {
		this.instructorRating = instructorRating;
	}
	@Override
	public String toString() {
		return "Instructor [instructorId=" + instructorId + ", instructorName=" + instructorName + ", instructorRating="
				+ instructorRating + "]";
	}
	
}