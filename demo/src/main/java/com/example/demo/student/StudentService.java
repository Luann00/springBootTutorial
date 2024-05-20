package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	
	public List<Student> getStudents() {
		return studentRepository.findAll();
				
	}
	
	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = 
				studentRepository
				.findStudentByEmail(student.geteMail());
		
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		} 
		
		studentRepository.save(student);
		System.out.println(student);
	}
	
	public void deleteStudent(Long studentId) {
		
		boolean exists = studentRepository.existsById(studentId);
		
		if(!exists) {
			throw new IllegalStateException("student with id " + studentId + " does not exists");
			}
		
		studentRepository.deleteById(studentId);

				
	}
	
	
	@Transactional
	public void updateStudent(long id, String name, String email) {
		
		Student student = studentRepository.findById(id).orElseThrow(() ->
		new IllegalStateException("student with id " + id + " does not exist!"));
		
		
		if(name != null && (name.length() > 0) && !(name.equals(student.getName()))) {
			student.setName(name);
		}
		
		if(email != null && (email.length() > 0) && !(email.equals(student.geteMail()))) {
			
			Optional<Student> studentMail = studentRepository.findStudentByEmail(email);
			
			if(!studentMail.isPresent()) {
				student.seteMail(email);
			} else {
				throw new IllegalStateException("email is taken!");
			}
			
		}
		
		
		
		studentRepository.save(student);
		
	}

}
