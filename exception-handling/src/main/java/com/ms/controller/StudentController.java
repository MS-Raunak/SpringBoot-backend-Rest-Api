package com.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.dto.StudentDto;
import com.ms.exception.ResourceNotFoundException;
import com.ms.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@PostMapping("/save")
	public ResponseEntity<?> postMethodName(@RequestBody StudentDto studentDto) {
		StudentDto saveStudent = studentService.saveStudent(studentDto);
		
		return new ResponseEntity<>(saveStudent, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getMethodName(@PathVariable("id") Integer id) {
		StudentDto studnStudentById = studentService.getStudnStudentById(id);
		
		return new ResponseEntity<>(studnStudentById, HttpStatus.OK);
	}
	
	
}
