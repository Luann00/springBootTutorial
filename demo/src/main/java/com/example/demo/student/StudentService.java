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
				.findStudentByEmail(student.getEmail());
		
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
	
	
	 public void updateStudent(Long id, String name, String email) {
	        Optional<Student> studentOptional = studentRepository.findById(id);
	        if (!studentOptional.isPresent()) {
	            throw new IllegalStateException("Student with id " + id + " does not exist");
	        }
	        Student student = studentOptional.get();
	        
	        if (name != null && !name.isEmpty() && !name.equals(student.getName())) {
	            student.setName(name);
	        }
	        
	        if (email != null && !email.isEmpty() && !email.equals(student.getEmail())) {
	            student.setEmail(email);
	        }
	        
	        studentRepository.save(student);
	    }

}
