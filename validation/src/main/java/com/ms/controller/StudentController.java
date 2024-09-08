package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.dto.StudentDto;
import com.ms.service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController {
	@Autowired
	StudentService studentService;
	
	@GetMapping("/")
	public List<StudentDto> getAllStudent(){
		return studentService.getAllStudents();

	}
	
	@PostMapping("/save")
	public ResponseEntity<?> postMethodName(@Valid @RequestBody StudentDto studentDto) {
		StudentDto saveStudent = studentService.saveStudent(studentDto);
		
		return new ResponseEntity<>(saveStudent, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getMethodName(@PathVariable("id") Integer id) {
		StudentDto studnStudentById = studentService.getStudnStudentById(id);
		
		return new ResponseEntity<>(studnStudentById, HttpStatus.OK);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<StudentDto> putMethodName(@Valid @RequestBody StudentDto studentDto, @PathVariable Integer id) {
		StudentDto updateStudentById = studentService.updateStudentById(studentDto, id);	
		return new ResponseEntity<StudentDto>(updateStudentById, HttpStatus.OK);
	}
	
	
}
