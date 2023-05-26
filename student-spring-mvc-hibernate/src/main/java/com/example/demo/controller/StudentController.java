package com.example.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@Controller
public class StudentController {
	
	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}	
	
	@GetMapping("/")
	public String studentHome(Model theModel) {
		try {
			List<Student> theStudents = studentService.getAllStudents();
			theModel.addAttribute("students", theStudents);
			return "student-home";
		} catch (Exception e) {
			return "student-home";
		}
	}
	
	@GetMapping("listStudents")
	public String listStudents(Model theModel) {
		try {
			List<Student> theStudents = studentService.getAllStudents();
			theModel.addAttribute("students", theStudents);
			return "list-students";
		} catch (Exception e) {
			return "student-home";
		}
		
	}
	
	@GetMapping("/showForm")
	public String showFormForAdd(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "student-form";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student theStudent, Model theModel) throws Exception {
		try {
			Student student = studentService.createStudent(theStudent);
			
			if(student != null) {
				return "student-form-success";
			}
		 } catch (Exception e) { 
			 System.out.println("saveStudent:"+e.getMessage());
			 theStudent.setErrorMessage("*** "+e.getMessage());
			 
			 theModel.addAttribute("student", theStudent);
			 return "student-form"; 
		 }
		return "student-form-success";
	}
	
	@PostMapping("/updateStudent")
	public String updateStudent(@ModelAttribute("student") Student theStudent, Model theModel) throws Exception {
		try {
			Student student = studentService.updateStudent(theStudent);
			
			if(student != null) {
				return "student-form-success";
			}
		 } catch (Exception e) { 
			 System.out.println("saveStudent:"+e.getMessage());
			 theStudent.setErrorMessage("*** "+e.getMessage());
			 
			 theModel.addAttribute("student", theStudent);
			 return "update-student-form"; 
		 }
		return "student-form-success";
	}
	
	@GetMapping("/showStudentFormById")
	public String showStudentFormById(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "find-student-form-id";
	}
	@PostMapping("/listStudentsById")
	public String showStudentById(@ModelAttribute("student") Student theStudent, @RequestParam("id") int theId,
									Model theModel) {
		try {
			Student student = studentService.findStudent(theId);	
			if(student == null) {
				theStudent.setErrorMessage("*** No student found for the given Id:");
				return "find-student-form-id";
			}else {
				List<Student> theStudents = new ArrayList<>();
				theStudents.add(student);
				theModel.addAttribute("students", theStudents);
				return "list-students";
				
			}
		} catch (Exception e) {
			theStudent.setErrorMessage("*** "+e.getMessage());
			return "find-student-form-id";
		}
	}
	
	@GetMapping("/showStudentFormByName")
	public String showStudentFormByName(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "find-student-form-name";
	}
	@PostMapping("/listStudentsByName")
	public String showStudentByName(@ModelAttribute("student") Student theStudent, @RequestParam("name") String theName,
									Model theModel) {
		
		try {
			List<Student> theStudents = studentService.findStudent(theName);	
			if(theStudents == null || theStudents.isEmpty()) {
				theStudent.setErrorMessage("*** No student found for the given Name:");
				return "find-student-form-name";
			}else {
				theModel.addAttribute("students", theStudents);
				return "list-students";
			}
		} catch (Exception e) {
			theStudent.setErrorMessage("*** "+e.getMessage());
			return "find-student-form-name";
		}
		
	}
	
	@GetMapping("/showStudentFormByEnteringDate")
	public String showStudentFormByEnteringDate(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "find-student-form-entering-date";
	}
	@PostMapping("/listStudentsByEnteringDate")
	public String showStudentByEnteringDate(@ModelAttribute("student") Student theStudent, @RequestParam("enteringDate") Date theEnteringDate,
									Model theModel) {
		
		try {
			List<Student> theStudents = studentService.findStudent(theEnteringDate);	
			if(theStudents == null || theStudents.isEmpty()) {
				theStudent.setErrorMessage("*** No student found for the given Entering Date:");
				return "find-student-form-entering-date";
			}else {
				theModel.addAttribute("students", theStudents);
				return "list-students";
			}
		} catch (Exception e) {
			theStudent.setErrorMessage("*** "+e.getMessage());
			return "find-student-form-entering-date";
		}
		
	}
	
	@GetMapping("/updateStudentFormById")
	public String updateStudentFormById(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "update-student-form-id";
	}
	@PostMapping("/updateStudentById")
	public String updateStudentById(@ModelAttribute("student") Student theStudent, @RequestParam("id") int theId,
									Model theModel) {
		try {
			Student student = studentService.findStudent(theId);	
			if(student == null) {
				theStudent.setid(theId);
				theStudent.setErrorMessage("*** No student found for the given Id:");
				return "update-student-form-id";
			}else {
				theModel.addAttribute("student", student);
				return "update-student-form";
			}
		} catch (Exception e) {
			theStudent.setErrorMessage("*** "+e.getMessage());
			return "update-student-form-id";
		}
		
	}
	
	
	@GetMapping("/updateStudentFormByName")
	public String updateStudentFormByName(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "update-student-form-name";
	}
	@PostMapping("/updateStudentByName")
	public String updateStudentName(@ModelAttribute("student") Student theStudent, @RequestParam("name") String theName,
									Model theModel) {
		
		try {
			List<Student> theStudents = studentService.findStudent(theName);	
			if(theStudents == null || theStudents.isEmpty()) {
				theStudent.setName(theName);
				theStudent.setErrorMessage("*** No student found for the given Id:");
				return "update-student-form-name";
			}else {
				theModel.addAttribute("student", theStudents.get(0));
				return "update-student-form";
			}
		} catch (Exception e) {
			theStudent.setErrorMessage("*** "+e.getMessage());
			return "update-student-form-name";
		}
		
	}
	
	@GetMapping("/deleteStudentFormById")
	public String deleteStudentFormById(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "delete-student-form-id";
	}
	@PostMapping("/deleteStudentById")
	public String deleteStudentId(@ModelAttribute("student") Student theStudent, @RequestParam("id") int theId,
									Model theModel) {
		try {
			studentService.deleteStudent(theId);
			
			theStudent.setid(theId);
			return "student-form-success";
		} catch (Exception e) {
			theStudent.setid(theId);
			theStudent.setErrorMessage("*** No student found for the given Id:");
			return "delete-student-form-id";
		}	
	}
	
	@GetMapping("/deleteAllStudentsForm")
	public String deleteAllStudentsForm(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("student", theStudent);
		return "delete-all-students-form";
	}
	@PostMapping("/deleteAllStudents")
	public String deleteAllStudents(@ModelAttribute("student") Student theStudent,
									Model theModel) {
		try {
			studentService.deleteAll();
			
			return "student-form-success";
		} catch (Exception e) {
			e.printStackTrace();
			theStudent.setErrorMessage("*** Error occurred while deleting all students:");
			return "delete-all-students-form";
		}	
	}
	
}
