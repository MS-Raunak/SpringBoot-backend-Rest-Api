package com.ms.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.dto.StudentDto;
import com.ms.exception.ResourceNotFoundException;
import com.ms.model.Student;
import com.ms.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	ModelMapper mapper;

	@Override
	public List<StudentDto> getAllStudents(){
		List<Student> allStudentEntity = studentRepo.findAll();
		
		List<StudentDto> studentListDto = allStudentEntity.stream().map(student->mapper.map(student, StudentDto.class)).toList();
		
		return studentListDto;
	}
	
	@Override
	public StudentDto saveStudent(StudentDto studentDto) {
		Student student = mapper.map(studentDto, Student.class);
		Student savedStudent = studentRepo.save(student);
		
		StudentDto saveStudentDto = mapper.map(savedStudent, StudentDto.class);
		
		return saveStudentDto;
	}

	@Override
	public StudentDto getStudnStudentById(Integer id) {
		Optional<Student> byId = studentRepo.findById(id);
		
		if (!byId.isPresent()) {
			 throw new ResourceNotFoundException("Student", "Id", id);
		}
		
		
		Student student = byId.get();
		StudentDto studentDto = mapper.map(student, StudentDto.class);
		return studentDto;
	}

	
	@Override
	public StudentDto updateStudentById(StudentDto studentDto, Integer id) {
		Optional<Student> byId = studentRepo.findById(id);
		
		if (!byId.isPresent()) {
			throw new ResourceNotFoundException("Student", "Id", id);
		}
		
		Student student = byId.get();
		
		mapper.map(studentDto, student);
		student.setId(id);
		
		Student savedStudent = studentRepo.save(student);
		
		return mapper.map(savedStudent, StudentDto.class);
		
	}

}
