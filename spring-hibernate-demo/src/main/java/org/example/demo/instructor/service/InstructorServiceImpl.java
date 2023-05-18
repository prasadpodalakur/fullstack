package org.example.demo.instructor.service;

import java.util.List;

import org.example.demo.instructor.dao.InstructorDao;
import org.example.demo.instructor.model.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("instructorService")
public class InstructorServiceImpl implements InstructorService {
	
	private InstructorDao instructorDao;
	
	@Autowired
	public InstructorServiceImpl(InstructorDao instructorDao) {

		this.instructorDao = instructorDao;
		
	}

	@Override
	public List<Instructor> getAllInstructors() {
		return instructorDao.getAllInstructors();
	}

	@Override
	public Instructor getInstructorById(int instructorId) {
		return instructorDao.getInstructorById(instructorId);
	}

	@Override
	public Instructor createInstructor(Instructor instructor) {
		return instructorDao.createInstructor(instructor);
	}
	
	@Override
	public Instructor updateInstructor(int instructorId, Instructor instructor) {
		return instructorDao.updateInstructor(instructorId, instructor);
	}
	
	@Override
	public void deleteInstructorById(int instructorId) {
		instructorDao.deleteInstructorById(instructorId);
	}

	@Override
	public void deleteAllInstructors() {
		instructorDao.deleteAllInstructors();
	}

}
